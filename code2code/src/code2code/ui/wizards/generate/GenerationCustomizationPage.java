package code2code.ui.wizards.generate;

import java.util.List;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import code2code.core.generator.Generator;
import code2code.core.generator.Template;
import code2code.utils.EclipseGuiUtils;


public class GenerationCustomizationPage extends WizardPage {

	private Composite container;
	private Composite contents;
	private GeneratorParametersPage generatorParametersPage;
	private Composite parent;

	public GenerationCustomizationPage(GeneratorParametersPage generatorParametersPage) {
		super("Templates Selection", "Customize Generation", null);
		this.generatorParametersPage = generatorParametersPage;
		setPageComplete(true);
	}

	public void createControl(Composite parent) {
		this.parent = parent;
		container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout(SWT.VERTICAL| SWT.HORIZONTAL));
		
		setControl(container);
		
		EclipseGuiUtils.scaleShellToClientArea(parent.getShell(), 0.8);
	}

	@Override
	public void setVisible(boolean visible) {

		if (visible) {
			try {
				createPage();
			} catch (Exception e) {
				EclipseGuiUtils.showErrorDialog(container.getShell(), e);
				throw new RuntimeException(e);
			}
		}
		
		super.setVisible(visible);
	}

	
	private void createPage() throws Exception {
		
		if(contents != null){
			contents.dispose();
		}
		
		final Generator selectedGenerator = generatorParametersPage.getSelectedGenerator();
		
		List<Template> templates = selectedGenerator.getTemplates();		
		
		contents = new GenerationCustomizationComposite(templates, container, SWT.NULL);
		
		container.pack();
		parent.layout();
		
		container.setVisible(true);
		
	}

}
