package code2code.core.generator;

import org.eclipse.core.resources.IFile;

import code2code.core.templateengine.TemplateEngineFactory;
import code2code.utils.FileUtils;


public class DescriptionConfig{

	private String description;

	public DescriptionConfig(Generator generator, GlobalParamsConfig globalParamsConfig) throws Exception {

		IFile file = TemplateEngineFactory.findKnownTemplate(generator.getGeneratorFolder(), "description");
		if(file == null){
			description = null;
		}else{
			description = TemplateEngineFactory.forFile(file).processString(generator, FileUtils.read(file.getContents()), globalParamsConfig.getProcessedParams());
		}
	}


	public String getDescription() {
		return description;
	}
	
	
	
}
