<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="javax.portlet.PortletPreferences" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import = "com.liferay.portlet.documentlibrary.model.DLFileEntry" %>
<%@ page import = "com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil" %>
<%-- <%@ page import = "com.liferay.portlet.documentlibrary.model.DLContent" %> 
<%@ page import = "com.liferay.portlet.documentlibrary.service.DLContentLocalServiceUtil" %>
 --%>
 <portlet:defineObjects />

This is the <b>First Lines</b> portlet in View mode.

<h1>My Library</h1>

<% 
final int fileCount = DLFileEntryLocalServiceUtil.getDLFileEntriesCount();
List<DLFileEntry> documents = DLFileEntryLocalServiceUtil.getDLFileEntries(0, fileCount);
//System.out.println(fileCount);
//System.out.println(documents.get(0));
List<DLFileEntry> textDocuments = new ArrayList<DLFileEntry>();

for(int i=0;i<documents.size();i++) {
	//System.out.println(documents.get(i).getExtension());
	if(documents.get(i).getExtension() == "txt") {
		textDocuments.add(documents.get(i));
		System.out.println("Found a text doc");
	}
}
//System.out.println(textDocuments);
%>

<portlet:actionURL name="loadFirstLines" var="loadFirstLinesURL">
</portlet:actionURL>
 
<aui:form action="<%= loadFirstLinesURL %>" method="post">
	<% for ( int i = 0; i < documents.size(); i++ ) {
		%>
	<aui:input type="checkbox"  
				name="<%= \"document-\" + Long.toString(documents.get(i).getFileEntryId()) %>" label="<%=documents.get(i).getTitle() %>">
	</aui:input>
	<% 
	}
	%>
	<aui:input type="hidden" name="doumentListSize" value="<%= documents.size() %>"></aui:input>
	<aui:input type="submit" name="" value="Get First Lines" />
</aui:form>

