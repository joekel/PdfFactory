package com.pdf.spi;

import java.util.List;

public interface TemplateDataTransformer {

	public boolean isJsonArray(String json);
	
	/**
	 * Transforms HTML Template containing variables with jsonObject
	 * HTML Template can contain variables in the format <code>{variable.name}</code>
	 * jsonObject should contain the key as variable.name with data value, e.g., 
	 * 		HTML Template: <h1>Hello {name}</h1>
	 * 		Json Object: {"name":"jaber kaal", "signature":"Jack Bauer"} 
	 * @param htmlTemplate
	 * @param jsonObject - contains a single object
	 * @return htmlTemplate with variables replaced by jsonData
	 */
	public String transformHTMLTemplate(String htmlTemplate, String jsonObject);
	
	
	/**
	 * Transforms HTML Template containing variables with jsonData
	 * HTML Template can contain variables in the format <code>{variable.name}</code>
	 * Each element of jsonData should contain the key as variable.name with data value, e.g., 
	 * 		HTML Template: <h1>Hello {name}</h1>
	 * 		Json Data: [{"name":"jaber kaal", "signature":"Jack Bauer"}, {"name":"John Doe", "signature":"Jack Bauer"}, {"name":"Richard Roe", "signature":"Jack Bauer"}] 
	 * @param htmlTemplate
	 * @param jsonData - contains a list of json objects
	 * @return List of htmlTemplates with variables replaced by jsonData
	 */
	public List<String> transformHTMLTemplates(String htmlTemplate, String jsonData);
	
}
