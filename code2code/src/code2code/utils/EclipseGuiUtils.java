package code2code.utils;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

public class EclipseGuiUtils {

	
	public static void openResource(final IFile resource) {
		
		final IWorkbenchPage activePage= PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		
		if (activePage != null) {
			final Display display=  PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().getDisplay();
			if (display != null) {
				display.asyncExec(new Runnable() {
					public void run() {
						try {
							IDE.openEditor(activePage, resource, true);
						} catch (PartInitException e) {
							EclipseGuiUtils.showErrorDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), e);
							throw new RuntimeException(e);
						}
					}
				});
			}
		}
	}

	public static void showErrorDialog(Shell shell, Exception e) {
		
		MessageDialog.openError(shell, 
				"An Error ocurred",
				"An Error ocurred: " + e.getMessage() + 
				"\nSee Error Log view for more details.");
		
	}

	public static void scaleShellToClientArea(Shell shell, double ratio) {
		
		Rectangle clientArea = Display.getCurrent().getClientArea();
		
		int relativeWidth = (int) (clientArea.width * ratio);
		int relativeHeight = (int) (clientArea.height * ratio);
		
		int hPadding = (clientArea.width - relativeWidth) / 2; 
		int vPadding = (clientArea.height - relativeHeight) / 2; 
		
		Rectangle bounds = new Rectangle(clientArea.x + hPadding, clientArea.y + vPadding, relativeWidth, relativeHeight);
		
		shell.setBounds(bounds);
		
	}

	
}
