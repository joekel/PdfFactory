
package com.pdf.rest.dto;

import java.io.Serializable;

public class PdfRequest implements Serializable {

	private String html;
	private String css;
	private String json;

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	
}
