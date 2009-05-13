package utils;

import org.eclipse.swtbot.eclipse.finder.SWTEclipseBot;

public class Driver {

	private static SWTEclipseBot bot;

	public static SWTEclipseBot bot() {
		if (bot == null) {
			bot = new SWTEclipseBot();
		}
		return bot;
	}

}
