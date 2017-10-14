
package com.pdf.test.converter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pdf.spi.Converter;
import com.pdf.spi.TemplateDataTransformer;
import com.pdf.spi.TestDataProvider;
import com.pdf.test.Application;

@RunWith(SpringJUnit4ClassRunner.class)
// @SpringApplicationConfiguration(classes=Application.class)
@SpringApplicationConfiguration(Application.class)
public class TemplateTransformationTest {

	@Autowired
	public Converter converter;

	@Autowired
	public TemplateDataTransformer transformer;

	@Autowired
	public TestDataProvider dataProvider;

	private String getSimpleHtml() {
		return dataProvider.getHtmlDoc();
	}

	private String getHtmlUsingTemplate() {
		return transformer.transformHTMLTemplate(dataProvider.getHtmlTemplateDoc(), dataProvider.getTestDataObject());
	}

//	@Test
//	public void testHtmlToPdfFile() {
//		try {
//			OutputStream file = new FileOutputStream(new File("HTMLtoPDFTest.pdf"));
//			converter.convertHtmlToPdf(getSimpleHtml(), file);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//	}

//	@Test
//	public void testHtmlToPdfByteFile() {
//		try {
//			ByteArrayOutputStream bos = new ByteArrayOutputStream();
//			OutputStream file = new FileOutputStream(new File("HTMLtoPDFTestByte.pdf"));
//			converter.convertHtmlToPdf(getSimpleHtml(), bos);
//			file.write(bos.toByteArray());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	@Test
	public void testHtmlToPdfFileWithCss() {
		try {
			OutputStream file = new FileOutputStream(new File("HTMLtoPDFTestCss.pdf"));
			converter.convertHtmlToPdf(getSimpleHtml(), dataProvider.getCssDoc(), file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

//	@Test
//	public void testHtmlToPdfByteFileWithCss() {
//		try {
//			ByteArrayOutputStream bos = new ByteArrayOutputStream();
//			OutputStream file = new FileOutputStream(new File("HTMLtoPDFTestByteCss.pdf"));
//			converter.convertHtmlToPdf(getSimpleHtml(), dataProvider.getCssDoc(), bos);
//			file.write(bos.toByteArray());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

//	@Test
//	public void testHtmlTemplateToPdfFile() {
//		try {
//			OutputStream file = new FileOutputStream(new File("HTMLTemplatetoPDFTest.pdf"));
//			converter.convertHtmlToPdf(getHtmlUsingTemplate(), file);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//
//	}

//	@Test
//	public void testHtmlTemplateToPdfByteFile() {
//		try {
//			ByteArrayOutputStream bos = new ByteArrayOutputStream();
//			OutputStream file = new FileOutputStream(new File("HTMLTemplatetoPDFTestByte.pdf"));
//			converter.convertHtmlToPdf(getHtmlUsingTemplate(), bos);
//			file.write(bos.toByteArray());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	@Test
	public void testHtmlTemplateToPdfFileWithCss() {
		try {
			OutputStream file = new FileOutputStream(new File("HTMLTemplatetoPDFTestCss.pdf"));
			converter.convertHtmlToPdf(getHtmlUsingTemplate(), dataProvider.getCssDoc(), file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

//	@Test
//	public void testHtmlTemplateToPdfByteFileWithCss() {
//		try {
//			ByteArrayOutputStream bos = new ByteArrayOutputStream();
//			OutputStream file = new FileOutputStream(new File("HTMLTemplatetoPDFTestByteCss.pdf"));
//			converter.convertHtmlToPdf(getHtmlUsingTemplate(), dataProvider.getCssDoc(), bos);
//			file.write(bos.toByteArray());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

}
