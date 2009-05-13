package pageobjects;

import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;

import utils.Driver;

public class SelectAGeneratorDialog {

	public static boolean isVisible() {
		try {
			Driver.bot().label("Select a Generator");
		} catch (WidgetNotFoundException e) {
			return false;
		}
		return true;
	}

	public static boolean isListed(String generator) {
		try {
			Driver.bot().radio(generator);
		} catch (WidgetNotFoundException e) {
			return false;
		}
		return true;
	}

	public static void select(String generator) throws Exception {
		Driver.bot().radio(generator).click();
	}

	public static void clickNext() throws Exception {
		Driver.bot().button("Next >").click();
	}

}
