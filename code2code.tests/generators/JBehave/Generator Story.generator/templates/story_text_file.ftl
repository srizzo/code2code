Story: ${story}

	As a Developer
	I want to ....
	So that ...



Scenario: ${story}

	Given I have a new project
	When I create the generator folder "generators/${generatorName}.generator"
	And I create the template file "generators/${generatorName}.generator/templates/${generatedFileName?capitalize?replace(" ", "")}.${generatorEngine}" with contents:
	  	...
	And I create the templates configuration file "generators/${generatorName}.generator/templates.${generatorEngine}" with contents:
	  	templates/${generatedFileName?capitalize?replace(" ", "")}.${generatorEngine}=${generatedFileName}
	And I create the params configuration file "generators/${generatorName}.generator/params.txt" with contents:
		...
	And I right click the project on the Package Explorer and select "Generate..."
	And I select the "${generatorName}" generator and click "Next"
	And I finish the "Generate Files" wizard
	Then I will see the file "${generatedFileName}" generated with content:
	  	...

