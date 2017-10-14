
package com.pdf.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdf.spi.TemplateDataTransformer;

@Service
public class TemplateDataTransformerImpl implements TemplateDataTransformer {
	private final Logger logger = LoggerFactory.getLogger(TemplateDataTransformerImpl.class);

	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public boolean isJsonArray(String json) {
		if(StringUtils.isNoneBlank(json) && StringUtils.isNoneBlank(json.trim()) && json.trim().startsWith("[")&& json.trim().endsWith("]")){
			return true;
		}
		return false;
	}

	@Override
	public String transformHTMLTemplate(String htmlTemplate, String jsonObject) {
		try {
			Map<String, Object> data = mapper.readValue(jsonObject, Map.class);
			return getHtmlFromTemplateAndData(htmlTemplate, data);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return htmlTemplate;
	}

	@Override
	public List<String> transformHTMLTemplates(String htmlTemplate, String jsonData) {
		try {
			List<String> html = new ArrayList<>();
			JsonFactory f = new JsonFactory();
			JsonParser jp = f.createParser(jsonData);
			// advance stream to START_ARRAY first:
			jp.nextToken();
			// and then each time, advance to opening START_OBJECT
			while (jp.nextToken() == JsonToken.START_OBJECT) {
				Map<String, Object> data = mapper.readValue(jp, Map.class);
				// process
				// after binding, stream points to closing END_OBJECT
				html.add(getHtmlFromTemplateAndData(htmlTemplate, data));
			}
			
			return html;
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return new ArrayList<>();
	}

	private String getHtmlFromTemplateAndData(String htmlTemplate, Map<String, Object> data) {
		logger.debug("Json Object contains " + data.entrySet().size() + " properties.");
		for (Entry<String, Object> e : data.entrySet()) {
//			logger.debug(e.getKey() + ":" + e.getValue());
			htmlTemplate = htmlTemplate.replaceAll("\\{" + e.getKey() + "\\}", (String) e.getValue());
//			logger.debug("HTML Template: " + htmlTemplate);
		}

		logger.debug("Final HTML Template: " + htmlTemplate);

		return htmlTemplate;
	}


}
