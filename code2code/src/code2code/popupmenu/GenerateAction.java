package code2code.popupmenu;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.wizards.IWizardDescriptor;
import org.eclipse.ui.wizards.IWizardRegistry;

import code2code.utils.EclipseGuiUtils;


public class GenerateAction implements IObjectActionDelegate{

	private ISelection selection;
	private IWorkbenchPart targetPart;

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.targetPart = targetPart;
	}

	public void run(IAction action) {
		
		IWizardRegistry newWizardRegistry = PlatformUI.getWorkbench().getNewWizardRegistry();
		IWizardDescriptor descriptor = newWizardRegistry.findWizard("code2code.newWizard");
		
		try {
			IWorkbenchWizard wizard = descriptor.createWizard();

			wizard.init(targetPart.getSite().getWorkbenchWindow().getWorkbench(), (IStructuredSelection) selection);
			WizardDialog dialog = new WizardDialog(targetPart.getSite().getShell(), wizard);
			dialog.open();

		} catch (CoreException e) {
			EclipseGuiUtils.showErrorDialog(targetPart.getSite().getShell(), e);
			throw new RuntimeException(e);
		}
		
	}

	
	
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

}
