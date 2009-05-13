package steps;

import static org.junit.Assert.*;

import org.jbehave.scenario.annotations.Then;
import org.jbehave.scenario.annotations.When;
import org.jbehave.scenario.steps.Steps;

import pageobjects.ConfigureParamsDialog;
import pageobjects.ConsoleView;
import pageobjects.CustomizeGenerationDialog;
import pageobjects.GenerateFilesWizard;
import pageobjects.PreviewDialog;
import pageobjects.SelectAGeneratorDialog;
import domain.WorkingProject;

public class GenerateFilesSteps extends Steps {
	
	@When("I open the \"Generate Files\" wizard")
	public void openGenerateFilesWizard() throws Exception {
		GenerateFilesWizard.open();
	}
	
	@When("I select the \"$generator\" generator and click \"Next\"")
	public void selectGeneratorAndClickNext(String generator) throws Exception {
		SelectAGeneratorDialog.select(generator);
		SelectAGeneratorDialog.clickNext();
	}

	@When("I click \"Next\" on the \"Configure Params\" dialog")
	public void clickNextOnConfigureParams() throws Exception {
		ConfigureParamsDialog.clickNext();
	}
	
	@When("I uncheck the file \"$file\" on the \"Customize Generation\" dialog")
	public void uncheckFileOnCustomizeGeneration(String file) throws Exception {
		CustomizeGenerationDialog.uncheckFile(file);
	}
	
	@When("I finish the \"Generate Files\" wizard")
	public void finishWizard() throws Exception {
		GenerateFilesWizard.finish();
	}
	
	@When("I set the param \"$param\" value to \"$value\"")
	public void setGeneratorParam(String param, String value) throws Exception{
		ConfigureParamsDialog.setParam(param, value);
	}
	
	@When("I change the template \"$template\" destination to \"$destination\" on the \"Customize Generation\" dialog")
	public void setFileDestination(String template, String destination) throws Exception{
		CustomizeGenerationDialog.setDestination(template, destination);
	}
	
		
	@When("I click \"Preview\" on the template \"$template\"")
	public void clickPreview(String template) throws Exception {
		CustomizeGenerationDialog.openPreview(template);
	}
		
	
	@When("I change the output content on the \"Preview\" dialog to: $content")
	public void changePreviewContent(String content) throws Exception {
		PreviewDialog.setContent(content);
	}
		
	@When("I click \"OK\" on the \"Preview\" dialog")
	public void clickOkOnPreviewDialog() throws Exception {
		PreviewDialog.clickOk();
	}
	
	
	@Then("I will see the \"Select a Generator\" dialog")
	public void checkSelectAGeneratorDialog() throws Exception{
		assertTrue(SelectAGeneratorDialog.isVisible());
	}
	
	@Then("I will see the \"Configure Params\" dialog")
	public void checkConfigureParamsDialog() throws Exception{
		assertTrue(ConfigureParamsDialog.isVisible());
	}

	@Then("I will see the \"$param\" parameter to fill")
	public void checkParamListed(String param){
		assertTrue(ConfigureParamsDialog.isListed(param));
	}
	
	@Then("I will see the \"$param\" value already filled with \"$value\"")
	public void checkParamFilled(String param, String value) throws Exception{
		assertEquals(value, ConfigureParamsDialog.getParamValue(param));
	}
	
	@Then("I will see the \"$generator\" generator listed")
	public void checkGeneratorListed(String generator) {
		assertTrue(SelectAGeneratorDialog.isListed(generator));
	}
		
	@Then("I will see the file \"$file\" generated")
	public void checkFileExists(String file) throws Exception {
		assertTrue(WorkingProject.fileExists(file));
	}

	@Then("I will see not the file \"$file\" generated")
	public void checkFileDoesntExist(String file) throws Exception {
		assertFalse(WorkingProject.fileExists(file));
	}


	@Then("I will see the file \"$file\" generated with content: $content")
	public void checkFileContent(String file, String content) throws Exception {
		assertEquals(content, WorkingProject.read(file));
	}

	
	@Then("I will see the \"Configure Params\" dialog with the description: $description")
	public void checkDescriptionOnConfigureParams(String description) throws Exception {
		assertEquals(description, ConfigureParamsDialog.getDescription());
	}
	
	
	@Then("I will see the \"Preview\" dialog with content: $content")
	public void checkPreviewContent(String content) throws Exception {
		assertEquals(content, PreviewDialog.getContent());
	}
	
	@Then("I will see the \"Console\" printed with the content: $content")
	public void checkConsoleContainsContent(String content) throws Exception {
		assertTrue("Expected Console content:\n" + ConsoleView.getContent() + "\n to contain: \n" + content, ConsoleView.getContent().contains(content));
	}
	
	
}
