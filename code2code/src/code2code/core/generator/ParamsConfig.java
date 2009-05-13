package code2code.core.generator;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;

import code2code.core.templateengine.TemplateEngine;
import code2code.core.templateengine.TemplateEngineFactory;
import code2code.utils.FileUtils;


class ParamsConfig{

	private Map<String, String> initialParams;
	private final Generator generator;
	private TemplateEngine templateEngine;

	public ParamsConfig(Generator generator) throws Exception {
		this.generator = generator;
		
		IFile file = TemplateEngineFactory.findKnownTemplate(generator.getGeneratorFolder(), "params");
		
		if (file == null) {
			initialParams = new LinkedHashMap<String, String>();
		}else{
			this.templateEngine = TemplateEngineFactory.forFile(file);
			initialParams = FileUtils.propertiesInputStreamAsMap(file.getContents());
		}

	}

	public TemplateEngine getTemplateEngine() {
		return templateEngine;
	}

	public Map<String, String> translated(Map<String, String> context) throws Exception {
		return templateEngine.processMap(generator, initialParams,  context);
	}

	public Map<String, String> getParams() {
		return initialParams;
	}
	
}