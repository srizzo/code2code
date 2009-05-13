package code2code.core.templateengine;

import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;

import code2code.core.generator.Generator;


public interface TemplateEngine {

	boolean canFindKnownFile(IFolder folder, String fileName);

	IFile getKnownFile(IFolder folder, String fileName);

	boolean canProcess(String fileName);

	Map<String, String> processMap(Generator generator, Map<String, String> params, Map<String, String> context) throws Exception;

	String processString(Generator generator, String content, Map<String, String> context) throws Exception;

	String processTemplate(Generator generator, String templateName, Map<String, String> context) throws Exception;

	boolean knowExtension(String extension);

	String escape(String contents);

}
