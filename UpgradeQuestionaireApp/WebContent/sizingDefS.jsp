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
	        				<li>This is a <b>standard upgrade</b></li>
							<li>Recent and upgrade-friendly Hybris version</li>
							<li>The customization complexity and code quality assumed OK</li> 
							<li>Will be followed by a Standard Upgrade Analysis service</li>
							<li>Only the new features that require no development effort will be enabled with this upgrade</li>
							<li>End-to-end regression testing will be performed by the Customer</li>
							<li>Deployments and upgrade test environment will be arranged by the Customer</li>	        			
	        			</ul>
	        		</td>
	        		<td><a href="#" onclick="document.getElementById('CalculateEffortDetails').submit()">50-150 days</a></td>
	        		 <input type="hidden" id="totalEffortApp" name="totalEffortApp" value="130">
        		</tr>
        	
        	</tbody>
        
        </table>
        
        
        
        
    