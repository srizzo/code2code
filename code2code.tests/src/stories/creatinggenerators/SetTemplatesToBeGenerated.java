package stories.creatinggenerators;
import org.jbehave.scenario.Scenario;

import steps.CreateGeneratorSteps;
import steps.GenerateFilesSteps;
import steps.ProjectSteps;

public class SetTemplatesToBeGenerated extends Scenario {
	
	
	public SetTemplatesToBeGenerated() {
       super(new ProjectSteps(), new CreateGeneratorSteps(), new GenerateFilesSteps());
    }


	
}