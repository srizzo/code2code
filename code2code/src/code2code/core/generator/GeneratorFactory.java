package code2code.core.generator;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.PlatformUI;

import code2code.core.templateengine.TemplateEngineFactory;
import code2code.utils.EclipseGuiUtils;


public class GeneratorFactory {

	
	public static Set<Generator> fromProject(IProject project) throws Exception {
		
		final Set<Generator> generators = new TreeSet<Generator>(new Comparator<Generator>(){
			public int compare(Generator o1, Generator o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		
		IFolder defaultGeneratorsFolder = project.getFolder("generators");
		
		if(!defaultGeneratorsFolder.exists()){
			return new HashSet<Generator>();
		}
		
		
		IResourceVisitor visitor = new IResourceVisitor(){
			public boolean visit(IResource resource) throws CoreException {

				if(resource.getType() == IResource.FOLDER){
					
					IFolder folder = (IFolder)resource;
					
					if(folder.getName().matches(".+\\.generator$") && TemplateEngineFactory.findKnownTemplate(folder, "templates") != null){
						
						try {
							generators.add(Generator.fromFolder(folder));
						} catch (Exception e) {
							EclipseGuiUtils.showErrorDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), e);
							throw new RuntimeException(e);
						}
					}
				}

				return true;
				
			}
		};
		
		defaultGeneratorsFolder.accept(visitor);
		
		return generators;
	}

	public static boolean exists(IProject project, String name) {
		IFolder defaultGeneratorsFolder = project.getFolder(new Path("generators"));
		return defaultGeneratorsFolder.exists(new Path(name + ".generator"));
	}
	
}
