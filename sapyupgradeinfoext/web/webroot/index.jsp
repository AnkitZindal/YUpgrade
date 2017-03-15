<%@page import="de.hybris.platform.core.Registry" %>
<%@page import="de.hybris.platform.jalo.extension.ExtensionManager" %>
<html>
	<body>
		<h1>sapyupgradeinfoext</h1>
		Welcome to my extension.
	</body>

<%
	ExtensionManager em = Registry.getCurrentTenant().getJaloConnection().getExtensionManager();

	out.println(em.getExtensions().get(0).getName());
	out.println(em.getExtensions().get(0).getCreatorDescription());

	out.println(em.getExtensions().get(0).getCreatorName());
	out.println(em.getExtensions().get(0).getCreatorParameterNames());
	out.println(em.getExtensions().get(0).getAttributeMap());
	out.println(em.getExtensions().get(0).getTransientObjectMap());

%>
</html>

