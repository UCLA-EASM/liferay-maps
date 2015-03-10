<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="javax.portlet.PortletPreferences" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%@ page import = "com.liferay.portlet.documentlibrary.model.DLContent" %> 
<%@ page import = "com.liferay.portlet.documentlibrary.service.DLContentLocalServiceUtil" %>

 <portlet:defineObjects />

This is the <b>First Lines</b> portlet in View mode.

<h1>My Library</h1>

<% 
final int fileCount = DLContentLocalServiceUtil.getDLContentsCount();
List<DLContent> documents = DLContentLocalServiceUtil.getDLContents(0, fileCount);
System.out.println(documents.get(0));
%>

<portlet:renderURL var="firstLinesURL">
	    <portlet:param name="mvcPath" value="/html/firstlines/results.jsp"></portlet:param>
</portlet:renderURL>
 
<aui:form action="<%= firstLinesURL %>" method="post">
	<% for ( int i = 0; i < documents.size(); i++ ) {
		%>
	<aui:input type="checkbox" id="<%= Long.toString(documents.get(i).getPrimaryKey()) %>" name="hello"> </aui:input>
	<% 
	}
	%>
	<aui:input type="submit" onClick="<%= firstLinesURL.toString() %>" name=""  value="Get First Lines"></aui:input>
</aui:form>
