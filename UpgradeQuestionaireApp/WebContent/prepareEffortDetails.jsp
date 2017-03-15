<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <html>
  <head>
  		
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Hybris Upgrade Effort Details</title>
        <link rel="stylesheet" href="css/normalize.css">
        <link href='http://fonts.googleapis.com/css?family=Nunito:400,300' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/main.css">
        
        <script src="js/jquery-3.1.1.min.js"></script>
		<script src="js/FileSaver.min.js"></script>
		<script src="js/tableexport.min.js"></script>
    </head>
     <style type="text/css"> 
     	tr:nth-child(even) { background: hsla(50, 50%, 80%, 0.7); }
     	
     	tr:nth-child(odd) { background: hsla(40, 40%, 60%, 0.5); }
     </style>
    
    <body>

      <form action="CalculateEffortDetails" method="post" style="max-width: 900px" id="CalculateEffortDetailsForm">
      <img alt="SAP" src="images/sap-logo.png" onclick="goHome();">
        <h1>Hybris Upgrade Effort Details</h1>
        
        
          
          <label for="totalEffortApp">Base Line Effort:</label>
          <input type="text" id="totalEffortApp" name="totalEffortApp" value="${totalEffortApp}">
          
          <label for="numberOfDev">Number of Developers:</label>
          <input type="text" id="numberOfDev" name="numberOfDev" value="${numberOfDev}">
          <table style="border: 1px;">
	          <thead>
		          <tr bgcolor="orange" style="text-align: center; font-weight: bold">
		          	<td>Activities</td>
		          	<td>SDC Total Effort</td>
		          	<td>SDC Onsite Effort</td>
		          	<td>SDC Offshore Effort</td>
		          	<c:if test="${!hideES}">
			          	<td>ES Total Effort</td>
			          	<td>ES Onsite Effort</td>
			          	<td>ES Offshore Effort</td>
		          	</c:if>
		          </tr>
	          </thead>
	          <tfoot>
		          <tr bgcolor="orange" style="text-align: center; font-weight: bold">
		          	<td>Total</td>		  
		          	<c:forEach items="${footerList}" var="footer">
		          		<td><c:out value="${footer}"></c:out> </td>		          	
		          	</c:forEach>
		          </tr>
	          </tfoot>
	          <tbody>
	          <c:forEach items="${activitiesEffortList}" var="effortDetails">
	          
	          <tr style="background: hsla(50, 50%, 80%, 0.7);">
	          	<c:forEach items="${effortDetails}" var="effortDetail">
	          		<td>
	          			<c:out value="${effortDetail}"></c:out>
	          		</td>
	          	</c:forEach>
	          </tr>
	          </c:forEach>
	          </tbody>
          </table>
        <br>
        
        	<label>
	        	<ul>
	        		<li>All effort in number of days</li>
	        		<li>Assuming only one SDC Technical Consultant would be working on upgrade</li>
	        		<li>SDC PM Activities would be parallel so no impact would be on overall time lines</li>
	        	</ul>
        	</label>
        <br>
        <button type="submit" style="width: 400px">Re-Calculate Sizing</button> &nbsp; &nbsp; <button type="button" onclick="goBack();" style="width: 400px">Go Back</button>
        <br>
        
        
      </form>
      <div align="center" >
      <h1>Hybris Upgrade Timelines</h1>
      <table border="1" style="background-color: #f4f7f8">
      <tr style="font-weight: bold;">
      <td>Activities</td>
      <c:forEach begin="0" end="${totalWeeks-1}" varStatus="loop">
      		
      		<td>
      		<c:out value="W${loop.index + 1}"></c:out>
      		</td>
      
      </c:forEach>
      </tr>
      <c:forEach items="${timelineDetails}" var="timelineDetailsLst">
      	<tr>
      		<td style="min-width: 300px;"><c:out value="${timelineDetailsLst[0]}"></c:out></td>
      		<c:if test="${timelineDetailsLst[1] > 0}">
      			<td colspan="${timelineDetailsLst[1]}"></td>
      		</c:if>
      		<td colspan="${timelineDetailsLst[2]}" bgcolor="blue" align="center"><font color="white">${timelineDetailsLst[3]}</font> </td>
      	</tr>
      </c:forEach>
      </table>
      </div>
      
      <br><br><br>
      
       <div align="center" >
       <h1>Hybris Upgrade Resource Loading</h1>
      <table border="1" style="background-color: #f4f7f8">
      <tr>
      		<td></td>
	      	<c:forEach items="${timelineDetails}" var="timelineDetailsLst">	      		
	      		<td colspan="${timelineDetailsLst[2]}" align="center"><c:out value="${timelineDetailsLst[0]}"/></td>
	      	</c:forEach>
      </tr>
      <tr style="font-weight: bold;">
      <td></td>
      <c:forEach begin="0" end="${totalWeeks-1}" varStatus="loop">     		
      		<td>
      		<c:out value="W${loop.index + 1}"></c:out>
      		</td>      
      </c:forEach>
      </tr>
      
      <c:forEach items="${resources}" var="resource">   
	      <tr style="font-weight: bold;">
	        <td style="min-width: 300px">${resource.name}</td>
	        <c:choose>
	        	<c:when test="${resource.outofFactory}">
	        		<c:forEach begin="0" end="${totalWeeks-1}" varStatus="loop">     		
			      		<td/>
			      		      
			      </c:forEach>
	        	</c:when>
	        	<c:otherwise>
	        		<c:choose>
	        			<c:when test="${resource.fullTime}">
	        				<c:forEach items="${resource.effortBreakup}" var="activityInfo">
	        					<c:set var="affortList" value="${activityInfo.value}"/>
	        					<c:if test="${affortList[1] > 0}">
	        						<c:forEach begin="1" end="${affortList[1]}" var="activity">
	        							<td bgcolor="green" align="center"><font color="white">${affortList[0]}</font> </td>
	        						</c:forEach>
	        					</c:if>
	        					<c:if test="${affortList[2] > 0}">
	        						<c:forEach begin="1" end="${affortList[2]}" var="activity">
	        							<td bgcolor="orange" align="center"><font color="white">${affortList[0]}</font> </td>
	        						</c:forEach>
	        					</c:if>
	        				</c:forEach>
	        			</c:when>
	        			<c:otherwise>
	        				<c:forEach items="${resource.effortBreakup}" var="activity" >
	        					<c:set var="affortList" value="${activity.value}"/>
	        					<c:set var="timelineDetailsLst" value="${timelineDetails[activity.key -1]}"></c:set>
	        					<c:choose>
		        					<c:when test="${affortList eq null}">
		        						<td colspan="${timelineDetailsLst[2]}" align="center"/>
		        					</c:when>
		        					<c:otherwise>
		        						<c:if test="${affortList[1] > 0}">
			        						<c:forEach begin="1" end="${affortList[1]}" var="activity">
			        							<td bgcolor="green" align="center"><font color="white">${affortList[0]}</font> </td>
			        						</c:forEach>
			        					</c:if>
			        					<c:if test="${affortList[2] > 0}">
			        						<c:forEach begin="1" end="${affortList[2]}" var="activity">
			        							<td bgcolor="orange" align="center"><font color="white">${affortList[0]}</font> </td>
			        						</c:forEach>
			        					</c:if>
		        					</c:otherwise>
	        					</c:choose>
	        				</c:forEach>
	        					        			
	        			</c:otherwise>
	        		</c:choose>
	        	
	        	
	        	
	        		
	        	</c:otherwise>
	        </c:choose>
	      </tr>
       </c:forEach>
      <tr bgcolor="grey" style="min-height: 10px">
      	<td bgcolor="grey" colspan="${totalWeeks+1}" style="min-height:50px;border: none;font-weight: bold;font-size: 25px" align="center"><font color="white"> Total Summary</font></td>	      	
      </tr>
      <tr>
      	<td>Total Effort</td>
	      	<c:forEach items="${timelineDetails}" var="timelineDetailsLst">	  
	      		<c:set var="mapKey" value="${timelineDetailsLst[0]}"/>
	      		<c:set var="effortList" value="${FinalFooter[mapKey]}"/>    		
	      		<td colspan="${timelineDetailsLst[2]}" align="center"><c:out value="${effortList[0]}"/></td>
	      	</c:forEach>
      </tr>
      <tr>
      	<td>Onsite Effort</td>
	      	<c:forEach items="${timelineDetails}" var="timelineDetailsLst">	  
	      		<c:set var="mapKey" value="${timelineDetailsLst[0]}"/>  
	      		<c:set var="effortList" value="${FinalFooter[mapKey]}"/>   		
	      		<td colspan="${timelineDetailsLst[2]}" align="center"><c:out value="${effortList[1]}"/></td>
	      	</c:forEach>
      </tr>
      <tr>
      	<td>Offshore Effort</td>
	      	<c:forEach items="${timelineDetails}" var="timelineDetailsLst">	  
	      		<c:set var="mapKey" value="${timelineDetailsLst[0]}"/>  
	      		<c:set var="effortList" value="${FinalFooter[mapKey]}"/>   		
	      		<td colspan="${timelineDetailsLst[2]}" align="center"><c:out value="${effortList[2]}"/></td>
	      	</c:forEach>
      </tr>
      <tr>
      	<td>Onsite % for Phase</td>
	      	<c:forEach items="${timelineDetails}" var="timelineDetailsLst">	  
	      		<c:set var="mapKey" value="${timelineDetailsLst[0]}"/> 
	      		<c:set var="effortList" value="${FinalFooter[mapKey]}"/>    		
	      		<td colspan="${timelineDetailsLst[2]}" align="center"><c:out value="${effortList[3]}"/></td>
	      	</c:forEach>
      </tr>
      <tr>
      	<td>Offshore % for Phase</td>
	      	<c:forEach items="${timelineDetails}" var="timelineDetailsLst">	  
	      		<c:set var="mapKey" value="${timelineDetailsLst[0]}"/>    
	      		<c:set var="effortList" value="${FinalFooter[mapKey]}"/> 		
	      		<td colspan="${timelineDetailsLst[2]}" align="center"><c:out value="${effortList[4]}"/></td>
	      	</c:forEach>
      </tr>
      
      <tr bgcolor="grey" style="min-height: 10px">
      	<td bgcolor="grey" colspan="${totalWeeks+1}" style="min-height:50px;border: none;font-weight: bold;font-size: 25px" align="center"><font color="white">Grand Total Summary</font></td>	      	
      </tr>
      <tr>
      	<td bgcolor="white" colspan="${totalWeeks+1}" style="min-height:50px;border: none;font-weight: bold;font-size: 18px" align="center"><font color="black">
      		Total Effort: ${grandTotal[0]}
      		<br>
      		Total Onsite Effort: ${grandTotal[1]}
      		<br>
      		Total Offshore Effort: ${grandTotal[2]}
      		<br>
      		Onsite Effort: ${grandTotal[3]}
      		<br>
      		Offshore Effort: ${grandTotal[4]}
      	</font></td>
      </tr>
      </table>
      
      	<br>
        	<button type="button" onclick="exportExcel();" style="width: 100%">Export Data</button>
        <br>
      </div>
      
      
    </body>
</html>
<script>
	function goBack() {
		window.history.back();
	}
	
	function exportExcel() {
		document.getElementById("CalculateEffortDetailsForm").action = "CalculateEffortDetails?downloadexcel=1";
        document.getElementById("CalculateEffortDetailsForm").submit();
        document.getElementById("CalculateEffortDetailsForm").action = "CalculateEffortDetails";
	}
	
	function goHome() {
		window.location.href="/UpgradeQuestionaireApp";
	}
	
</script>