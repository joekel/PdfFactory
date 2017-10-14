
package com.pdf.rest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pdf.spi.TestDataProvider;

@RestController
@RequestMapping("service/test/data")
public class TestDataResource {
	private static final Logger logger = LoggerFactory.getLogger(TestDataResource.class);

	@Autowired
	private TestDataProvider dataProvider;

	
	@RequestMapping({ "", "/" })
	@ResponseBody
	public String sayHello() {
		String testHtml =dataProvider.getHtmlDoc();
		String testCSS=dataProvider.getCssDoc();
		
		testHtml = StringEscapeUtils.escapeHtml4(testHtml);
		
		String testJson = "{\"html\": \""+StringEscapeUtils.escapeJson(testHtml)+"\", \"css\":\""+StringEscapeUtils.escapeJson(testCSS)+"\"}";
		
		logger.debug("This is the Test HTML to PDF wrapper service.");
		String resp = "This is the Test HTML to PDF wrapper service. " + "All methods are the same and GET. "
				+ "No need to provide HTML or CSS. <br/> <br/>" + "<h1>Test HTML: </h1>" + testHtml
				+ " <br/><br/>" + "<h1>Test CSS:</h1>" + testCSS 
				+ "<br/><br/>" + "<h1>Test JSON:</h1>" + testJson
				;

		logger.debug("HTML: " + testHtml);
		logger.debug("CSS: " + testCSS);
		logger.debug("JSON: " + testJson);
		
		return resp;
	}

	
	@RequestMapping("/html")
	@ResponseBody
	public String getHtml() {
//		return StringEscapeUtils.escapeHtml4(dataProvider.getHtmlDoc());
		return dataProvider.getHtmlDoc();
	}

	@RequestMapping("/html/template")
	@ResponseBody
	public String getHtmlTemplate() {
//		return StringEscapeUtils.escapeHtml4(dataProvider.getHtmlTemplateDoc());
		return dataProvider.getHtmlTemplateDoc();
	}

	
	@RequestMapping("/css")
	@ResponseBody
	public String getCss() {
//		return StringEscapeUtils.escapeHtml4(dataProvider.getCssDoc());
		return dataProvider.getCssDoc();
	}

	@RequestMapping("/json")
	@ResponseBody
	public String getJsonData() {
//		return StringEscapeUtils.escapeHtml4(dataProvider.getTestData());
		return dataProvider.getTestDataArray();
	}

}
