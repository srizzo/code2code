package code2code.core.generator;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;

import code2code.core.templateengine.TemplateEngine;
import code2code.core.templateengine.TemplateEngineFactory;
import code2code.utils.FileUtils;


class GlobalParamsConfig{

	private Map<String, String> processedParams;
	private TemplateEngine templateEngine;
	
	
	public GlobalParamsConfig(Generator generator) throws Exception {
		
		IFile file = TemplateEngineFactory.findKnownTemplate(generator.getGeneratorFolder().getProject().getFolder("generators"), "params");
		
		if(file == null){
			processedParams = new LinkedHashMap<String, String>();
		}else{
			templateEngine = TemplateEngineFactory.forFile(file);
			processedParams = templateEngine.processMap(generator, FileUtils.propertiesInputStreamAsMap(file.getContents()), new LinkedHashMap<String, String>());
		}
		
	}
	
	public Map<String, String> getProcessedParams() {
		return processedParams;
	}
	
}