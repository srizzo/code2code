package code2code.ui.wizards.generate;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import code2code.core.generator.Template;
import code2code.utils.EclipseGuiUtils;


public class GenerationCustomizationComposite extends Composite {

	  public static void main(String[] args) throws Exception {

		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout(SWT.VERTICAL| SWT.HORIZONTAL));
		
		GenerationCustomizationComposite c = new GenerationCustomizationComposite(new ArrayList<Template>(), shell, SWT.NULL);
		
		c.pack();
		c.setVisible(true);
		
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
	  
	  
	public GenerationCustomizationComposite(final List<Template> results, Composite parent, int style) throws Exception {
		super(parent, style);
		
		this.setLayout(new FillLayout(SWT.VERTICAL| SWT.HORIZONTAL));
		
		ScrolledComposite scroll = new ScrolledComposite(this, SWT.V_SCROLL | SWT.H_SCROLL);
		
		scroll.setExpandVertical(true);
		scroll.setExpandHorizontal(true);
		
		Composite container = new Composite(scroll, SWT.NONE);
		
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		container.setLayout(layout);
		
		
		
//		for (int i = 0; i < 10; i++) {
//			
//			Button checkbox1 = new Button(container, SWT.CHECK);
//			checkbox1.setSelection(true);
//			checkbox1.setText("teste");
//			checkbox1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
//			
//			Text text1 = new Text(container, SWT.BORDER);
//			text1.setText("teste");
//			text1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
//			
//			Button previewButton1 = new Button(container, SWT.PUSH);
//			previewButton1.setText("Preview...");
//		
//		}
		
		
		for (final Template result : results) {

			final String templateName = result.getTemplateName();
			
			Button checkbox = new Button(container, SWT.CHECK);
			checkbox.setSelection(true);
			checkbox.addSelectionListener(new SelectionAdapter(){
				@Override
				public void widgetSelected(SelectionEvent event) {
					try {
						result.setSelectedToGenerate(((Button)event.widget).getSelection());
					} catch (Exception e) {
						EclipseGuiUtils.showErrorDialog(GenerationCustomizationComposite.this.getShell(), e);
						throw new RuntimeException(e);
					}
				}
			});
			
			checkbox.setText(templateName);
			checkbox.setToolTipText(templateName);
			checkbox.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
			
			Text text = new Text(container, SWT.BORDER);
			text.setText(result.calculateDestination());
			
			text.addModifyListener(new ModifyListener(){
				public void modifyText(ModifyEvent e) {
					result.setUserChoosenDestination(((Text)e.widget).getText());
				}
			});
			text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
			
			Button previewButton = new Button(container, SWT.PUSH);
			previewButton.setText("Preview...");
			
			previewButton.addSelectionListener(new SelectionAdapter(){
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					
					PreviewDialog previewDialog;
					try {
						previewDialog = new PreviewDialog(GenerationCustomizationComposite.this.getShell(), result);
					} catch (Exception e1) {
						EclipseGuiUtils.showErrorDialog(GenerationCustomizationComposite.this.getShell(), e1);
						throw new RuntimeException(e1);
					}
					
					previewDialog.setBlockOnOpen(true);
					
					previewDialog.create();
					previewDialog.open();
					
					
				}
			});
			
		}

		
		scroll.setMinSize(container.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scroll.setContent(container);
	}


}
