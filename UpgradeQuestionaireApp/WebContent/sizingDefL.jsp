<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        
        <table border="1">
        	<thead>
        		<tr>
	        		<td>T Shirt Size</td>
	        		<td>Assumptions</td>
	        		<td>Upgrade estimation(assuming execution by SAP SDC)</td>
        		</tr>
        	</thead>
        	<tbody>
        		<tr>
	        		<td style="text-align: center;">${size}</td>
	        		<td>
	        			<ul>
	        				<li>This is a <b>extended upgrade</b></li>
							<li>Possible non-upgrade-friendly version</li>
							<li>Expected less upgrade than development work</li> 
							<li>Strong requirements for reworking Accelerator/implementing new features within the upgrade</li>
							<li>Will be followed by a detailed Extended Upgrade Analysis</li>
							<li>Split of responsibilities will be defined in the assessment</li>
							<li>Deployments and upgrade test environment will be arranged by the Customer</li>	        			
	        			</ul>
	        		</td>
	        		<td><a href="#" onclick="document.getElementById('CalculateEffortDetails').submit()">200+ days</a></td>
	        		<input type="hidden" id="totalEffortApp" name="totalEffortApp" value="250">
        		</tr>
        	
        	</tbody>
        
        </table>
        
        
        
        
    