package domain;

import java.io.ByteArrayInputStream;
import java.net.URI;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;

import code2code.utils.FileUtils;

public class WorkingProject {
	
	private static IProject project;
	
	public static IProject create() throws Exception{
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		String projectName = "code2code.TestProject";
		project = root.getProject(projectName);
		int index = 0;
		while(project.exists()){
			project = root.getProject(projectName + "_" + (++index));
		}
		project.create(null);
		project.open(null);
		return project;
	}

	public static void copyGenerator(String generator) throws Exception {
		URI example = FileLocator.resolve(Platform.getBundle("code2code.tests")
				.getResource(Fixtures.exampleGeneratorDir(generator))).toURI();
	
		URI destination = project.getProject().getFolder("generators").getFolder(generator + ".generator").getLocationURI();
		
		IFileStore sourceStore = EFS.getStore(example);
		IFileStore destinationStore = EFS.getStore(destination);
		
		sourceStore.copy(destinationStore, 0, null);
	
		project.getProject().getFolder("generators").refreshLocal(IResource.DEPTH_INFINITE, null);
	}


	public static IProject project() {
		return project;
	}

	public static void delete() throws Exception {
		if(project != null){
			project.delete(true, true, null);
		}
	}

	public static void createFolder(String folderName) throws Exception {
		FileUtils.createFolderWithParents(project.getFolder(folderName));
	}


	public static void createFileWithContents(String filePath, String content) throws Exception {
		IFile file = project.getFile(filePath);
		FileUtils.createParentFolders(file);
		file.create(new ByteArrayInputStream(content.getBytes()) , true, null);
	}

	public static void createFile(String filePath) throws Exception {
		createFileWithContents(filePath, "");
	}

	public static boolean fileExists(String file) {
		return project.getFile(file).exists();
	}

	public static String read(String file) throws Exception {
		return FileUtils.read(project.getFile(file).getContents());
	}
}
