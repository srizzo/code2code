package pageobjects;

import org.eclipse.swt.custom.StyledText;
import org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.finders.ChildrenControlFinder;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotStyledText;

import utils.Driver;

public class PreviewDialog {

	public static String getContent() throws Exception {
		StyledText text = (StyledText) new ChildrenControlFinder(Driver.bot().activeShell().widget).findControls(WidgetMatcherFactory.widgetOfType(StyledText.class)).get(0);
		SWTBotStyledText swtBotText = new SWTBotStyledText(text);
		return swtBotText.getText();
	}

	public static void setContent(String content) {
		StyledText text = (StyledText) new ChildrenControlFinder(Driver.bot().activeShell().widget).findControls(WidgetMatcherFactory.widgetOfType(StyledText.class)).get(0);
		SWTBotStyledText swtBotText = new SWTBotStyledText(text);
		swtBotText.setText(content);
	}

	public static void clickOk() {
		SWTBotShell previewShell = Driver.bot().activeShell();
		Driver.bot().button("OK").click();
		Driver.bot().waitUntil(Conditions.shellCloses(previewShell));
	}

}
