package edu.ucla.macroscope.firstlines;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
//import com.liferay.portlet.documentlibrary.model.DLContent;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
//import com.liferay.portlet.documentlibrary.service.DLContentLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class FirstLinesPortlet
 */
public class FirstLinesPortlet extends MVCPortlet {
 
	public void loadFirstLines(ActionRequest request, ActionResponse response) 
			throws InvalidParameterException, PortalException, SystemException, SQLException, IOException {
		
		String docListSize = request.getParameter("doumentListSize");
		int count = Integer.parseInt(docListSize);
		System.out.println(count);
		
		String[] requestParamDocumentIds = request.getParameterValues("documentIDs");
		System.out.println(requestParamDocumentIds.length);
		
		ArrayList<String> selectedDocumentIds = new ArrayList<String>();
		
		for (int i = 0; i < count; i++) {
			if(ParamUtil.getBoolean(request,requestParamDocumentIds[i])){
				selectedDocumentIds.add(requestParamDocumentIds[i]);
			}
		}
		System.out.print(selectedDocumentIds);
		
		if (requestParamDocumentIds == null) {
			throw new InvalidParameterException("Document IDs was null");
		}
		
		List<FirstLinesResult> results = new ArrayList<FirstLinesResult>();
		
		for (String textualDocumentId : requestParamDocumentIds) {
			long numericalDocumentID = Long.parseLong(textualDocumentId);
			DLFileEntry document = DLFileEntryLocalServiceUtil.getDLFileEntry(numericalDocumentID);
//			DLContent document = (DLContentLocalServiceUtil.getDLContent(numericalDocumentID));
			
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			
//			InputStream stream = document.getData().getBinaryStream();
			InputStream stream = document.getContentStream();
			System.out.println(stream);
			
			// 4096 because it's unlikely the first line of a text file will have a first line
			// greater than 4K long
			int byteCount = stream.read(buffer, 0, 4096);
			
			String firstLine;
			
			if (byteCount != -1) {
				byteStream.write(buffer, byteCount, 0);
				stream.close();
				byte[] bytes = byteStream.toByteArray();
				
				// Assuming all files will be UTF-8. Not safe for production,
				// and should be tested before a demo
				String possibleFirstLines = new String(bytes, "UTF-8");
				
				firstLine = possibleFirstLines.split("[\\r\\n]+")[0];
			} else {
				firstLine = "<Empty file>";
			}
			
			results.add(new FirstLinesResult(document, firstLine));
		}
		
		SessionMessages.add(request, "results", results);
	}

}
