package stories.creatinggenerators;
import org.jbehave.scenario.Scenario;

import steps.CreateGeneratorSteps;
import steps.GenerateFilesSteps;
import steps.ProjectSteps;

public class CreateCompositeGenerators extends Scenario {
	
	
	public CreateCompositeGenerators() {
       super(new ProjectSteps(), new CreateGeneratorSteps(), new GenerateFilesSteps());
    }


	
}