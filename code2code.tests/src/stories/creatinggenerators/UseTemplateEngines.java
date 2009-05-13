package stories.creatinggenerators;
import org.jbehave.scenario.Scenario;

import steps.CreateGeneratorSteps;
import steps.GenerateFilesSteps;
import steps.ProjectSteps;

public class UseTemplateEngines extends Scenario {
	
	
	public UseTemplateEngines() {
       super(new ProjectSteps(), new CreateGeneratorSteps(), new GenerateFilesSteps());
    }


	
}