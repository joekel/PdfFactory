
package com.pdf.spi;

import java.io.OutputStream;
import java.util.List;

public interface Converter {

	public void convertHtmlToPdf(List<String> htmls, OutputStream out);

	public void convertHtmlToPdf(List<String> htmls, String css, OutputStream out);

	
	public void convertHtmlToPdf(String html, OutputStream out);

	public void convertHtmlToPdf(String html, String css, OutputStream out);
}
