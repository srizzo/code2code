package stories.usingthegenerator;
import org.jbehave.scenario.Scenario;

import steps.CreateGeneratorSteps;
import steps.GenerateFilesSteps;
import steps.ProjectSteps;

public class GenerateFiles extends Scenario {
	
	
	public GenerateFiles() {
       super(new ProjectSteps(), new CreateGeneratorSteps(), new GenerateFilesSteps());
    }


	
}