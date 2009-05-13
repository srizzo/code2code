package pageobjects;

import utils.Driver;

public class Workbench {

	private static boolean welcomeClosed = false;

	public static void closeWelcomeView() throws Exception {
		if (!welcomeClosed) {
			Driver.bot().view("Welcome").close();
			welcomeClosed = true;
		}
	}



}
