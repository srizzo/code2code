package pageobjects;

import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.StringResult;

import utils.Driver;

public class ConfigureParamsDialog {

	public static boolean isVisible() {
		try {
			Driver.bot().label("Configure Params");
		} catch (WidgetNotFoundException e) {
			return false;
		}
		return true;
	}

	public static boolean isListed(String param) {
		try {
			Driver.bot().textWithLabel(param);
		} catch (WidgetNotFoundException e) {
			return false;
		}
		return true;
	}

	public static String getParamValue(String param) throws Exception {
		return Driver.bot().textWithLabel(param).getText();
	}

	public static void clickNext() throws Exception {
		Driver.bot().button("Next >").click();
	}

	public static void setParam(String param, String value) throws Exception {
		Driver.bot().textWithLabel(param).setText(value);
	}

	public static String getDescription() throws Exception {
		return UIThreadRunnable.syncExec(Driver.bot().activeShell().widget.getDisplay(), new StringResult(){
			public String run() {
				try {
					return ((WizardDialog)Driver.bot().activeShell().widget.getData()).getCurrentPage().getDescription();
				} catch (WidgetNotFoundException e) {
					throw new RuntimeException(e);
				}
			}});
	}

}
