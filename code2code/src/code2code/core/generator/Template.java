package code2code.core.generator;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import code2code.core.templateengine.TemplateEngine;
import code2code.core.templateengine.TemplateEngineFactory;



public class Template {

	private boolean selectedToGenerate = true;
	private String userChoosenResult;
	private String userChoosenDestination;
	private final String templateName;
	private final TemplatesConfig templatesConfig;
	private final String initialDestination;


	public Template(TemplatesConfig templatesConfig, String templateName, String initialDestination) {
		this.templatesConfig = templatesConfig;
		this.templateName = templateName;
		this.initialDestination = initialDestination;
	}


	public String getTemplateName() {
		return templateName;
	}
	

	public InputStream calculateResult() throws Exception {
		
		if(userChoosenResult!=null){
			return new ByteArrayInputStream(userChoosenResult.getBytes());
		}
		
		TemplateEngine templateEngine = TemplateEngineFactory.forFileName(templateName);
		
		if(templateEngine != null){
			return new ByteArrayInputStream(templateEngine.processTemplate(templatesConfig.getGenerator(), templateName, templatesConfig.getGenerator().calculateContext()).getBytes());
		}else{
			return templatesConfig.getGenerator().getGeneratorFolder().getFile(templateName).getContents();
		}
		
	}
	
	public String calculateDestination() throws Exception {
		if(userChoosenDestination != null){
			return userChoosenDestination;
		}else{		
			return templatesConfig.getTemplateEngine().processString(templatesConfig.getGenerator(), initialDestination, templatesConfig.getGenerator().calculateContext());
		}
	}

	
	public boolean isSelectedToGenerate() {
		return selectedToGenerate;
	}

	public void setSelectedToGenerate(boolean selectedToGenerate) {
		this.selectedToGenerate = selectedToGenerate;
	}
	
	public void setUserChoosenResult(String userChoosenResult) {
		this.userChoosenResult = userChoosenResult;
	}
	
	public void setUserChoosenDestination(String userChoosenDestination) {
		this.userChoosenDestination = userChoosenDestination;
	}

}
