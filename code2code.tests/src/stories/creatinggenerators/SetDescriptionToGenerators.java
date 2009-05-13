package stories.creatinggenerators;

import org.jbehave.scenario.Scenario;

import steps.CreateGeneratorSteps;
import steps.GenerateFilesSteps;
import steps.ProjectSteps;

public class SetDescriptionToGenerators extends Scenario {
	
	
	public SetDescriptionToGenerators() {
       super(new ProjectSteps(), new CreateGeneratorSteps(), new GenerateFilesSteps());
    }


	
}