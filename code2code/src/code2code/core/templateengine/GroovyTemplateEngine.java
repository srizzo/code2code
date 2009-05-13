package code2code.core.templateengine;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import code2code.core.generator.Generator;
import code2code.utils.FileUtils;

import groovy.lang.Writable;
import groovy.text.SimpleTemplateEngine;
import groovy.text.Template;

public class GroovyTemplateEngine extends AbstractTemplateEngine  implements TemplateEngine {

	@Override
	public List<String> getKnownExtensions() {
		return Arrays.asList(new String[]{"groovy"});
	}

	public String processTemplate(Generator generator, String templateName, Map<String, String> context) throws Exception {

		SimpleTemplateEngine engine = new SimpleTemplateEngine();
		
		Template template = engine.createTemplate(FileUtils.read(generator.getGeneratorFolder().getFile(templateName).getContents()));

		Writable writable = template.make(context);
		
		StringWriter stringWriter = new StringWriter();
		writable.writeTo(stringWriter);
		
		return stringWriter.toString();
	}
	

	public String processString(Generator generator, String templateContent, Map<String, String> context) throws Exception {

		SimpleTemplateEngine engine = new SimpleTemplateEngine();
		
		Template template = engine.createTemplate(templateContent);

		Writable writable = template.make(context);
		
		StringWriter stringWriter = new StringWriter();
		writable.writeTo(stringWriter);
		
		return stringWriter.toString();
	}


	public String escape(String contents) {
		
		String escaped = contents.replace("\\", "\\\\").
			replace("$", "\\$")
			.replaceAll("(?<=<)%|%(?=>)", "<%=\"%\"%>");
		
		return escaped;
	}

	

}
