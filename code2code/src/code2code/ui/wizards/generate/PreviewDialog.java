package code2code.ui.wizards.generate;

import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import code2code.core.generator.Template;
import code2code.utils.EclipseGuiUtils;
import code2code.utils.FileUtils;


public class PreviewDialog extends TrayDialog {
	
	private StyledText previewText;
	private final Template result;

	
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		
		EclipseGuiUtils.scaleShellToClientArea(newShell, 0.7);
	}
	
	public PreviewDialog(Shell shell, Template result) {
		super(shell);
		
		this.result = result;
		setShellStyle(getShellStyle() | SWT.RESIZE);
	}

	protected Control createDialogArea(Composite parent) {
		
		Composite dialogArea= (Composite) super.createDialogArea(parent);
		
		FillLayout fillLayout = new FillLayout();
		dialogArea.setLayout(fillLayout);
		
		previewText = new StyledText(dialogArea, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		
		try {
			previewText.setText(FileUtils.read(result.calculateResult()));
		} catch (Exception e) {
			EclipseGuiUtils.showErrorDialog(getShell(), e);
			throw new RuntimeException(e);
		}
		
		return dialogArea;
	}
	
	
	@Override
	protected void okPressed() {
		
		result.setUserChoosenResult(previewText.getText());
		
		super.okPressed();
	}
	

	
	
}