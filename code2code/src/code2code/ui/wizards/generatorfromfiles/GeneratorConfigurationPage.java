package code2code.ui.wizards.generatorfromfiles;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import code2code.core.generator.GeneratorFactory;
import code2code.core.generatorfromfiles.ImportedFile;
import code2code.core.generatorfromfiles.NewGeneratorConfig;
import code2code.utils.EclipseGuiUtils;


public class GeneratorConfigurationPage   extends WizardPage{

	private final IStructuredSelection selection;
	private List<ImportedFile> importedFiles;
	private NewGeneratorConfig newGeneratorConfig;

	public GeneratorConfigurationPage(IStructuredSelection selection) {
		super("Generator Configuration");
		this.selection = selection;
		
		newGeneratorConfig = new NewGeneratorConfig();
		
		newGeneratorConfig.setProject(((IResource)selection.getFirstElement()).getProject());
		newGeneratorConfig.setGeneratorName("NewGenerator");
		
		if(GeneratorFactory.exists(newGeneratorConfig.getProject(), newGeneratorConfig.getGeneratorName())){
			setErrorMessage("Generator already exists with this name");
			setPageComplete(false);
		}
		
	}

	public void createControl(Composite parent) {

		EclipseGuiUtils.scaleShellToClientArea(parent.getShell(), 0.8);
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		Composite container = new Composite(scrolledComposite, SWT.NONE);
		scrolledComposite.setContent(container);
		
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		container.setLayout(layout);

		createProjectTextInput(container);
		createGeneratorNameTextInput(container);
		
		try {
			createFilesSelectionCheckboxes(container);
		} catch (Exception e) {
			EclipseGuiUtils.showErrorDialog(container.getShell(), e);
			throw new RuntimeException(e);
		}
		
		container.setSize(container.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		setControl(scrolledComposite);
		
		
		scrolledComposite.pack();
		scrolledComposite.setVisible(true);
		scrolledComposite.layout();
		
	}



	@SuppressWarnings("unchecked")
	private void createFilesSelectionCheckboxes(Composite container) throws Exception {
		
		List<IResource> selectedResources = selection.toList();
		
		importedFiles = new ArrayList<ImportedFile>();
		
		for (final IResource selectedResource : selectedResources) {
			
			final IPath basePath = selectedResource.getParent().getProjectRelativePath();
			
			selectedResource.accept(new IResourceVisitor(){

				public boolean visit(IResource resource) throws CoreException {
					if (resource instanceof IFile) {
						
						ImportedFile importedFile = new ImportedFile(newGeneratorConfig, (IFile)resource, basePath);
						importedFiles.add(importedFile);
						
					}
					return true;
				}
				
			});
		}
		
	}



	private void createGeneratorNameTextInput(final Composite container) {
		Label label1 = new Label(container, SWT.NONE);
		label1.setText("Generator name:");
		
		final Text projectText1 = new Text(container, SWT.BORDER);
		
		projectText1.setText(newGeneratorConfig.getGeneratorName());
		
		GridData data1 = new GridData();
		data1.widthHint = 300;
		projectText1.setLayoutData(data1);
		
		
		projectText1.addModifyListener(new ModifyListener(){


			public void modifyText(ModifyEvent event) {

				newGeneratorConfig.setGeneratorName(projectText1.getText());
				
				if(newGeneratorConfig.getGeneratorName().equals("")){
					setErrorMessage("Generator name must be entered.");
					setPageComplete(false);
				}else if(GeneratorFactory.exists(newGeneratorConfig.getProject(), newGeneratorConfig.getGeneratorName())){
					setErrorMessage("A Generator already exists with this name");
					setPageComplete(false);
				}else{
					setErrorMessage(null);
					setPageComplete(true);
				}
				
			}
			
		});
		
		projectText1.setFocus();
		
	}



	private void createProjectTextInput(final Composite container) {
		Label label = new Label(container, SWT.NONE);
		label.setText("Project name:");
		
		final Text projectText = new Text(container, SWT.BORDER);
		
		projectText.setText(newGeneratorConfig.getProject().getName());
		
		GridData data = new GridData();
		data.widthHint = 300;
		projectText.setLayoutData(data);
		
		
		projectText.addModifyListener(new ModifyListener(){

			public void modifyText(ModifyEvent event) {

				newGeneratorConfig.setProject(null);
				
				setPageComplete(false);

				String projectName = projectText.getText();
				
				if(projectName.equals("")){
					setErrorMessage("Project name must be entered.");
					return;
				}
				
				try{
					IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
					
					
					if(project.exists() && !project.isOpen()){
						setErrorMessage("Project does not exist.");
						return;
					}
					
					if(!project.exists()){
						setErrorMessage("Project does not exist.");
						return;
					}
					
					if(project.exists()){
						newGeneratorConfig.setProject(project);
						setErrorMessage(null);
						setPageComplete(true);
					}
					
				}catch(IllegalArgumentException e){
					setErrorMessage("Invalid project path.");
				}
				
			}
			
		});
	}



	public IProject getSelectedProject() {
		return newGeneratorConfig.getProject();
	}



	public String getGeneratorName() {
		return newGeneratorConfig.getGeneratorName();
	}



	public List<ImportedFile> getFilesToImport() {
		return importedFiles;
	}



	public NewGeneratorConfig getNewGeneratorConfig() {
		return newGeneratorConfig;
	}
	
}
