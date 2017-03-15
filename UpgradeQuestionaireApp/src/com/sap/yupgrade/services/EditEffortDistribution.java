package com.sap.yupgrade.services;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.cxf.common.util.CollectionUtils;
import org.eclipse.persistence.config.PersistenceUnitProperties;

import com.sap.yupgrade.model.ActivityDetails;

/**
 * Servlet implementation class CreateJobs
 */
@WebServlet("/EditEffortDistribution")
public class EditEffortDistribution extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private List<ActivityDetails> activitiesList = null;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditEffortDistribution() {
		super();



	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		fetchActiviesFromDB(request);
		request.setAttribute("activitiesList", activitiesList);
		request.getRequestDispatcher("/editEffortDistribution.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String [] ids = request.getParameterValues("id");
		if (null != ids) {
			
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

				for (String id : ids) {
					ActivityDetails entity = em.find(ActivityDetails.class, new Integer(id));
					entity.setSdcShare(request.getParameter("sdc" + id) == null ? 0 : Integer.parseInt(request.getParameter("sdc" + id)));
					entity.setEsShare(request.getParameter("es" + id) == null ? 0 : Integer.parseInt(request.getParameter("es" + id)));
					entity.setOnsiteShare(request.getParameter("onsite" + id) == null ? 0 : Integer.parseInt(request.getParameter("onsite" + id)));
					entity.setIndexOfEffort(request.getParameter("index" + id) == null ? 0 : Integer.parseInt(request.getParameter("index" + id)));
					entity.setOverlap(request.getParameter("overlap" + id) == null ? 0 : Integer.parseInt(request.getParameter("overlap" + id)));
				}

				transaction.commit();
			}
			catch (Exception e) {
				System.out.println("Error" + e);
				//throw new ServletException(e);
			}
			finally{
				if(em.isOpen())
					em.close();
				if(emf.isOpen())
					emf.close();
			}
		}
				
		fetchActiviesFromDB(request);
		request.setAttribute("activitiesList", activitiesList);
		request.getRequestDispatcher("/editEffortDistribution.jsp").forward(request, response);
	}

	private void fetchActiviesFromDB(HttpServletRequest request) {
		EntityManager em = null;
		EntityManagerFactory emf = null;

		try {

			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DefaultDB");

			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put(PersistenceUnitProperties.NON_JTA_DATASOURCE, ds);
			emf = Persistence.createEntityManagerFactory("UpgradeQuestionaireApp", properties);

			em = emf.createEntityManager();


			Query query = em.createQuery("Select A from ActivityDetails A");

			List<ActivityDetails> activityDetsails = query.getResultList();

			if (!CollectionUtils.isEmpty(activityDetsails)) {
				activitiesList = activityDetsails;
			}

			Collections.sort(activityDetsails, new Comparator<ActivityDetails>() {

				@Override
				public int compare(ActivityDetails o1, ActivityDetails o2) {
					// TODO Auto-generated method stub
					return o1.getID().compareTo(o2.getID());
				}

			});

			for(ActivityDetails e:activityDetsails) {
				System.out.println("Employee NAME :"+e);
			}


		}
		catch (Exception e) {
			System.out.println("Error" + e);
			//throw new ServletException(e);
		}
		finally{
			if(em.isOpen())
				em.close();
			if(emf.isOpen())
				emf.close();
		}
	}

}
