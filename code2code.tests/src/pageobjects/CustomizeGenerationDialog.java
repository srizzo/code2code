package pageobjects;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.ControlFinder;
import org.eclipse.swtbot.swt.finder.utils.SWTUtils;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;

import utils.Driver;

public class CustomizeGenerationDialog {

	public static void uncheckFile(String file) throws Exception {
		Driver.bot().checkBox(file).deselect();
	}

	@SuppressWarnings("unchecked")
	public static void setDestination(String template, String destination) throws Exception {
		Widget templateButtonWidget = new ControlFinder().findControls(
				WidgetMatcherFactory.allOf(
						WidgetMatcherFactory.widgetOfType(Button.class),
						WidgetMatcherFactory.withText(template)
				))
				.get(0);
		SWTBotText destinationText = new SWTBotText((Text) SWTUtils.nextWidget(templateButtonWidget));
		
		destinationText.setText(destination);
	}

	@SuppressWarnings("unchecked")
	public static void openPreview(String template) throws Exception {
		Widget templateButtonWidget = new ControlFinder().findControls(
				WidgetMatcherFactory.allOf(
						WidgetMatcherFactory.widgetOfType(Button.class),
						WidgetMatcherFactory.withText(template)
				))
				.get(0);
		Widget destinationWidget = SWTUtils.nextWidget(templateButtonWidget);
		SWTBotButton previewButton = new SWTBotButton((Button) SWTUtils.nextWidget(destinationWidget));

		previewButton.click();
	}

	public static boolean isVisible() {
		try {
			Driver.bot().label("Customize Generation");
		} catch (WidgetNotFoundException e) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public static boolean isListed(String template) {
		return new ControlFinder().findControls(
				WidgetMatcherFactory.allOf(
						WidgetMatcherFactory.widgetOfType(Button.class),
						WidgetMatcherFactory.withText(template)
				)).size() == 1;
	}

	@SuppressWarnings("unchecked")
	public static String getDestination(String template) {
		Widget templateButtonWidget = new ControlFinder().findControls(
				WidgetMatcherFactory.allOf(
						WidgetMatcherFactory.widgetOfType(Button.class),
						WidgetMatcherFactory.withText(template)
				))
				.get(0);
		SWTBotText destinationText = new SWTBotText((Text) SWTUtils.nextWidget(templateButtonWidget));
		
		return destinationText.getText();
	}
}
