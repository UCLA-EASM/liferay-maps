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
import com.liferay.portlet.documentlibrary.model.DLContent;
import com.liferay.portlet.documentlibrary.service.DLContentLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class FirstLinesPortlet
 */
public class FirstLinesPortlet extends MVCPortlet {
 
	public void loadFirstLines(ActionRequest request, ActionResponse response) 
			throws InvalidParameterException, PortalException, SystemException, SQLException, IOException {
		
		String requestParamDocumentIds = request.getParameter("documentIDs");
		
		if (requestParamDocumentIds == null) {
			throw new InvalidParameterException("Document IDs was null");
		}
		
		String[] splitDocumentIds = requestParamDocumentIds.split(",");
		
		List<FirstLinesResult> results = new ArrayList<FirstLinesResult>();
		
		for (String textualDocumentId : splitDocumentIds) {
			long numericalDocumentID = Long.parseLong(textualDocumentId);
			DLContent document = (DLContentLocalServiceUtil.getDLContent(numericalDocumentID));
			
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			
			InputStream stream = document.getData().getBinaryStream();
			
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
