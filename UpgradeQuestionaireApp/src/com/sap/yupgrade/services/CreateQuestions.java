package com.sap.yupgrade.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class CreateJobs
 */
@WebServlet("/CalculateUpgradeSize")
public class CreateQuestions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Map<String, Map<String,String>> technicalChangesMap = new HashMap<>();
	private static Map<String, String> dbDriverMap = new HashMap<>();
	
	private static List<String> deprecatedExtn = new ArrayList<>();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateQuestions() {
        super();
        
        dbDriverMap.put("org.hsqldb.jdbcDriver", "HSQLDB");
        dbDriverMap.put("com.mysql.jdbc.Driver", "MySQL");
        dbDriverMap.put("oracle.jdbc.driver.OracleDriver", "Oracle");
        dbDriverMap.put("com.microsoft.sqlserver.jdbc.SQLServerDriver", "Microsoft SQLServer");
        dbDriverMap.put("com.sap.db.jdbc.Driver", "SAP HANA");
        
        // TODO Auto-generated constructor stub
    	Map<String, String> latestVersion = new HashMap<>();
    	//latestVersion.put("Hybris Platform", "6.3");
    	latestVersion.put("JDK", "8");
    	latestVersion.put("Spring Framework", "4.3");
    	latestVersion.put("Spring Integration", "4.3");
    	latestVersion.put("Spring Security", "4.1");
    	latestVersion.put("Solr", "6.1");
    	
    	technicalChangesMap.put("latest", latestVersion);
    	
    	Map<String, String> pre5Version = new HashMap<>();
    	//pre5Version.put("Hybris Platform", "Pre 5.0");
    	pre5Version.put("JDK", "7");
    	pre5Version.put("Spring Framework", "3.1");
    	pre5Version.put("Spring Integration", "3.0");
    	pre5Version.put("Spring Security", "3.1");
    	pre5Version.put("Solr", "3.5");
    	technicalChangesMap.put("pre5Version", pre5Version);
    	
    	Map<String, String> post551Version = new HashMap<>();
    	//post551Version.put("Hybris Platform", "5.5.1+");
    	post551Version.put("JDK", "8");
    	post551Version.put("Spring Framework", "4.1");
    	post551Version.put("Spring Integration", "4.1");
    	post551Version.put("Spring Security", "3.2");
    	post551Version.put("Solr", "4.6");
    	technicalChangesMap.put("post551Version", post551Version);
    	
    	Map<String, String> fiveto551Version = new HashMap<>();
    	//fiveto551Version.put("Hybris Platform", "5.0 to 5.5.1");
    	fiveto551Version.put("JDK", "7 or 8");
    	fiveto551Version.put("Spring Framework", "3.2 to 4.1");
    	fiveto551Version.put("Spring Integration", "3.0 to 4.1");
    	fiveto551Version.put("Spring Security", "3.2");
    	fiveto551Version.put("Solr", "4.5");
    	technicalChangesMap.put("fiveto551Version", fiveto551Version);
    	
    	String [] depExtnArr = new String[] {"acceleratorsampledata","adminweb","b2b","b2bacceleratorfacades","b2bacceleratorsampledata","b2bstore","b2ccheckoutaddon","btg","category",
    			"chinaacceleratorfacades","chinaacceleratorservices","chinacheckoutaddon","chinastoreelectronics","chinesesnsjiathisaddon","ciscommons","cms","commercesearchsampledata",
    			"cronjob","cybersource","dataonboardingbackoffice","dataonboardingclient","facetsearch","flexstore","fulfilmentprocess","hyend","hyend2","hygocockpit","hygocommerce","hygosearch",
    			"instoresampledata","jiathis","jmssample","mam","mobiletemplate","oauthorizationserver","omsats","omsbackoffice","omsclient","omscommons","omsorders","omssampledata","ordermanagementbackoffice",
    			"perfweb","processengine","promotionservices","propertyregistry","propertyregistryclient","salesdemo","sampledata","sbgdemostorefront","servicelayer","soapspring","springmvcstore",
    			"storefoundation","storetemplate","subscriptionbundlecockpits","subscriptionbundleservices","task","telcoacceleratorsampledata","telcoacceleratorservices","telcostore","testdata",
    			"variants","webfoundation","webservice","yb2bacceleratorcore","yb2bacceleratorfacades","yb2bacceleratorinitialdata","yb2bacceleratorstorefront","yb2bacceleratortest","yinstoreinitialdata",
    			"ysapproductconfigb2baddon","ytelcoacceleratorcockpits","ytelcoacceleratorcore","ytelcoacceleratorfacades","ytelcoacceleratorinitialdata","ytelcoacceleratorstorefront"};
    	
    	deprecatedExtn.addAll(Arrays.asList(depExtnArr));
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		if (isMultipart) {
			processUploadFile(request);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			
		} else {
			calculateSizing(request);


			request.setAttribute("latest", technicalChangesMap.get("latest"));

			String currentVersion = request.getParameter("currentVersion");
			if ("pre5".equals(currentVersion)) {
				request.setAttribute("current", technicalChangesMap.get("pre5Version"));
			} else if ("5to55".equals(currentVersion)) {
				request.setAttribute("current", technicalChangesMap.get("fiveto551Version"));
			} else {
				request.setAttribute("current", technicalChangesMap.get("post551Version"));
			}

			request.getRequestDispatcher("/upgradeSizing.jsp").forward(request, response);
		}
	}

	private void processUploadFile(HttpServletRequest request) throws IOException {
		Map<String, String> infoMap = new HashMap<>();
		Map<String, String> extVersionap = new HashMap<>();
		BufferedReader br = null;
		try {
			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// Configure a repository (to ensure a secure temp location is used)
			ServletContext servletContext = this.getServletConfig().getServletContext();
			File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
			factory.setRepository(repository);

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

			// Parse the request
			List<FileItem> items = upload.parseRequest(request);
			
			// Process the uploaded items
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
			    FileItem item = iter.next();
			    br = new BufferedReader(new InputStreamReader(item.getInputStream()));
			    String line = null;
			    while ((line = br.readLine()) != null) {
			    	if (line.indexOf("=") > 0) {
			    		String[] info = line.split("=");
			    		if (info.length >1) {
			    			if (info[0].equals("build")) {
			    				infoMap.put("currentVersion", info[1]);
			    				
			    				String[] versions = info[1].split("\\.");
			    				
			    				double version = Double.parseDouble(versions[0] + "." + versions[1]);
			    				double more = new Double(5.5);
			    				double less = new Double(5.0);
			    				if (version > more) {
			    					infoMap.put("q1", "3");
			    				} else if (version < less) {
			    					infoMap.put("q1", "1");
			    				} else {
			    					infoMap.put("q1", "2");
			    				}
			    				
			    			}
			    			else if (info[0].equals("database")) {
			    				if (dbDriverMap.get(info[1]) != null) {
			    					infoMap.put("db", dbDriverMap.get(info[1]));
			    				} else {
			    					infoMap.put("db", info[1]);
			    				}
			    			} else if (info[0].equals("extensions")) {
			    				infoMap.put("extensions", info[1].replace(",", ", "));
			    			}			    			
			    			else {
			    				if (info[0].contains("core")) {
			    					infoMap.put("customer", info[0].replace("core", ""));			    					
			    					infoMap.put("coreversion", info[1]);
			    				}
			    				extVersionap.put(info[0], info[1]);
			    			}
			    		}
 			    	}
			    }
			    
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (br!= null) {
				br.close();
			}
		}
		
		if (infoMap.get("currentVersion") != null && infoMap.get("coreversion") != null && !infoMap.get("currentVersion").equals(infoMap.get("coreversion"))) {
			infoMap.put("q2", "true");
		}
		
		request.setAttribute("infoMap", infoMap);
		request.setAttribute("extVersionap", extVersionap);
		
		String extnStr = infoMap.get("extensions");
		List<String> usedExtnTmp = Arrays.asList(extnStr.split(","));
		List<String> usedExtn = new ArrayList<>();
		for (String ext : usedExtnTmp) {
			usedExtn.add(ext.trim());
		}
		
		usedExtn.retainAll(deprecatedExtn);
		
		request.setAttribute("depExtnList", usedExtn);

		
		request.setAttribute("showDetails", true);
	}
	
	private void calculateSizing (HttpServletRequest request) {
		String currentVersion = request.getParameter("currentVersion");
		String q1 = request.getParameter("q1");
		String q2 = request.getParameter("q2");
		String q3 = request.getParameter("q3");
		int size = 1;
		if ("yes".equals(q1) || "pre5".equals(currentVersion)) {
			size = 3;
		} else if ("yes".equals(q2) || "5to55".equals(currentVersion)) {
			size = 2;
		}
		
		if ("SAP HANA".equals(q3) && size == 1) {
			size = 2;
		}
		
		request.setAttribute("size", (size == 3) ? "L" : (size == 2) ? "M" : "S");
		request.setAttribute("name", request.getParameter("name"));
		
		request.setAttribute("currentVersion", "pre5".equals(currentVersion) ? "Pre 5.0" : "5to55".equals(currentVersion) ? "5.0 - 5.5.1" : "5.5.1+");
		request.setAttribute("q1", "yes".equals(q1) ? "Yes" : "No");
		request.setAttribute("q2", "yes".equals(q2) ? "Yes" : "No");		
		request.setAttribute("q3", q3);
	}
	
	/*private void doAdd(HttpServletRequest request) {
		EntityManager em = null;
		EntityManagerFactory emf = null;

		try {

			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DefaultDB");

			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put(PersistenceUnitProperties.NON_JTA_DATASOURCE, ds);
			emf = Persistence.createEntityManagerFactory("UpgradeQuestionaireApp", properties);
			em = emf.createEntityManager();
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();

			QuestionMaster ques1 = new QuestionMaster();
			ques1.setQUES_ID(1);
			ques1.setDESCRIPTION("Hybris Version Currently Used?");
			ques1.setACTIVE(true);

			em.persist(ques1);

			transaction.commit();	




		}
		catch (NamingException e) {
			System.out.println("Error");
			//throw new ServletException(e);
		}
		finally{
			if(em.isOpen())
				em.close();
			if(emf.isOpen())
				emf.close();
		}
}*/

}
