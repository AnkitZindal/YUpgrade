<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <head>
  		
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Hybris Upgrade Customer Questionaire Result</title>
        <link rel="stylesheet" href="css/normalize.css">
        <link href='http://fonts.googleapis.com/css?family=Nunito:400,300' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/main.css">
    </head>
    <body>

      
    <form action="CalculateEffortDetails" method="post" style="max-width: 800px" id="CalculateEffortDetails"> 
       <img alt="SAP" src="images/sap-logo.png" onclick="goHome();">
        <h1>Estimated T-shirt size<br>(${size})</h1>
        
        <jsp:include page="/sizingDef${size}.jsp"></jsp:include>
        
        <fieldset>
        	<br/>
         		<h2>Roles and Responsibilities:</h2><a href="roleResp.jsp">Click Here</a> to check roles and responsibilities
        	<br/>
          
          <h2>High Level Technical Challenges:</h2>
          <table border="1" style="width: 100%">
          	<thead>
          		<tr>
          			<td></td>
          			<td align="center">Current Platform</td>
          			<td align="center">Upgraded Platform</td>
          		</tr>
          	</thead>
          	<tbody>
          		
          		<c:forEach var="currentItem" items="${current}">
          		<tr>
          			<td>${currentItem.key}</td>
          			<td align="center">${currentItem.value}</td>
          			<td align="center">${latest[currentItem.key]}</td>
          		</tr>
          		</c:forEach>
          		
          	</tbody>          
          </table>
          
          <br>
          <h2>Customer Details:</h2>
          <label for="name">Customer Name:</label>
          <input type="text" id="name" name="name" disabled="disabled" value="${name}">
          
          
          <label for="currentVersion">Current Hybris Version:</label>
          <input type="text" id="currentVersion" name="currentVersion" disabled="disabled" value="${currentVersion}">
          
          
          <label for="q1">Do you have requirements to leverage the new accelerator or new features within the upgrade scope?</label>
          <input type="text" id="q1" name="q1" disabled="disabled" value="${q1}">
          
          
          <label for="q2">Is solution already upgraded in past?</label>
          <input type="text" id="q2" name="q2" disabled="disabled" value="${q2}">
          
          <label for="q3">Database</label>
          <input type="text" id="q3" name="q3" disabled="disabled" value="${q3}">
          
        </fieldset>
        
        <button type="button" onclick="goBack();">Go Back</button>
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