package code2code.ui.wizards.generatorfromfiles;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import code2code.core.generatorfromfiles.ImportedFile;
import code2code.utils.EclipseGuiUtils;


public class ImportedFilesConfigurationComposite extends Composite {

	private String selectedExtension =  "groovy";

	public static void main(String[] args) {

		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout(SWT.VERTICAL | SWT.HORIZONTAL));

		ImportedFilesConfigurationComposite c = new ImportedFilesConfigurationComposite(new ArrayList<ImportedFile>(), shell, SWT.NULL);

		c.setVisible(true);

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	public ImportedFilesConfigurationComposite(final List<ImportedFile> importedFiles, Composite parent, int style) {
		super(parent, style);

		this.setLayout(new FormLayout());

		final Group templateEngineGroup = new Group(this, SWT.NULL);
		templateEngineGroup.setText("Template Engine");
		final GridLayout gridLayout_2 = new GridLayout();
		gridLayout_2.numColumns = 2;
		templateEngineGroup.setLayout(gridLayout_2);
		final FormData fd_templateEngineGroup = new FormData();
		fd_templateEngineGroup.top = new FormAttachment(0, 0);
		fd_templateEngineGroup.right = new FormAttachment(100, 0);
		fd_templateEngineGroup.left = new FormAttachment(0, 0);
		templateEngineGroup.setLayoutData(fd_templateEngineGroup);

		final Label escapeAsLabel = new Label(templateEngineGroup, SWT.NONE);
		escapeAsLabel.setText("Escape files as:");

		final Combo combo = new Combo(templateEngineGroup, SWT.READ_ONLY);
		combo.setItems(new String[] { "groovy", "ftl", "vm" });
		combo.select(combo.indexOf(selectedExtension));
		
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				for (ImportedFile importedFile : importedFiles) {
					importedFile.setExtension(combo.getText());
					selectedExtension = combo.getText();
				}
				super.widgetSelected(e);
			}
		});

		final ScrolledComposite scroll = new ScrolledComposite(this, SWT.V_SCROLL | SWT.H_SCROLL);
		scroll.setExpandVertical(true);
		scroll.setExpandHorizontal(true);
		final FormData fd_scroll = new FormData();
		fd_scroll.top = new FormAttachment(templateEngineGroup, 0, SWT.DEFAULT);
		fd_scroll.left = new FormAttachment(0, 0);
		fd_scroll.right = new FormAttachment(100, 0);
		fd_scroll.bottom = new FormAttachment(100, 0);
		scroll.setLayoutData(fd_scroll);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.makeColumnsEqualWidth = true;
		gridLayout.numColumns = 2;
		scroll.setLayout(gridLayout);

		final Group filesToImportGroup = new Group(scroll, SWT.NONE);
		filesToImportGroup.setText("Files to import");
		final GridLayout gridLayout_1 = new GridLayout();
		gridLayout_1.makeColumnsEqualWidth = true;
		gridLayout_1.numColumns = 2;
		filesToImportGroup.setLayout(gridLayout_1);

		// for (int i = 0; i < 10; i++) {
		// final Button button = new Button(filesToImportGroup, SWT.CHECK);
		// button.setText("Check Button");
		//			
		// final Text text_1 = new Text(filesToImportGroup, SWT.BORDER);
		// text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
		// false));
		//			
		// }
		//		

		for (final ImportedFile importedFile : importedFiles) {

			Button generateCheckbox = new Button(filesToImportGroup, SWT.CHECK);
			generateCheckbox.setSelection(true);
			generateCheckbox.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent event) {
					try {
						importedFile.setSelectedToBeCreated(((Button) event.widget).getSelection());
					} catch (Exception e) {
						EclipseGuiUtils.showErrorDialog(ImportedFilesConfigurationComposite.this.getShell(), e);
						throw new RuntimeException(e);
					}
				}
			});
			generateCheckbox.setText(importedFile.getImportedFile().getProject().getName() + "/"
					+ importedFile.getImportedFile().getProjectRelativePath().toString());

			generateCheckbox.setToolTipText(importedFile.getImportedFile().getProject().getName() + "/"
					+ importedFile.getImportedFile().getProjectRelativePath().toString());

			generateCheckbox.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

			Text destinationText = new Text(filesToImportGroup, SWT.BORDER);
			destinationText.setText(importedFile.getGeneratorRelativeDestinationPath());

			destinationText.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					importedFile.setGeneratorRelativeDestinationPath(((Text) e.widget).getText());
				}
			});

			destinationText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		}

		scroll.setMinSize(filesToImportGroup.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scroll.setContent(filesToImportGroup);

	}

	
	public String getSelectedExtension() {
		return selectedExtension;
	}
}
