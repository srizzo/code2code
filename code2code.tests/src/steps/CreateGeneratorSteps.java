package steps;

import static org.junit.Assert.*;

import org.jbehave.scenario.annotations.Then;
import org.jbehave.scenario.annotations.When;
import org.jbehave.scenario.steps.Steps;

import pageobjects.CustomizeGenerationDialog;
import pageobjects.SelectAGeneratorDialog;
import domain.WorkingProject;

public class CreateGeneratorSteps extends Steps {
	
	@When("I create the generator folder \"$folderPath\"")
	public void createFolder(String folderPath) throws Exception {
		WorkingProject.createFolder(folderPath);
	}
	
	@When("I create the template file \"$filePath\"")
	public void createTemplateFile(String filePath) throws Exception {
		WorkingProject.createFile(filePath);
	}

	@When("I create the template file \"$filePath\" with contents: $contents")
	public void createTemplateFileWithContents(String filePath, String contents) throws Exception {
		WorkingProject.createFileWithContents(filePath, contents);
	}
	
	@When("I create the templates configuration file \"$filePath\"")
	public void createTemplatesConfigurationFile(String filePath) throws Exception {
		WorkingProject.createFile(filePath);
	}

	@When("I create the templates configuration file \"$filePath\" with contents: $contents")
	public void createTemplatesConfigurationFileWithContents(String filePath, String contents) throws Exception {
		WorkingProject.createFileWithContents(filePath, contents);
	}
	
	@When("I create the params configuration file \"$filePath\" with contents: $contents")
	public void createParamsConfigurationFile(String filePath, String contents) throws Exception {
		WorkingProject.createFileWithContents(filePath, contents);
	}

	@When("I create the description file \"$filePath\" with contents: $contents")
	public void createDescriptionFile(String filePath, String contents) throws Exception {
		WorkingProject.createFileWithContents(filePath, contents);
	}
	
	
	@When("I create the global params configuration file \"$filePath\" with contents: $contents")
	public void createGlobalParamsConfigurationFile(String filePath, String contents) throws Exception {
		WorkingProject.createFileWithContents(filePath, contents);
	}
	
	@Then("I will see the \"$generator\" generator listed on the \"Select a Generator\" dialog")
	public void checkGeneratorListed(String generator) {
		assertTrue(SelectAGeneratorDialog.isListed(generator));
	}

	@Then("I will see the template \"$template\" listed with destination \"$destination\" on the \"Customize Generation\" dialog")
	public void checkTemplateWithDestinationListed(String template, String destination) {
		assertTrue(SelectAGeneratorDialog.isListed(template));
	}
	
	@Then("I will see the \"Customize Generation\" dialog")
	public void checkCustomizeGenerationDialog() throws Exception{
		assertTrue(CustomizeGenerationDialog.isVisible());
	}

	@Then("I will see the template \"$template\" listed")
	public void checkTemplateListed(String template) throws Exception{
		assertTrue(CustomizeGenerationDialog.isListed(template));
	}

	@Then("I will see the template \"$template\" destination set to \"$destination\"")
	public void checkTemplateDestination(String template, String destination) throws Exception{
		assertEquals(destination, CustomizeGenerationDialog.getDestination(template));
	}
	
	
	
	
	
}
