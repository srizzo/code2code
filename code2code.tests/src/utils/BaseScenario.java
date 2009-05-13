package utils;

import org.jbehave.scenario.Scenario;
import org.jbehave.scenario.steps.CandidateSteps;


public class BaseScenario  extends Scenario {


	public BaseScenario(CandidateSteps... candidateSteps) {
		super(candidateSteps);
	}
	
}
