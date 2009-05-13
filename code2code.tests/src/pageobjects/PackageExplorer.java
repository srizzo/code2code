package pageobjects;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.ChildrenControlFinder;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;

import utils.Driver;


public class PackageExplorer {

	public static void rightClick(IProject project, String menuItem) throws Exception {
	 	SWTBotTree swtBotTree = selectProject(project);
		SWTBotMenu generateMenuItem = swtBotTree.contextMenu(menuItem);
		generateMenuItem.click();
	}
	
	
	public static SWTBotTree selectProject(IProject project) throws WidgetNotFoundException {
		SWTBotView packageExplorerView = Driver.bot().view("Package Explorer");
		
		Tree projectsTree = (Tree) new ChildrenControlFinder(packageExplorerView.getWidget()).findControls(WidgetMatcherFactory.widgetOfType(Tree.class)).get(0);
		SWTBotTree swtBotTree = new SWTBotTree(projectsTree);
		swtBotTree.select(project.getName());
		swtBotTree.setFocus();
		return swtBotTree;
		
	}
	
}
