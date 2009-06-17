package code2code.ui.wizards.generate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import code2code.core.generator.Generator;
import code2code.core.generator.GeneratorFactory;
import code2code.utils.EclipseGuiUtils;


public class GeneratorSelectionPage  extends WizardPage {

	private List<Button> generatorButtons;
	private Generator selectedGenerator;
	private final IProject project;
	
	@Override
	public boolean isPageComplete() {
		return getSelectedGenerator() != null;
	}
	
	public GeneratorSelectionPage(IProject project) {
		super("Generator Selection", "Select a Generator", null);
		this.project = project;
		setPageComplete(false);
	}
	

	public void createControl(Composite parent) {

		ScrolledComposite scrolledComposite = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		Composite container = new Composite(scrolledComposite, SWT.NONE);
		scrolledComposite.setContent(container);
		
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		container.setLayout(layout);

		generatorButtons = new ArrayList<Button>();

		Set<Generator> generators;
		try {
			generators = GeneratorFactory.fromProject(project);
		} catch (Exception e1) {
			EclipseGuiUtils.showErrorDialog(container.getShell(), e1);
			throw new RuntimeException(e1);
		}

		if(generators.isEmpty()){
			Label label = new Label(container, SWT.NONE);
			label.setText("No generators found.");
		}
		
		for (Generator generator : generators) {

			Button button = new Button(container, SWT.RADIO);
			button.setText(generator.getName());
			button.setData("generator", generator);

			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					selectedGenerator = (Generator) ((Button) e.getSource()).getData("generator");
					setPageComplete(true);
				}
			});
			
			generatorButtons.add(button);
		}
		
		container.setSize(container.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		setControl(scrolledComposite);
	}

	
	public Generator getSelectedGenerator(){
		return selectedGenerator;
	}

	
}
