package code2code.core.templateengine;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import code2code.core.generator.Generator;


public class VelocityTemplateEngine extends AbstractTemplateEngine  implements TemplateEngine {

	@Override
	public List<String> getKnownExtensions() {
		return Arrays.asList(new String[]{"vm"});
	}

	public String processTemplate(Generator generator, String templateName, Map<String, String> context) throws Exception {

		VelocityEngine velocityEngine = createVelocityEngine(generator);
		VelocityContext velocityContext = createVelocityContext(context);

		StringWriter writer = new StringWriter();
		velocityEngine.mergeTemplate(templateName, "UTF-8", velocityContext, writer);
		
		return writer.toString();
	}
	

	public String processString(Generator generator, String templateContent, Map<String, String> context) throws Exception {

		VelocityEngine velocityEngine = createVelocityEngine(generator);
		VelocityContext velocityContext = createVelocityContext(context);

		StringWriter writer = new StringWriter();
		velocityEngine.evaluate(velocityContext, writer, "preview", templateContent);
		
		return writer.toString();
	}


	public String escape(String contents) {
		
		String escaped = contents.replace("$", "${d}").replace("#", "${b}");
		escaped = "#set($b='#')\n" + "#set($d='$')\n" + escaped;
		
		return escaped;
	}

	
	private VelocityEngine createVelocityEngine(Generator generator) throws Exception {
		VelocityEngine velocityEngine = new VelocityEngine();
		Properties properties = new Properties();
	    properties.setProperty("file.resource.loader.path", generator.getGeneratorFolder().getLocation().toString());
	    velocityEngine.init( properties );
		return velocityEngine;
	}

	private VelocityContext createVelocityContext(Map<String, String> context) {
		VelocityContext velocityContext = new VelocityContext();
		
		for (String key : context.keySet()) {
			velocityContext.put(key, context.get(key));
		}
		return velocityContext;
	}


}
