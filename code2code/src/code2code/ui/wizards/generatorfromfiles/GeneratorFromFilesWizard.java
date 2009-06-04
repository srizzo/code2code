package code2code.ui.wizards.generatorfromfiles;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import code2code.core.generatorfromfiles.ImportedFile;
import code2code.utils.EclipseGuiUtils;


public class GeneratorFromFilesWizard extends Wizard implements INewWizard {

	private IStructuredSelection selection;
	private IWorkbench workbench;
	private GeneratorConfigurationPage generatorConfigurationPage;
	private ImportedFilesConfigurationPage importedFilesConfigurationPage;
	
	@Override
	public boolean performFinish() {
		
		final IProject project = generatorConfigurationPage.getSelectedProject();
		String generatorName = generatorConfigurationPage.getGeneratorName();
		
		String selectedExtension = importedFilesConfigurationPage.getSelectedExtension();
		String mainEngineExtension = null;
		
		if(selectedExtension.equals("bin")){
			mainEngineExtension = "txt";
		}else{
			mainEngineExtension = selectedExtension;
		}
		
		
		
		
		
		try {
		
			IFolder generatorsFolder = project.getFolder("generators");
			IFolder generatorFolder = generatorsFolder.getFolder(generatorName + ".generator");

			IFile paramsFile = generatorFolder.getFile("params." + mainEngineExtension);
			InputStream paramsFileContents = new ByteArrayInputStream("".getBytes());

			
			IFile descriptionFile = generatorFolder.getFile("description." + mainEngineExtension);
			InputStream descriptionFileContents = new ByteArrayInputStream("".getBytes());
			
			final StringBuilder templates = new StringBuilder();
			final String ls = System.getProperty("line.separator");
			
			final List<ImportedFile> importedFiles = importedFilesConfigurationPage.getFilesToImport();

			if(!generatorsFolder.exists()){
				generatorsFolder.create(false, true , null);
			}
			
			generatorFolder.create(false, true, null);
			paramsFile.create(paramsFileContents , false, null);
			descriptionFile.create(descriptionFileContents , false, null);
			

			
			for (ImportedFile importedFile : importedFiles) {
				if(importedFile.isSelectedToBeCreated()){
					importedFile.importFile();
					templates.append(importedFile.getOriginDestinationMappingProperty() + ls);
				}
			}
			
			
			IFile templatesFile = generatorFolder.getFile("templates." + mainEngineExtension);
			
			InputStream templatesFileContents = new ByteArrayInputStream(templates.toString().getBytes());
			templatesFile.create(templatesFileContents , false, null);
			
		} catch (Exception e) {
			EclipseGuiUtils.showErrorDialog(workbench.getActiveWorkbenchWindow().getShell(), e);
			throw new RuntimeException(e);
		}
		
		return true;
	}

	
	
	
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
		this.selection = selection;
	}
	
	
	@Override
	public void addPages() {
		
		generatorConfigurationPage = new GeneratorConfigurationPage(selection);
		importedFilesConfigurationPage = new ImportedFilesConfigurationPage(generatorConfigurationPage, selection);
		
		
		addPage(generatorConfigurationPage);
		addPage(importedFilesConfigurationPage);
		
	}
	
	
	
	

	
}
