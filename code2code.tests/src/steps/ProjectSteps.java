package steps;

import org.jbehave.scenario.annotations.AfterScenario;
import org.jbehave.scenario.annotations.BeforeScenario;
import org.jbehave.scenario.annotations.Given;
import org.jbehave.scenario.annotations.When;
import org.jbehave.scenario.steps.Steps;

import pageobjects.PackageExplorer;
import pageobjects.Workbench;
import domain.WorkingProject;

public class ProjectSteps extends Steps {
	
	@BeforeScenario
	public void closeWelcome() throws Exception {
		Workbench.closeWelcomeView();
	}

	@Given("I have a new project")
	public void createNewProject() throws Exception{
		WorkingProject.create();
	}
	
	@When("I right click the project on the Package Explorer and select \"$menuItem\"")
	public void rightClick(String menuItem) throws Exception {
		PackageExplorer.rightClick(WorkingProject.project(), menuItem);
	}
	
	@AfterScenario()
	public void removeProject() throws Exception{
//		WorkingProject.delete();
	}
	
	
}
