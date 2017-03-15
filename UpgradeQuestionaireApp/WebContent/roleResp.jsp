<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <head>
  		
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Hybris Upgrade Roles and Responsibilities</title>
        <link rel="stylesheet" href="css/normalize.css">
        <link href='http://fonts.googleapis.com/css?family=Nunito:400,300' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/main.css">
    </head>
     <style type="text/css">    
/*General styles*/
body
{
	margin: 0;
	padding: 0;
}

#main
{
	background: white;
	-moz-border-radius: 8px;
	-webkit-border-radius: 8px;
	-moz-box-shadow: 0 2px 2px #9c9c9c;
	-webkit-box-shadow: 0 2px 2px #9c9c9c;
}

/*Features table------------------------------------------------------------*/
.features-table
{
  width: 100%;
  margin: 2 auto;
  border-collapse: separate;
  border-spacing: 0;
  text-shadow: 0 1px 0 #fff;
  color: #2a2a2a;
  background: #fafafa;  
  background-image: -moz-linear-gradient(top, #fff, #eaeaea, #fff); /* Firefox 3.6 */
  background-image: -webkit-gradient(linear,center bottom,center top,from(#fff),color-stop(0.5, #eaeaea),to(#fff)); 
}

.features-table td
{
  height: 50px;
  line-height: 50px;
  padding: 10 10px;
  border-bottom: 1px solid #cdcdcd;
  box-shadow: 0 1px 0 white;
  -moz-box-shadow: 0 1px 0 white;
  -webkit-box-shadow: 0 1px 0 white;
  text-align: center;
  margin: 2px;
}

/*Body*/
.features-table tbody td
{
  text-align: center;
  font: normal 14px Verdana, Arial, Helvetica;
  width: 150px;
}

.features-table tbody td:first-child
{
  width: auto;
  text-align: left;
}

.features-table td:nth-child(2), .features-table td:nth-child(3)
{
  background: #efefef;
  background: rgba(144,144,144,0.15);
  border-right: 1px solid white;
}


.features-table td:nth-child(4)
{
  background: #e7f3d4;  
  background: rgba(184,243,85,0.3);
}

/*Header*/
.features-table thead td
{
  font: bold 1.3em 'trebuchet MS', 'Lucida Sans', Arial;  
  -moz-border-radius-topright: 10px;
  -moz-border-radius-topleft: 10px; 
  border-top-right-radius: 10px;
  border-top-left-radius: 10px;
  border-top: 1px solid #eaeaea; 
}

.features-table thead td:first-child
{
  border-top: none;
}

/*Footer*/
.features-table tfoot td
{
  font: bold 1.4em Georgia;  
  -moz-border-radius-bottomright: 10px;
  -moz-border-radius-bottomleft: 10px; 
  border-bottom-right-radius: 10px;
  border-bottom-left-radius: 10px;
  border-bottom: 1px solid #dadada;
}

.features-table tfoot td:first-child
{
  border-bottom: none;
}
    </style> 
    
    
    <body>
	<form action="CalculateUpgradeSize" method="post" style="max-width: 800px"> 
        <img alt="SAP" src="images/sap-logo.png" onclick="goHome();">
        <h1>Roles and Responsibilities</h1>
      
      
    <div id="main">
		
		<table class="features-table">
				<col width="500">
  				<col width="150">
  				<col width="150">
				<thead>
					<tr>
						<td></td>
						<td>SAP SDC</td>
						<td>Customer</td>
						<!-- <td>Enterprise</td> -->
					</tr>
				</thead>
				
				<tfoot>
					<tr>
						<td colspan="3"><button type="button" onclick="goBack();">Go Back</button></td>						
					</tr>
				</tfoot>	 			
				<tbody>
					<tr>
						<td>Deployments of the upgraded application on all testing stages and production (Customer environments)</td>
						<td></td>
						<td><img src="images/check.png" width="16" height="16" alt="check"></td>								
					</tr>
					<tr>
						<td>Updates and adjustments of all third-party components and integrations in the Customer environments (including Java upgrade to version 8 and Solr upgrade on all Solr nodes)</td>
						<td></td>
						<td><img src="images/check.png" width="16" height="16" alt="check"></td>								
					</tr>
					<tr>
						<td>Solr configuration of SAP Hybris Commerce (application deployment package)</td>
						<td><img src="images/check.png" width="16" height="16" alt="check"></td>
						<td></td>								
					</tr>
					<tr>
						<td>Upgrade of SAP Hybris Commerce version and migration of all relevant Customer custom code and data, preparation of an updated deployment package</td>
						<td><img src="images/check.png" width="16" height="16" alt="check"></td>
						<td></td>								
					</tr>
					<tr>
						<td>Smoke testing according to the common use cases</td>
						<td><img src="images/check.png" width="16" height="16" alt="check"></td>
						<td></td>								
					</tr>
					<tr>
						<td>End-to-end testing of all functionality and configuration of the application against regression issues and reporting those as upgrade defects via agreed tool</td>
						<td></td>
						<td><img src="images/check.png" width="16" height="16" alt="check"></td>								
					</tr>
					<tr>
						<td>Analyzing defects towards qualification as upgrade or non-upgrade related</td>
						<td><img src="images/check.png" width="16" height="16" alt="check"></td>
						<td><img src="images/check.png" width="16" height="16" alt="check"></td>								
					</tr>
					<tr>
						<td>Resolving upgrade defects</td>
						<td><img src="images/check.png" width="16" height="16" alt="check"></td>
						<td></td>								
					</tr>
					<tr>
						<td>Resolving non-upgrade-related issues identified in the upgraded application merged with ongoing development changes</td>
						<td></td>
						<td><img src="images/check.png" width="16" height="16" alt="check"></td>								
					</tr><tr>
						<td>Merging of the code changes implemented in parallel to the upgrade activities into the upgrade branch and resolving code conflicts and other merging issues</td>
						<td><img src="images/check.png" width="16" height="16" alt="check"></td>
						<td></td>								
					</tr><tr>
						<td>Merging of the code changes implemented in the upgrade activities into other branches including main one (trunk or master) and resolving code conflicts and other merging issues</td>
						<td></td>
						<td><img src="images/check.png" width="16" height="16" alt="check"></td>								
					</tr>
					<tr>
						<td>Management over Customer environments including configuration of components and access rights as required for successful execution of the upgrade project</td>
						<td></td>
						<td><img src="images/check.png" width="16" height="16" alt="check"></td>								
					</tr>
					<tr>
						<td>Management over the automation tools (Continuous Integration) and appropriate changes to the configuration of the tools in order to support the upgrade project. This includes resolving the issues with automated processes (build, testing, deployment) and refactoring of the automated tests if they are no longer applicable/valid for the upgraded application</td>
						<td></td>
						<td><img src="images/check.png" width="16" height="16" alt="check"></td>								
					</tr>
					<tr>
						<td>Resolving issues raised by automated tests if the tests are applicable/valid for the upgraded application</td>
						<td><img src="images/check.png" width="16" height="16" alt="check"></td>
						<td></td>								
					</tr>
					<tr>
						<td>Efficient communication throughout the project enabling good alignment on the current activities and timely escalation of issues impacting the project performance
						<ul>
							<li>Regular status meetings</li>
							<li>Regular performance reporting on the assigned tasks</li>
						</ul>
						</td>
						<td><img src="images/check.png" width="16" height="16" alt="check"></td>
						<td><img src="images/check.png" width="16" height="16" alt="check"></td>								
					</tr>
				</tbody>
		</table>
		
	</div>
</form>
        
      
    </body>
</html>

<script>
	function goBack() {
		window.history.back();
	}
	function goHome() {
		window.location.href="/UpgradeQuestionaireApp";
	}
</script>