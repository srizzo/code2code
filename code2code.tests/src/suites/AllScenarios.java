package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import stories.creatinggenerators.CreateCompositeGenerators;
import stories.creatinggenerators.CreateGenerators;
import stories.creatinggenerators.SetDescriptionToGenerators;
import stories.creatinggenerators.SetParamsForGenerators;
import stories.creatinggenerators.SetTemplateEngineForTemplateFiles;
import stories.creatinggenerators.SetTemplateEngineForTheTemplatesConfigFile;
import stories.creatinggenerators.SetTemplatesToBeGenerated;
import stories.creatinggenerators.UseTemplateEngines;
import stories.usingthegenerator.ChooseFilesThatWillBeGenerated;
import stories.usingthegenerator.GenerateFiles;
import stories.usingthegenerator.GenerateTemplatesToConsole;
import stories.usingthegenerator.LogGenerationToConsole;
import stories.usingthegenerator.ParamsReferencingOtherParams;
import stories.usingthegenerator.PreviewTheOutput;
import stories.usingthegenerator.SetParamsForGeneration;
import stories.usingthegenerator.SetTemplatesDestinations;
import stories.usingthegenerator.UseGlobalParams;


@RunWith(Suite.class)
@SuiteClasses({
		CreateGenerators.class,
		SetParamsForGenerators.class,
		ChooseFilesThatWillBeGenerated.class,
		UseTemplateEngines.class,
		SetParamsForGeneration.class,
		SetTemplatesDestinations.class,
		SetDescriptionToGenerators.class,
		PreviewTheOutput.class,
		UseGlobalParams.class,
		GenerateTemplatesToConsole.class,
		LogGenerationToConsole.class,
		ParamsReferencingOtherParams.class,
		SetTemplatesToBeGenerated.class,
		CreateCompositeGenerators.class,
		GenerateFiles.class,
		SetTemplateEngineForTemplateFiles.class,
		SetTemplateEngineForTheTemplatesConfigFile.class



})
public class AllScenarios {
	
}
