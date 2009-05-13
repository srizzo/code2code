package code2code.core.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.core.resources.IFile;

import code2code.core.templateengine.TemplateEngine;
import code2code.core.templateengine.TemplateEngineFactory;
import code2code.utils.FileUtils;


class TemplatesConfig{

	private final IFile templatesFile;
	private final Generator generator;

	private final List<Template> templates = new ArrayList<Template>();
	private TemplateEngine templateEngine;
	private List<Generator> nestedGenerators = new ArrayList<Generator>();
	
	public TemplatesConfig(Generator generator) throws Exception {

		this.generator = generator;

		templatesFile = TemplateEngineFactory.findKnownTemplate(generator.getGeneratorFolder(), "templates");
		templateEngine = TemplateEngineFactory.forFile(templatesFile);

		
		for (Entry<Object, Object> entry : FileUtils.inputStreamAsProperties(templatesFile.getContents()).entrySet()) {

			String templateName = (String) entry.getKey();
			String destination = (String) entry.getValue();
			
			if(templateName.endsWith(".generator")){
				nestedGenerators.add(Generator.fromFolder(generator.getGeneratorFolder().getFolder(templateName)));
			}else{			
				templates.add(new Template(this, templateName, destination));
			}
			
		}
		
	}
	
	
	
	public List<Template> getTemplates() throws Exception {
		return templates;
	}


	public TemplateEngine getTemplateEngine() {
		return templateEngine;
	}


	public Generator getGenerator() {
		return generator;
	}
	
	public List<Generator> getNestedGenerators() {
		return nestedGenerators;
	}

}
