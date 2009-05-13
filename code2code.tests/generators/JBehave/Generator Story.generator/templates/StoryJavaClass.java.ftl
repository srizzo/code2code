package ${destination?replace("src/", "")?replace("/", ".")};
import org.jbehave.scenario.Scenario;

import steps.CreateGeneratorSteps;
import steps.GenerateFilesSteps;
import steps.ProjectSteps;

public class ${story?capitalize?replace(" ", "")} extends Scenario {
	
	
	public ${story?capitalize?replace(" ", "")}() {
       super(new ProjectSteps(), new CreateGeneratorSteps(), new GenerateFilesSteps());
    }


	
}