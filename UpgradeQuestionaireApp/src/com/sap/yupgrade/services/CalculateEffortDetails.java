package com.sap.yupgrade.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.apache.cxf.common.util.StringUtils;
import org.eclipse.persistence.config.PersistenceUnitProperties;

import com.sap.yupgrade.model.ActivityDetails;
import com.sap.yupgrade.model.ResourceDetails;

/**
 * Servlet implementation class CreateJobs
 */
@WebServlet("/CalculateEffortDetails")
public class CalculateEffortDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private List<ActivityDetails> activitiesList = null;
	private List<ResourceDetails> resources = null;
	


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CalculateEffortDetails() {
		super();
		//String actrivities = "Project Prepration,Code Changes,DB Level Changes,SAP Smoke Testing,Customer Testing (Fucntional & Regression),UAT,System Review & Performance Testing (yES),Product Cutover Activities & Production GO-LIVE,Post GO-LIVE Support,Project Management & QA";
		//activitiesList = Arrays.asList(actrivities.split(","));

		//totalEffortDist = new HashMap<String, Integer>();
		/*ActivityDetails activity1 = new ActivityDetails("Project Prepration", 4, 0, 0, 1, 0);
		activity1.setID(1);
		ActivityDetails activity2 = new ActivityDetails("Code Changes", 31, 0, 0, 1, 0);
		activity2.setID(2);
		ActivityDetails activity3 = new ActivityDetails("DB Level Changes", 7, 0, 0, 1, 0);
		activity3.setID(3);
		
		ActivityDetails activity4 = new ActivityDetails("SAP Smoke Testing", 5, 0, 0, 1, 0);
		activity4.setID(4);
		ActivityDetails activity5 = new ActivityDetails("Customer Testing (Functional & Regression)", 8, 0, 100, 1, 0);
		activity5.setID(5);
		ActivityDetails activity6 = new ActivityDetails("UAT", 4, 0, 100, 1, 0);
		activity6.setID(6);
		ActivityDetails activity10 = new ActivityDetails("System Review & Performance Testing (yES)", 10, 0, 100, 1, 0);
		activity10.setID(7);
		ActivityDetails activity7 = new ActivityDetails("Product Cutover Activities & Production GO-LIVE", 8, 0, 100, 1, 0);
		activity7.setID(8);
		ActivityDetails activity8 = new ActivityDetails("Post GO-LIVE Support", 8, 0, 50, 1, 0);
		activity8.setID(9);
		ActivityDetails activity9 = new ActivityDetails("Project Management & QA", 15, 0, 0, 0, 0);
		activity9.setID(10);*/
		
		ActivityDetails activity1 = new ActivityDetails("Discovery (Upgrade Analysis)", 5, 0, 100, 1, 0);
		activity1.setID(1);
		ActivityDetails activity2 = new ActivityDetails("Project Prepration", 4, 0, 0, 1, 0);
		activity2.setID(2);
		ActivityDetails activity3 = new ActivityDetails("Technical Upgrade (Code + DB + Media + Config)", 35, 0, 0, 1, 0);
		activity3.setID(3);	
		ActivityDetails activity4 = new ActivityDetails("SAP Smoke Testing", 5, 0, 0, 1, 0);
		activity4.setID(4);
		ActivityDetails activity5 = new ActivityDetails("Customer Testing (Functional & Regression)", 8, 0, 100, 1, 0);
		activity5.setID(5);
		ActivityDetails activity6 = new ActivityDetails("Application Hardening (Config Validation)", 8, 0, 0, 1, 0);
		activity6.setID(6);
		ActivityDetails activity7 = new ActivityDetails("Customer Acceptance Testing (UAT)", 4, 0, 100, 1, 0);
		activity7.setID(7);
		ActivityDetails activity8 = new ActivityDetails("Product Cutover Activities & Production GO-LIVE", 8, 0, 100, 1, 0);
		activity8.setID(8);
		ActivityDetails activity9 = new ActivityDetails("Post GO-LIVE Support", 8, 0, 50, 1, 0);
		activity9.setID(9);
		ActivityDetails activity10 = new ActivityDetails("Project Management & QA", 15, 0, 0, 0, 0);
		activity10.setID(10);


		activitiesList = new ArrayList<>();
		activitiesList.add(activity1);
		activitiesList.add(activity2);
		activitiesList.add(activity3);
		activitiesList.add(activity4);
		activitiesList.add(activity5);
		activitiesList.add(activity6);
		activitiesList.add(activity10);
		activitiesList.add(activity7);
		activitiesList.add(activity8);
		activitiesList.add(activity9);

		populateResources(1);


	}
	
	private void populateResources ( int numberOfDev) {
		resources = new ArrayList<>();
		
		
		Integer[] activitiespm = {0};		
		ResourceDetails resource2 = new ResourceDetails("MU PM (Outside Factory)", true, true, activitiespm);
		resources.add(resource2);
		
		Integer[] activitiesyes = {0};		
		ResourceDetails resourceyes = new ResourceDetails("Solution Arch [yES outside Factory]", true, true, activitiesyes);
		resources.add(resourceyes);
		
		Integer[] activitiespo = {10};		
		ResourceDetails resourcepo = new ResourceDetails("Product Owner (SDC PM)", true, false, activitiespo);
		resources.add(resourcepo);
		
		if (numberOfDev > 0) {		
			Integer[] activities = {1,2,3,4,5,6,7,8,9};		
			ResourceDetails resource1 = new ResourceDetails("Hybris Technical Arch", false, false, activities);
			resources.add(resource1);
		}
	
		if (numberOfDev > 1) {
			Integer[] activitiesdev1 = {2,3,4,5,6};		
			ResourceDetails resourcedev1 = new ResourceDetails("Developer 1", false, false, activitiesdev1);
			resources.add(resourcedev1);
		}
		
		if (numberOfDev > 2) {
			Integer[] activitiesdev2 = {2,3,4,5};		
			ResourceDetails resourcedev2 = new ResourceDetails("Developer 2", false, false, activitiesdev2);
			resources.add(resourcedev2);
		}
		
		if (numberOfDev > 3) {
			Integer[] activitiesdev3 = {2,3,4,5};
			ResourceDetails resourcedev3 = new ResourceDetails("Developer 3", false, false, activitiesdev3);
			resources.add(resourcedev3);
		}


	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		putDefaultAcitivitiesInDB(request, activitiesList);

		doPost(request, response);
	}

	private void putDefaultAcitivitiesInDB(HttpServletRequest request, List<ActivityDetails> activitiesList2) {
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

			for (ActivityDetails ques1  : activitiesList2) {
				em.persist(ques1);
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		fetchActiviesFromDB(request);
		
		int numberOfDev = 1;
		if (null !=request.getParameter("numberOfDev")) {
			try {
			numberOfDev = Integer.parseInt(request.getParameter("numberOfDev"));
			} catch (Exception e) {}
		}
		
		populateResources(numberOfDev);
		calculateSizing(request);
		
		request.setAttribute("resources", resources);
		request.setAttribute("numberOfDev", numberOfDev);
		
		String downloadExcel = request.getParameter("downloadexcel");
		
		if ("1".equals(downloadExcel)) {
			response.setContentType("application/vnd.ms-excel");
			request.getRequestDispatcher("/downloadEffortDetails.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/prepareEffortDetails.jsp").forward(request, response);
		}
	}

	private void calculateResourceWithEffort(int weeksToStart, HttpServletRequest request) {
		// TODO Auto-generated method stub
		for (ResourceDetails res : resources) {
			if (res.isFullTime()) {
				Integer id  = res.getListOfActivities()[0];
				ActivityDetails act = getActityForId(id);
				if (act != null) {
					List<String> effortList = new ArrayList<>();
										
					effortList.add(String.valueOf((int)(act.getTotalCalculatedEffort()*8.0)/weeksToStart));	
					int numberOfOnsiteWeeks = 0;
					if (act.getOnsiteShare() > 0) {
						numberOfOnsiteWeeks = act.getOnsiteShare()/5;						
					}
					effortList.add(String.valueOf(numberOfOnsiteWeeks));
					effortList.add(String.valueOf(weeksToStart - numberOfOnsiteWeeks));

					Map<Integer, List<String>> activityMap = new HashMap<>();
					activityMap.put(id, effortList);
					res.setEffortBreakup(activityMap);
				}
			} else {
				for (Integer id  : res.getListOfActivities()) {
					ActivityDetails act = getActityForId(id);
					if (act != null) {
						List<String> effortList = new ArrayList<>();
						double doubleVal = Math.ceil(act.getTotalCalculatedEffort()/5.0);
						int weeksForActivity = (int)doubleVal;
						List<ResourceDetails> resources = getNumberOfRForId(id);
						if (!CollectionUtils.isEmpty(resources)) {
							doubleVal = Math.ceil(act.getTotalCalculatedEffort()/ (5.0*resources.size()));
							weeksForActivity = (int)doubleVal;
														
							int weeklyEffort = (int)((act.getTotalCalculatedEffort()*8.0)/(weeksForActivity *resources.size()));
							effortList.add(String.valueOf(weeklyEffort));
							int onshore = (int)Math.floor((act.getTotalCalculatedOnsiteEffort()*8.0)/(weeklyEffort));
							if (onshore > weeksForActivity) {
								onshore = weeksForActivity;
							}
							effortList.add(String.valueOf(onshore));
							effortList.add(String.valueOf(weeksForActivity - onshore));
							Map<Integer, List<String>> activityMap = res.getEffortBreakup();
							if (activityMap == null ) {
								activityMap = new HashMap<>();
							}
							if (!containsKey(activityMap, id)) {
								activityMap.put(id, effortList);
								res.setEffortBreakup(activityMap);
							}
							
							for (ResourceDetails res2 : resources) {
								if (res2 != res) {
									List<String> effortList2 = new ArrayList<>();
									effortList2.add(String.valueOf(weeklyEffort));
									effortList2.add("0");
									effortList2.add(String.valueOf(weeksForActivity));
									
									Map<Integer, List<String>> activityMap2 = res2.getEffortBreakup();
									if (activityMap2 == null ) {
										activityMap2 = new HashMap<>();
									}
									if (!containsKey(activityMap2, id)) {
										activityMap2.put(id, effortList2);
										res2.setEffortBreakup(activityMap2);
									}
								}
							}
							
							
							
						} else {

							int weeklyEffort = (int)((act.getTotalCalculatedEffort()*8.0)/(weeksForActivity));
							effortList.add(String.valueOf(weeklyEffort));	
							int offshoreWeeks = (int)Math.floor((act.getTotalCalculatedOffshoreEffort()*8.0)/(weeklyEffort));

							effortList.add(String.valueOf(weeksForActivity - offshoreWeeks));
							effortList.add(String.valueOf(offshoreWeeks));

							Map<Integer, List<String>> activityMap = res.getEffortBreakup();
							if (activityMap == null ) {
								activityMap = new HashMap<>();
							}
							activityMap.put(id, effortList);
							res.setEffortBreakup(activityMap);
						}
					}
					
					putRemainingActivities(res);
				}
			}
		}
		
		populateEffortDisForActivity(request);
		
	}
	
	private void populateEffortDisForActivity (HttpServletRequest request) {
		
		Map<String, List<String>> effortDistMap = new HashMap<>();
		int totalGrandEffort = 0;
		int totalGrandOnsite = 0;
		int totalGrandOffsite = 0;
		
		for (ResourceDetails res : resources) {
			if (null != res.getEffortBreakup() && !res.isFullTime()) {
				Set<Integer> activityIds = res.getEffortBreakup().keySet();
				for (Integer id : activityIds) {
					ActivityDetails ac = getActityForId(id);
					
					List<String> efforts = res.getEffortBreakup().get(id);
					List<String> existingEffort = effortDistMap.get(ac.getName());
					if (!CollectionUtils.isEmpty(efforts) && efforts.size() > 2) {
						if (existingEffort == null) {
							existingEffort = new ArrayList<>();
							int totalOnsiteEffort = Integer.parseInt(efforts.get(0)) * Integer.parseInt(efforts.get(1));
							int totalOffsiteEffort = Integer.parseInt(efforts.get(0)) * Integer.parseInt(efforts.get(2));
							
							for (ResourceDetails res2 : resources) {
								if (res2.isFullTime() && null != res2.getEffortBreakup()) {
									List<String> fillTimeEffort = res2.getEffortBreakup().values().iterator().next();
									int fullTimeWeeklyEffort = Integer.parseInt(fillTimeEffort.get(0));
									int fullTimeOnsiteWeeks = Integer.parseInt(fillTimeEffort.get(1));
									int fullTimeOOffsiteWeeks = Integer.parseInt(fillTimeEffort.get(2));
									
									int totalWeeks = Integer.parseInt(efforts.get(1)) + Integer.parseInt(efforts.get(2));
									
									if (fullTimeOnsiteWeeks > 0) {
										totalOnsiteEffort = totalOnsiteEffort + fullTimeWeeklyEffort * (fullTimeOnsiteWeeks < totalWeeks ? fullTimeOnsiteWeeks : totalWeeks);
									} else {
										totalOffsiteEffort = totalOffsiteEffort + fullTimeWeeklyEffort * (fullTimeOOffsiteWeeks < totalWeeks ? fullTimeOOffsiteWeeks : totalWeeks);
									}
									
									
								}
							}
							

							existingEffort.add(String.valueOf(totalOnsiteEffort + totalOffsiteEffort ));
							existingEffort.add(String.valueOf(totalOnsiteEffort));
							existingEffort.add(String.valueOf(totalOffsiteEffort));
							
							if ((totalOnsiteEffort + totalOffsiteEffort) > 0) {
								existingEffort.add(String.valueOf(totalOnsiteEffort*100/(totalOnsiteEffort + totalOffsiteEffort )) + " %");
								existingEffort.add(String.valueOf(totalOffsiteEffort*100/(totalOnsiteEffort + totalOffsiteEffort )) + " %");
							} else {
								existingEffort.add("0 %");
								existingEffort.add("0 %");
							}
							
							effortDistMap.put(ac.getName(), existingEffort);
						} else {
							int totalOnsiteEffort = Integer.parseInt(existingEffort.get(1)) + Integer.parseInt(efforts.get(0)) * Integer.parseInt(efforts.get(1));
							int totalOffsiteEffort = Integer.parseInt(existingEffort.get(2)) + Integer.parseInt(efforts.get(0)) * Integer.parseInt(efforts.get(2));

							
							for (ResourceDetails res2 : resources) {
								if (res2.isFullTime() && null != res2.getEffortBreakup()) {
									List<String> fillTimeEffort = res2.getEffortBreakup().values().iterator().next();
									int fullTimeWeeklyEffort = Integer.parseInt(fillTimeEffort.get(0));
									int fullTimeOnsiteWeeks = Integer.parseInt(fillTimeEffort.get(1));
									int fullTimeOOffsiteWeeks = Integer.parseInt(fillTimeEffort.get(2));
									
									int totalWeeks = Integer.parseInt(efforts.get(1)) + Integer.parseInt(efforts.get(2));
									
									if (fullTimeOnsiteWeeks > 0) {
										totalOnsiteEffort = totalOnsiteEffort + fullTimeWeeklyEffort * (fullTimeOnsiteWeeks < totalWeeks ? fullTimeOnsiteWeeks : totalWeeks);
									} else {
										totalOffsiteEffort = totalOffsiteEffort + fullTimeWeeklyEffort * (fullTimeOOffsiteWeeks < totalWeeks ? fullTimeOOffsiteWeeks : totalWeeks);
									}
									
									
								}
							}
							existingEffort.clear();

							existingEffort.add(String.valueOf(totalOnsiteEffort + totalOffsiteEffort ));
							existingEffort.add(String.valueOf(totalOnsiteEffort));
							existingEffort.add(String.valueOf(totalOffsiteEffort));
							if ((totalOnsiteEffort + totalOffsiteEffort) > 0) {
								existingEffort.add(String.valueOf(totalOnsiteEffort*100/(totalOnsiteEffort + totalOffsiteEffort )) + " %");
								existingEffort.add(String.valueOf(totalOffsiteEffort*100/(totalOnsiteEffort + totalOffsiteEffort )) + " %");
							} else {
								existingEffort.add("0 %");
								existingEffort.add("0 %");
							}
							
						}
					}
				}
			}
		}
		
		for (List<String> efforts : effortDistMap.values()) {
			totalGrandEffort = totalGrandEffort + Integer.parseInt(efforts.get(0));
			totalGrandOnsite = totalGrandOnsite + Integer.parseInt(efforts.get(1));
			totalGrandOffsite = totalGrandOffsite + Integer.parseInt(efforts.get(2));
		}
		
		
		
		List<String> grandTotal = new ArrayList<>();
		grandTotal.add(String.valueOf(totalGrandEffort) + " (hrs) OR " + String.valueOf(totalGrandEffort/8) + " (Days)");
		grandTotal.add(String.valueOf(totalGrandOnsite)  + " (hrs) OR " + String.valueOf(totalGrandOnsite/8) + " (Days)");
		grandTotal.add(String.valueOf(totalGrandOffsite)  + " (hrs) OR " + String.valueOf(totalGrandOffsite/8) + " (Days)");
		grandTotal.add(String.valueOf(String.valueOf(totalGrandOnsite*100/(totalGrandOnsite + totalGrandOffsite )) + " %"));
		grandTotal.add(String.valueOf(String.valueOf(totalGrandOffsite*100/(totalGrandOnsite + totalGrandOffsite )) + " %"));
		request.setAttribute("grandTotal", grandTotal);
		
		request.setAttribute("FinalFooter", effortDistMap);
	}
	
	private boolean containsKey (Map<Integer, List<String>> activityMap, Integer id) {
		boolean contains = false;
		
		for (Integer i : activityMap.keySet()) {
			if (i.equals(id)) {
				contains = true;
				break;
			}
		}
		
		return contains;
	}

	private void putRemainingActivities(ResourceDetails res) {
		for (ActivityDetails actDet: activitiesList) {
			boolean found = false;
			for (Integer i : res.getListOfActivities()) {
				if (actDet.getID().equals(i)) {
					found = true;
					break;
				}
			}
			if (!found) {
				Map<Integer, List<String>> activityMap = res.getEffortBreakup();
				if (activityMap == null ) {
					activityMap = new HashMap<>();
				}
				activityMap.put(actDet.getID(), null);
				res.setEffortBreakup(activityMap);
			}
		}
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	private List<ResourceDetails> getNumberOfRForId (Integer id) {
		List<ResourceDetails> resourcesTemp = new ArrayList<>();
		for (ResourceDetails res : resources) {
			if (!res.isFullTime() && res.getListOfActivities() != null) {
				for (Integer i : res.getListOfActivities()) {
					if (i.equals(id)) {
						resourcesTemp.add(res);
						break;
					}
				}
			}
		}
		return resourcesTemp;
	}
	
	private ActivityDetails getActityForId(Integer id) {
		for (ActivityDetails act : activitiesList) {
			if (act.getID().equals(id)) {
				return act;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param request
	 */
	private void calculateSizing (HttpServletRequest request) {
		int baselineEffort =  0;
		if (StringUtils.isEmpty(request.getParameter("totalEffortApp"))) {
			baselineEffort = 150;
		} else {
			baselineEffort = Integer.parseInt(request.getParameter("totalEffortApp"));
		}

		List<List<String>> activitiesEffortList = new ArrayList<List<String>>();

		int totalSDCEffort = 0;
		int totalESEffort = 0;

		int totalSDCOnsiteEffort = 0;
		int totalESOnsiteEffort = 0;

		int totalSDCOffshoreEffort = 0;
		int totalESOffshoreEffort = 0;

		Map<String, ActivityDetails> activityMap = new HashMap<>();

		for (ActivityDetails activity: activitiesList) {

			activityMap.put(activity.getName(), activity);

			List<String> item = new ArrayList<String>();
			item.add(activity.getName());
			int sdcEffort = 0;
			int esShare = 0;
			int sdcOnsiteEffort = 0;
			int esOnsiteEffort = 0;
			if (activity.getSdcShare() > 0) {
				sdcEffort = baselineEffort*activity.getSdcShare()/100;
				totalSDCEffort = totalSDCEffort + sdcEffort;

				item.add(String.valueOf(sdcEffort));
				if (activity.getOnsiteShare() > 0) {
					sdcOnsiteEffort = sdcEffort * activity.getOnsiteShare()/100;
					totalSDCOnsiteEffort = totalSDCOnsiteEffort + sdcOnsiteEffort;
					item.add(String.valueOf(sdcOnsiteEffort));
					item.add(String.valueOf(sdcEffort-sdcOnsiteEffort));
				} else {
					item.add("");
					item.add(String.valueOf(sdcEffort));
				}
			} else {
				item.add("");
				item.add("");
				item.add("");
			}
			if (activity.getEsShare() > 0) {
				esShare = baselineEffort*activity.getEsShare()/100;
				totalESEffort = totalESEffort + esShare;
				item.add(String.valueOf(esShare));
				if (activity.getOnsiteShare() > 0) {
					esOnsiteEffort = esShare * activity.getOnsiteShare()/100;
					totalESOnsiteEffort = totalESOnsiteEffort + esOnsiteEffort;
					item.add(String.valueOf(esOnsiteEffort));
					item.add(String.valueOf(esShare-esOnsiteEffort));
				} else {
					item.add("");
					item.add(String.valueOf(esShare));
				}
			} else {
				item.add("");
				item.add("");
				item.add("");
			}			
			activitiesEffortList.add(item);
			activity.setTotalCalculatedEffort(sdcEffort + esShare);
			activity.setTotalCalculatedOnsiteEffort(sdcOnsiteEffort + esOnsiteEffort);
			activity.setTotalCalculatedOffshoreEffort(activity.getTotalCalculatedEffort() - activity.getTotalCalculatedOnsiteEffort());
		}

		totalSDCOffshoreEffort = totalSDCEffort - totalSDCOnsiteEffort;
		totalESOffshoreEffort = totalESEffort - totalESOnsiteEffort;

		List<String> item = new ArrayList<String>();
		item.add(String.valueOf(totalSDCEffort));
		item.add(String.valueOf(totalSDCOnsiteEffort));
		item.add(String.valueOf(totalSDCOffshoreEffort));
		
		if (totalESEffort > 0) {		
			item.add(String.valueOf(totalESEffort));
			item.add(String.valueOf(totalESOnsiteEffort));
			item.add(String.valueOf(totalESOffshoreEffort));
		} else {
			request.setAttribute("hideES", true);
		}


		request.setAttribute("activitiesEffortList", activitiesEffortList);
		request.setAttribute("footerList", item);

		request.setAttribute("totalEffortApp", baselineEffort);

		populateTimelineMap(activitiesEffortList, request, baselineEffort, activityMap);
	}

	private void populateTimelineMap (List<List<String>> activitiesEffortList, HttpServletRequest request, int baselineEffort, Map<String, ActivityDetails> activityMap) {
		int weeksToStart = 0;
		int weeksForActivity = 0;

		List<List<String>> timelineDetails = new ArrayList<List<String>>();

		for (List<String> activityDetails : activitiesEffortList) {
			if (activityDetails.size() > 1) {
				String activityName = activityDetails.get(0);
				ActivityDetails details = activityMap.get(activityName);
				if (null != details && details.getIndexOfEffort() > 0 && activityDetails.size() >=details.getIndexOfEffort()) {

					String days = activityDetails.get(details.getIndexOfEffort());
					if (!StringUtils.isEmpty(days)) {
						
						int divide = 1;
						List<ResourceDetails> resourcesLst = getNumberOfRForId(details.getID());
						if (!CollectionUtils.isEmpty(resourcesLst)) {
							divide = resourcesLst.size();
						}
						
						double doubleVal = Math.ceil(Integer.parseInt(days)/(5.0*divide));
						weeksForActivity = (int)doubleVal;

						List<String> timeline = new ArrayList<>();
						timeline.add(activityName);

						weeksToStart = weeksToStart - details.getOverlap();

						timeline.add(String.valueOf(weeksToStart));
						timeline.add(String.valueOf(weeksForActivity));
						timeline.add(days + " Days");
						timeline.add(String.valueOf(Integer.parseInt(days)*8));
						timelineDetails.add(timeline);

						//BigDecimal bigD = new BigDecimal(Integer.parseInt(days)/5.0);
						//bigD.setScale(0, BigDecimal.ROUND_HALF_UP);
						weeksToStart = weeksToStart + weeksForActivity;//bigD.intValue();
					}
				}
			}
		}

		//BigDecimal bigD = new BigDecimal(baselineEffort/5.0);
		//bigD.setScale(0, BigDecimal.ROUND_HALF_UP);

		request.setAttribute("totalWeeks", weeksToStart);
		request.setAttribute("timelineDetails", timelineDetails);
		
		calculateResourceWithEffort(weeksToStart, request);
		


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
