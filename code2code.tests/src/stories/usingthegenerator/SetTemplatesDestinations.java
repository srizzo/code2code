package stories.usingthegenerator;
import org.jbehave.scenario.Scenario;

import steps.CreateGeneratorSteps;
import steps.GenerateFilesSteps;
import steps.ProjectSteps;

public class SetTemplatesDestinations extends Scenario {
	
	
	public SetTemplatesDestinations() {
       super(new ProjectSteps(), new CreateGeneratorSteps(), new GenerateFilesSteps());
    }


	
}