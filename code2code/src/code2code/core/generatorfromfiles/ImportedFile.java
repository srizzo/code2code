package code2code.core.generatorfromfiles;

import java.io.ByteArrayInputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IPath;

import code2code.core.templateengine.TemplateEngine;
import code2code.core.templateengine.TemplateEngineFactory;
import code2code.utils.FileUtils;


public class ImportedFile {

	private final IFile importedFile;
	private IPath destinationPath;
	private final NewGeneratorConfig newGeneratorConfig;
	private boolean selectedToBeCreated = true;
	private String generatorRelativeDestinationPath;
	private IFolder generatorFolder;
	private String extension = "groovy";

	
	public ImportedFile(NewGeneratorConfig newGeneratorConfig, IFile importedFile, IPath basePath) {

		this.newGeneratorConfig = newGeneratorConfig;
		this.importedFile = importedFile;
		
		IFolder generatorsFolder = newGeneratorConfig.getProject().getFolder("generators");
		generatorFolder = generatorsFolder.getFolder(newGeneratorConfig.getGeneratorName() + ".generator");

		generatorRelativeDestinationPath = "templates/" + importedFile.getProjectRelativePath().removeFirstSegments(basePath.segmentCount());
	}


	public void importFile() throws Exception {

		destinationPath = generatorFolder.getProjectRelativePath().append(calculateGeneratorRelativeFinalDestinationPath());
		
		IFile file = newGeneratorConfig.getProject().getFile(destinationPath);
		FileUtils.createParentFolders(file);
		
		if(!extension.equals("")){
			String contents = FileUtils.read(((IFile) importedFile).getContents());
			TemplateEngine templateEngine = TemplateEngineFactory.forExtension(extension);
			String escaped = templateEngine.escape(contents);
			file.create(new ByteArrayInputStream(escaped.getBytes()), false, null);
		}else{
			file.create(importedFile.getContents(), false, null);
		}
		
	}

	public String getOriginDestinationMappingProperty() {
		return calculateGeneratorRelativeFinalDestinationPath() + "=" + importedFile.getProjectRelativePath();
	}

	public IFile getImportedFile() {
		return importedFile;
	}
	
	public String getGeneratorRelativeDestinationPath() {
		return generatorRelativeDestinationPath;
	}


	public void setSelectedToBeCreated(boolean selectedToBeCreated) {
		this.selectedToBeCreated = selectedToBeCreated;
	}
	
	public boolean isSelectedToBeCreated() {
		return selectedToBeCreated;
	}


	public void setGeneratorRelativeDestinationPath(String generatorRelativeDestinationPath) {
		this.generatorRelativeDestinationPath = generatorRelativeDestinationPath;
	}


	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	private String calculateExtension(){
		if(extension.equals("")){
			return "";
		}else{
			return "." + extension;
		}
	}
	
	private String calculateGeneratorRelativeFinalDestinationPath(){
		return generatorRelativeDestinationPath + calculateExtension();
	}
	
}
