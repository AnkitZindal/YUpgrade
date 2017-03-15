<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Hybris Upgrade Customer Questionaire Form</title>
        <link rel="stylesheet" href="css/normalize.css">
        <link href='http://fonts.googleapis.com/css?family=Nunito:400,300' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/main.css">
    </head>
    <body>
      <form style="max-width: 900px">
      	<img alt="SAP" src="images/sap-logo.png">
        <h1>Hybris Upgrade Customer Questionaire</h1>
      </form>
      <form action="CalculateUpgradeSize" method="post" enctype="multipart/form-data" style="max-width: 900px">
      		<label><b>Upload Customer Details File</b></label>
      		<input type="file" name="file" /><br/>
		    <input type="submit" value="Submit" />
		
      </form>

      <form action="CalculateUpgradeSize" method="post" style="max-width: 900px">
        <label><b>Customer Details</b></label>
        
        <fieldset>
          
          <label for="name">Customer Name:</label>
          <input type="text" id="name" name="name" value="${infoMap['customer']}">
          
          <label for="currentVersion">Current Hybris Version: (${infoMap['currentVersion']})</label> 
          <select id="currentVersion" name="currentVersion">          
            <option value="pre5" <c:if test="${infoMap['q1'] == '1'}"> selected="selected"</c:if>>Pre 5.0</option>
            <option value="5to55" <c:if test="${infoMap['q1'] == '2'}"> selected="selected"</c:if>>5.0 - 5.5.1</option>
            <option value="55+" <c:if test="${infoMap['q1'] == '3'}"> selected="selected"</c:if>>5.5.1+</option>
          </select>
          
          <label for="q1">Do you have requirements to leverage the new accelerator or new features within the upgrade scope?</label>
          <select id="q1" name="q1">          
            <option value="no">No</option>
            <option value="yes">Yes</option>
          </select>
          
          <label for="q2">Is solution already upgraded in past?</label>
          <select id="q2" name="q2">          
            <option value="no" >No</option>
            <option value="yes" <c:if test="${infoMap['q2'] == 'true'}"> selected="selected"</c:if> >Yes</option>
          </select>
          
          <label for="q3">Database</label>
          <select id="q3" name="q3">          
            <option value="HSQLDB" <c:if test="${infoMap['db'] == 'HSQLDB'}"> selected="selected"</c:if>>HSQLDB</option>
            <option value="MySQL" <c:if test="${infoMap['db'] == 'MySQL'}"> selected="selected"</c:if>>MySQL</option>
            <option value="Oracle" <c:if test="${infoMap['db'] == 'Oracle'}"> selected="selected"</c:if>>Oracle</option>
            <option value="Microsoft SQLServer" <c:if test="${infoMap['db'] == 'Microsoft SQLServer'}"> selected="selected"</c:if>>Microsoft SQLServer</option>
            <option value="SAP HANA" <c:if test="${infoMap['db'] == 'SAP HANA'}"> selected="selected"</c:if>>SAP HANA</option>
          </select>
          
          <c:if test="${showDetails}">
          <label><b>All extensions Used: </b></label><span style="word-wrap: break-word;">${infoMap['extensions']}</span>
          <br><br>
          <label><b>Custom Extensions Build Information:</b></label>
         	<table>
         		<c:forEach items="${extVersionap}" var="mapEntry">
         			<tr>
         				<td>${mapEntry.key}</td>
         				<td>${mapEntry.value}</td>
         			</tr>
         		</c:forEach>        	
         	</table>
         	<br>
         	<label><b>Deprecated Extensions in Use: </b></label><span style="word-wrap: break-word;">${depExtnList}</span>
          	<br>
          </c:if>
          
        </fieldset>
        
        
        <button type="submit">Calculate Sizing</button>
      </form>
      
    </body>
</html>