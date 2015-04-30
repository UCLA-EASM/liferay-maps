package edu.ucla.macroscope.firstlines;
//import ValueComparator;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

public class Extractwords  extends MVCPortlet{
	/*public void tokenize_file(ActionRequest request, ActionResponse response) 
			throws InvalidParameterException, PortalException, SystemException, SQLException, IOException, PortletException {
		
		ArrayList<Long> selectedDocumentIds = new ArrayList<Long>();
		String count = "0";
		HashMap<String,Integer> dict = new HashMap<String,Integer>();
		ValueComparator bvc =  new ValueComparator(dict);
        TreeMap<String,Integer> sorted_map = new TreeMap<String,Integer>(bvc);

		
		for (Enumeration<String> parameterNames = request.getParameterNames(); parameterNames.hasMoreElements();) {
			String parameterName = parameterNames.nextElement();
			
			if (!parameterName.startsWith("document-")) {
				continue;
			}
			
			if(ParamUtil.getBoolean(request, parameterName)) {
				// NOTE: Potential bug if document IDs get more complex
				Long documentId = Long.parseLong(parameterName.replaceAll("document-", ""));
				selectedDocumentIds.add(documentId);
			}
		}
		
		if (selectedDocumentIds.isEmpty()) {
			throw new InvalidParameterException("No document IDs selected");
		}
		
		List<WordCloudResult> results = new ArrayList<WordCloudResult>();
		
		for (Long documentId : selectedDocumentIds) {;
			DLFileEntry document = DLFileEntryLocalServiceUtil.getDLFileEntry(documentId);
			//BufferedReader br=null;
			//br = new BufferedReader(new FileReader(document.getTitle()));
			//br = new BufferedReader(bew FileReader(document));
			
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[Math.abs((int)document.getSize())];
			
			InputStream stream = document.getContentStream();
			if(stream.read(buffer)==-1){
				System.out.println("Entire file parsed");
			}
			
			stream.close();
			
			String lines = new String(buffer);
			
			//Making the dictionary
			Pattern p = Pattern.compile("[\\w']+");
			Matcher m = p.matcher(lines);
			while(m.find()){
				String word = lines.substring(m.start(), m.end());
				if(dict.containsKey(word)){
					int count1  = dict.get(word);
					count1++;
					dict.put(word, count1);
				}
				else{
					dict.put(word, 0);
				}
				 //System.out.println();
			}
			sorted_map.putAll(dict);
			//System.out.println("results: "+sorted_map);
			//System.out.println(lines);
		
			
			//////////////////// Previous Code reused
			String firstLine;
			
			
			count = Long.toString(document.getSize());

			results.add(new WordCloudResult(document, sorted_map, count));
			//results.add(new FirstLinesResult(document, firstLine));
		}
		
		//SessionMessages.add(request, "results", results);
		//System.out.println(SessionMessages.get(request, "results"));
		//System.out.println(results.get(0).getLine());
		for(int i=0; i< results.size();i++) {
			response.setRenderParameter("result"+i, results.get(i).getWordArray());
			response.setRenderParameter("title"+i, results.get(i).getContent().getTitle());
			response.setRenderParameter("filecounts"+i, results.get(i).getFileCount());
		}
		
		response.setRenderParameter("resultSize", Integer.toString(results.size()));
		response.setRenderParameter("jspPage", "/html/firstlines/viewcloud.jsp");
	}*/
}
/*
class ValueComparator implements Comparator<String> {

    Map<String, Integer> base;
    public ValueComparator(Map<String, Integer> base) {
        this.base = base;
    }

    // Note: this comparator imposes orderings that are inconsistent with equals.    
    public int compare(String a, String b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }
}*/