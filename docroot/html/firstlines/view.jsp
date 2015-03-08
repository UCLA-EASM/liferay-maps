<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="javax.portlet.PortletPreferences" %>
<%@ page import="java.util.List" %>
<%@ page import = "com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil" %>
<%@ page import = "com.liferay.portlet.documentlibrary.model.DLFileEntry" %> 
<%-- 
<%@ page import = "com.liferay.portlet.documentlibrary.model.DLContent" %> 
<%@ page import = "com.liferay.portlet.documentlibrary.service.DLContentLocalServiceUtil" %>
--%>
 <portlet:defineObjects />

This is the <b>First Lines</b> portlet in View mode.

<h1>My Library</h1>

<% 
List<DLFileEntry> documents = DLFileEntryLocalServiceUtil.getDLFileEntries(0, 10);
%>
<table>
<%
    for ( int i = 0; i < documents.size(); i++ ) {
        %>
        <TR>
        <TD><%= documents.get(i).getTitle() %></TD>
        </TR>
        <%
    }
%>
</table>
<aui:button-row>
	<portlet:renderURL var="firstLinesURL">
	    <portlet:param name="mvcPath" value="/html/firstlines/results.jsp"></portlet:param>
	</portlet:renderURL>
	
	<aui:button onClick="<%= firstLinesURL.toString() %>" value="Get First Lines"></aui:button>
</aui:button-row>


<%-- 
<liferay-ui:search-container delta="10" emptyResultsMessage="No documents found">
	<liferay-ui:search-container-results
		results="<%=DLFileEntryLocalServiceUtil.getDLFileEntries(searchContainer.getStart(), searchContainer.getEnd())%>"
		total="<%=DLFileEntryLocalServiceUtil.getDLFileEntriesCount()%>"
		/>
		
		<liferay-ui:search-container-row
			className="edu.ucla.macroscope.firstlines.FirstLinesPortlet"
			keyProperty="documentId"
			modelVar="document"
		>
		</liferay-ui:search-container-row>
	
	<liferay-ui:search-iterator />
		
</liferay-ui:search-container>
 --%>
 
