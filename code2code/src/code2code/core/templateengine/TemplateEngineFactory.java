/**
 * 
 */
package code2code.core.templateengine;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;


public class TemplateEngineFactory{

	private static List<TemplateEngine> templateEngines;
	
	static{
		templateEngines = new ArrayList<TemplateEngine>();
		templateEngines.add(new GroovyTemplateEngine());
		templateEngines.add(new FreemarkerTemplateEngine());
		templateEngines.add(new VelocityTemplateEngine());
		templateEngines.add(new PlainTemplateEngine());
	}
	
	public static IFile findKnownTemplate(IFolder folder, String fileName) {
		for (TemplateEngine templateEngine : templateEngines ) {
			IFile knownFile = templateEngine.getKnownFile(folder, fileName);
			if(knownFile != null){
				return knownFile;
			}
		}
		return null;
	}

	public static TemplateEngine forFile(IFile file) {
		return forFileName(file.getName());
	}

	public static TemplateEngine forFileName(String fileName) {
		for (TemplateEngine templateEngine : templateEngines) {
			if(templateEngine.canProcess(fileName)){
				return templateEngine;
			}
		}
		
		return null;
	}

	public static TemplateEngine nullTemplateEngine() {
		return new PlainTemplateEngine();
	}

	public static TemplateEngine forExtension(String extension) {
		
		for (TemplateEngine templateEngine : templateEngines) {
			if(templateEngine.knowExtension(extension)){
				return templateEngine;
			}
		}
		
		return null;
	}
	
	
}