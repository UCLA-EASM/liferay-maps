package edu.ucla.macroscope.firstlines;

import com.liferay.portlet.documentlibrary.model.DLContent;

public class FirstLinesResult {
	private DLContent content;
	private String line;
	
	public FirstLinesResult(DLContent content, String line) {
		super();
		this.content = content;
		this.line = line;
	}
	
	public DLContent getContent() {
		return content;
	}
	public String getLine() {
		return line;
	}
	
}
