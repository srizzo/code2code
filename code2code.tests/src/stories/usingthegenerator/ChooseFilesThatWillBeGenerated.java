package stories.usingthegenerator;
import org.jbehave.scenario.Scenario;

import steps.CreateGeneratorSteps;
import steps.GenerateFilesSteps;
import steps.ProjectSteps;

public class ChooseFilesThatWillBeGenerated extends Scenario {
	
	
	public ChooseFilesThatWillBeGenerated() {
       super(new ProjectSteps(), new GenerateFilesSteps(), new CreateGeneratorSteps());
    }


	
}