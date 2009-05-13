package stories.creatinggenerators;
import org.jbehave.scenario.Scenario;

import steps.CreateGeneratorSteps;
import steps.GenerateFilesSteps;
import steps.ProjectSteps;

public class SetTemplateEngineForTheTemplatesConfigFile extends Scenario {
	
	
	public SetTemplateEngineForTheTemplatesConfigFile() {
       super(new ProjectSteps(), new CreateGeneratorSteps(), new GenerateFilesSteps());
    }


	
}