package code2code.core.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFolder;

public class Generator {
	
	final private IFolder generatorFolder;
	
	final private DescriptionConfig descriptionConfig;
	final private GlobalParamsConfig globalParamsConfig;
	final private ParamsConfig paramsConfig;
	final private TemplatesConfig templatesConfig;

	final private List<Generator> nestedGenerators;

	final private List<Template> templates;

	final private UserParams userParams;

	
	private Generator(IFolder folder) throws Exception{
		generatorFolder = folder;
		
		globalParamsConfig = new GlobalParamsConfig(this);
		descriptionConfig = new DescriptionConfig(this, globalParamsConfig);
		paramsConfig = new ParamsConfig(this);
		templatesConfig = new TemplatesConfig(this);
		
		nestedGenerators = templatesConfig.getNestedGenerators();
		
		templates = calculateGeneratorTemplates();
		
		userParams = new UserParams(this, globalParamsConfig, paramsConfig, nestedGenerators);
		
	}

	public static Generator fromFolder(IFolder folder) throws Exception {
		return new Generator(folder);
	}

	
	private List<Template> calculateGeneratorTemplates() throws Exception {
		
		List<Template> templates = templatesConfig.getTemplates();
		
		for (Generator nestedGenerator : nestedGenerators) {
			templates.addAll(nestedGenerator.calculateGeneratorTemplates());
		}

		return templates;
	}
	

	public List<Template> calculateChoosenTemplatesToGenerate() throws Exception {
		
		List<Template> templatesToGenerate = new ArrayList<Template>();
		
		for (Template template : templates) {
			if(template.isSelectedToGenerate()){
				templatesToGenerate.add(template);
			}
		}
		
		return templatesToGenerate;
	}
	

	public IFolder getGeneratorFolder() {
		return generatorFolder;
	}
	
	
	public String getName() {
		return generatorFolder.getFullPath().removeFirstSegments(2).removeFileExtension().toString();
	}

	public String getDescription() {
		return descriptionConfig.getDescription();
	}

	
	public Map<String, String> calculateRequiredParams() {
		return userParams.calculateGeneratorRequiredParams();
	}
	

	public Map<String, String> calculateContext() throws Exception {
		return userParams.translated();
	}

	
	public void setUserConfiguredParams(Map<String, String> userConfiguredParams) {
		userParams.setUserConfiguredParams(userConfiguredParams);
	}
	
	public List<Template> getTemplates() {
		return templates;
	}


}
