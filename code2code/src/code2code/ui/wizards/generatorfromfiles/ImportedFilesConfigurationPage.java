package code2code.ui.wizards.generatorfromfiles;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import code2code.core.generatorfromfiles.ImportedFile;
import code2code.core.generatorfromfiles.NewGeneratorConfig;
import code2code.utils.EclipseGuiUtils;


public class ImportedFilesConfigurationPage   extends WizardPage{

	private List<ImportedFile> importedFiles;
	private NewGeneratorConfig newGeneratorConfig;
	private final IStructuredSelection selection;
	private Composite container;
	private ImportedFilesConfigurationComposite contents;
	private Composite parent;

	public ImportedFilesConfigurationPage(GeneratorConfigurationPage generatorConfigurationPage, IStructuredSelection selection) {
		super("Imported Files Configuration");
		this.selection = selection;
		
		newGeneratorConfig = generatorConfigurationPage.getNewGeneratorConfig();
		
		setPageComplete(false);
	}
	


	public void createControl(Composite parent) {
		this.parent = parent;
		container = new Composite(parent, SWT.NULL);
		container.setLayout(new FillLayout(SWT.VERTICAL| SWT.HORIZONTAL));
		
		setControl(container);
	}
	
	
	@Override
	public void setVisible(boolean visible) {

		if (visible) {
			try {
				createContents();
			} catch (Exception e) {
				EclipseGuiUtils.showErrorDialog(container.getShell(), e);
				throw new RuntimeException(e);
			}
		}else{
			setPageComplete(false);
		}
		
		super.setVisible(visible);
	}
	
	
	@SuppressWarnings("unchecked")
	private void createContents() throws Exception {
		
		if (contents != null) {
			contents.dispose();
		}
		
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
		
		
		contents = new ImportedFilesConfigurationComposite(importedFiles, container, SWT.NONE);
		
		setPageComplete(true);
		
		container.pack();
		parent.layout();
		
		container.setVisible(true);
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



	public String getSelectedExtension() {
		return contents.getSelectedExtension();
	}
	
}
