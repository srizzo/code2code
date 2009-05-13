package code2code.utils;

import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

public class Console {
	
	private static final String CONSOLE_NAME = "code2code.console";

	public static MessageConsole getConsole() {
		ConsolePlugin plugin = ConsolePlugin.getDefault();
		IConsoleManager conMan = plugin.getConsoleManager();
		IConsole[] existing = conMan.getConsoles();
		for (int i = 0; i < existing.length; i++)
			if (CONSOLE_NAME.equals(existing[i].getName()))
				return (MessageConsole) existing[i];
		MessageConsole myConsole = new MessageConsole(CONSOLE_NAME, null);
		conMan.addConsoles(new IConsole[] { myConsole });
		return myConsole;
	}
	
	public static void disposeConsole(){

//		TODO how I do that?		
//		MessageConsole console = getConsole();
//		console.destroy();
//		IConsoleManager consoleManager = ConsolePlugin.getDefault().getConsoleManager();
//		consoleManager.removeConsoles(new IConsole[]{console});
		
	}
	
	
	public static void write(String text) throws Exception {

		ConsolePlugin.getDefault().getConsoleManager().showConsoleView(getConsole());

		MessageConsoleStream out = getConsole().newMessageStream();
		out.println(text);
		out.flush();
		out.close();
	}

	public static String getText() {
		return getConsole().getDocument().get();
	}

}
