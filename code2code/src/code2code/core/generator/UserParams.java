package code2code.core.generator;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import code2code.core.templateengine.TemplateEngine;
import code2code.core.templateengine.TemplateEngineFactory;


class UserParams{
	
	private final GlobalParamsConfig globalParamsConfig;
	
	private Map<String, String> userConfiguredParams;
	private final Generator generator;
	
	private TemplateEngine templateEngine;
	private final List<Generator> nestedGenerators;
	private final ParamsConfig paramsConfig;;
	
	
	public UserParams(Generator generator, GlobalParamsConfig globalParamsConfig, ParamsConfig paramsConfig, List<Generator> nestedGenerators) {
		
		this.globalParamsConfig = globalParamsConfig;
		
		this.generator = generator;
		this.paramsConfig = paramsConfig;
		
		this.nestedGenerators = nestedGenerators;
		
		if(paramsConfig.getTemplateEngine() != null){
			this.templateEngine = paramsConfig.getTemplateEngine();
		}else{
			this.templateEngine = TemplateEngineFactory.nullTemplateEngine();
		}
		
		setUserConfiguredParams(calculateGeneratorRequiredParams());
		
	}

	public Map<String, String> translated() throws Exception {
		return templateEngine.processMap(generator, userConfiguredParams , globalParamsConfig.getProcessedParams());
	}
	

	public void setUserConfiguredParams(Map<String, String> userConfiguredParams) {
		this.userConfiguredParams = userConfiguredParams;
		
		for (Generator nestedGenerator : nestedGenerators) {
			nestedGenerator.setUserConfiguredParams(userConfiguredParams);
		}
		
	}
	
	
	public Map<String, String> calculateGeneratorRequiredParams() {
		
		Map<String, String> allRequiredParams = new LinkedHashMap<String, String>();
		
		for (Generator nestedGenerator : nestedGenerators) {
			allRequiredParams.putAll(nestedGenerator.calculateRequiredParams());
		}

		for (String key : paramsConfig.getParams().keySet()) {
			allRequiredParams.remove(key);
		}
		allRequiredParams.putAll(paramsConfig.getParams());

		return allRequiredParams;
	}
	
}

