<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <head>
  		
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Hybris Upgrade Edit Effort Distribution</title>
        <link rel="stylesheet" href="css/normalize.css">
        <link href='http://fonts.googleapis.com/css?family=Nunito:400,300' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/main.css">
    </head>
    <body>

      
    <form action="EditEffortDistribution" method="post" style="max-width: 1250px" id="EditEffortDistribution"> 
       <img alt="SAP" src="images/sap-logo.png" onclick="goHome();">
        
          <h2>Edit Effort Distribution</h2>
          <table border="1">
          	<thead>
          		<tr style="max-height: 20px">
          			<td style="max-width: 450px; text-align: center;">Activity Name</td>
          			<td style="max-width: 150px; text-align: center;">SDC Effort %</td>
          			<td style="max-width: 150px; text-align: center;">ES Effort %</td>
          			<td style="max-width: 150px; text-align: center;">Onsite Effort %</td>
          			<td style="max-width: 150px; text-align: center;">Index of Total Effort<br>(1 for SDC, 4 for ES, 0 to exclude from timeline)</td>
          			<td style="max-width: 150px; text-align: center;">Overlaping with previous task <br>(in weeks)</td>
          		</tr>
          	</thead>
          
          <tbody>
          	<c:forEach items="${activitiesList}" var="activity">
          		<input type="hidden" name="id" value="${activity.ID}">
          		<tr style="max-height: 20px">
          			<td>${activity.name}</td>
          			<td><input type="text" style="width: 150px; text-align: center; margin-bottom: 2px;padding: 2px" id="sdc${activity.ID}" name="sdc${activity.ID}" value="${activity.sdcShare}"></td>
          			<td><input type="text" style="width: 150px; text-align: center; margin-bottom: 2px;padding: 2px"" id="es${activity.ID}" name="es${activity.ID}" value="${activity.esShare}"></td>
          			<td><input type="text" style="width: 150px; text-align: center; margin-bottom: 2px;padding: 2px"" id="onsite${activity.ID}" name="onsite${activity.ID}" value="${activity.onsiteShare}"></td>
          			<td><input type="text" style="width: 150px; text-align: center; margin-bottom: 2px;padding: 2px"" id="index${activity.ID}" name="index${activity.ID}" value="${activity.indexOfEffort}"></td>
          			<td><input type="text" style="width: 150px; text-align: center; margin-bottom: 2px;padding: 2px"" id="overlap${activity.ID}" name="overlap${activity.ID}" value="${activity.overlap}"></td>
          		</tr>
          	</c:forEach>
          	</tbody>
          </table>
       <button type="submit">Submit</button>
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