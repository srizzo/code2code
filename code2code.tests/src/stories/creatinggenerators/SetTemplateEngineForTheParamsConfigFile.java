package stories.creatinggenerators;
import org.jbehave.scenario.Scenario;

import steps.CreateGeneratorSteps;
import steps.GenerateFilesSteps;
import steps.ProjectSteps;

public class SetTemplateEngineForTheParamsConfigFile extends Scenario {
	
	
	public SetTemplateEngineForTheParamsConfigFile() {
       super(new ProjectSteps(), new CreateGeneratorSteps(), new GenerateFilesSteps());
    }


	
}