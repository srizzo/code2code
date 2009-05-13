package code2code.popupmenu;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;

import code2code.ui.wizards.generatorfromfiles.GeneratorFromFilesWizard;


public class CreateGeneratorAction  implements IObjectActionDelegate{

	private ISelection selection;
	private IWorkbenchPart targetPart;

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.targetPart = targetPart;
	}

	public void run(IAction action) {
		
		   IWorkbenchWindow window = targetPart.getSite().getWorkbenchWindow();
		   GeneratorFromFilesWizard wizard = new GeneratorFromFilesWizard();
		   wizard.init(window.getWorkbench(), (IStructuredSelection) selection);
		   WizardDialog dialog = new WizardDialog(window.getShell(), wizard);
		   dialog.open();
	}

	
	
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

}
