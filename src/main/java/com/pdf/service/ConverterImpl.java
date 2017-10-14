
package com.pdf.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.pdf.spi.Converter;

@Service
public class ConverterImpl implements Converter {
	private static final Logger logger = LoggerFactory.getLogger(ConverterImpl.class);

	@Override
	public void convertHtmlToPdf(String html, OutputStream out) {
		convertHtmlToPdf(html, null, out);
	}

	@Override
	public void convertHtmlToPdf(String html, String css, OutputStream out) {
		try {
			html = correctHtml(html);
			html = getFormedHTMLWithCSS(html, css);

			ITextRenderer r = new ITextRenderer();
			r.setDocumentFromString(html);
			r.layout();
			r.createPDF(out);
			r.finishPDF();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public void convertHtmlToPdf(List<String> htmls, OutputStream out) {
		convertHtmlToPdf(htmls, null, out);
	}

	@Override
	public void convertHtmlToPdf(List<String> htmls, String css, OutputStream out) {
		try {
			PDFMergerUtility merge = new PDFMergerUtility();

			for (String html : htmls) {
				ByteArrayOutputStream bos = new ByteArrayOutputStream();

				// convertHtmlToPdf() performs null check on css by default, so
				// no need to do it here.
				convertHtmlToPdf(html, css, bos);

				ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
				merge.addSource(bis);
			}

			merge.setDestinationStream(out);
			merge.mergeDocuments(null);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
	}

	private String getFormedHTMLWithCSS(String htmlBody, String css) {
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<head>");
		if (StringUtils.isNotBlank(css)) {
			sb.append("<style type='text/css'>");
			sb.append(css);
			sb.append("</style>");
		}
		sb.append("</head>");
		sb.append("<body>");
		sb.append(htmlBody);
		sb.append("</body>");
		sb.append("</html>");
		return sb.toString();
	}

	private String correctHtml(String html) {
		html = html.replaceAll("&nbsp;", "&#160;");

		return html;
	}

}
