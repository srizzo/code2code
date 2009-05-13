package stories.usingthegenerator;
import org.jbehave.scenario.Scenario;

import steps.CreateGeneratorSteps;
import steps.GenerateFilesSteps;
import steps.ProjectSteps;

public class ParamsReferencingOtherParams extends Scenario {
	
	
	public ParamsReferencingOtherParams() {
       super(new ProjectSteps(), new CreateGeneratorSteps(), new GenerateFilesSteps());
    }


	
}