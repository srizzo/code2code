package pageobjects;

import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;

import utils.Driver;
import domain.WorkingProject;

public class GenerateFilesWizard {

	public static void finish() throws Exception {
		SWTBotShell wizardShell = Driver.bot().activeShell();
		Driver.bot().button("Finish").click();
		Driver.bot().waitUntil(Conditions.shellCloses(wizardShell));
	}

	public static void open() throws Exception {
		PackageExplorer.rightClick(WorkingProject.project(), "Generate...");
	}

}
