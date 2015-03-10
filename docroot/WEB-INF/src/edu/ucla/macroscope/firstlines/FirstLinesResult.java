package edu.ucla.macroscope.firstlines;

import com.liferay.portlet.documentlibrary.model.DLContent;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;

public class FirstLinesResult {
	private DLFileEntry content;
	private String line;
	
	public FirstLinesResult(DLFileEntry content, String line) {
		super();
		this.content = content;
		this.line = line;
	}
	
	public DLFileEntry getContent() {
		return content;
	}
	public String getLine() {
		return line;
	}
	
}
