package code2code.core.generatorfromfiles;

import org.eclipse.core.resources.IProject;

public class NewGeneratorConfig {

	private IProject project;
	private String generatorName;

	public IProject getProject() {
		return project;
	}

	public void setProject(IProject project) {
		this.project = project;
	}

	public String getGeneratorName() {
		return generatorName;
	}

	public void setGeneratorName(String generatorName) {
		this.generatorName = generatorName;
	}

}
