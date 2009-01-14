---
layout: default
title: code2code
---

# code2code - eclipse plugin
	
## Let code create code

code2code is a plugin for Eclipse 3.4 to generate code from templates you create using popular template engines (currently [Groovy](http://groovy.codehaus.org/Groovy+Templates), [Freemarker](http://freemarker.sourceforge.net/) and [Velocity](http://velocity.apache.org/) are available). It's inspired on (but not equals to) [Rails generators](http://wiki.rubyonrails.org/rails/pages/UnderstandingGenerators).


## Lightweight, easy to work

code2code adds a "Generate..." option to your project context menu ([see](images/screenshots/generate.png)). When clicked, it will let you choose one of your generators ([see](images/screenshots/generatorSelection.png)), ask you the params you've specified ([see](images/screenshots/paramsConfiguration.png)), and then generate the result to the places you've told it ([see](images/screenshots/generatorCustomization.png)).

## Very simple template creation

1. Create the templates using your favorite engine, with its corresponding extensions (*.groovy*, *.ftl*, and *.vm*).
1. Create a *"templates"* file telling the destination of each template when generated.
1. Create a *"params"* file telling the params your templates need.
1. Put them on a folder with the *".generator"* extension.
1. Put your generators on a folder called *"generators"* on you project root.
1. That's all... you can know right click on your project root folder, and access the *"Generate..."* option to generate code using your custom generators.

See a HelloWorld generator example: 


<ul class="directory-structure">
	<li>
		<img src="images/icons/folder.png" class="file-icon"> generators/
		<ul>
			<li>
				<img src="images/icons/folder.png" class="file-icon"> HelloWorld.generator/
				<ul>
					<li>
						<img src="images/icons/folder.png" class="file-icon"> templates/
						<ul>
							<li>
								<img src="images/icons/file.png" class="file-icon"> HelloWorld.java.ftl
							</li>
						</ul>
					</li>
					<li>
						<img src="images/icons/file.png" class="file-icon"> templates.ftl
					</li>
					<li>
						<img src="images/icons/file.png" class="file-icon"> params.ftl
					</li>
				</ul>
			</li>
		</ul>
	</li>
</ul>


The source: 

<div><img src="images/icons/file.png" class="file-icon"> HelloWorld.java.ftl</div>

    public class HelloWorld{
		public static void main(String[] args) {
			System.out.println("Hello ${name}");
		}
    }

<div><img src="images/icons/file.png" class="file-icon"> templates.ftl</div>

    templates/HelloWorld.java.ftl=src/HelloWorld.java


<div><img src="images/icons/file.png" class="file-icon"> params.ftl</div>

    name

## Open Source

code2code is free, released under the [MIT license](http://en.wikipedia.org/wiki/MIT_License).

## Install

Current version is: 0.0.5 Alpha. It's already totally functional, but you can expect limited functionality and not a really beautyful design. Also, you can expect major changes in how it works if needed. 

You can download/install it from our [Update Site](http://srizzo.github.com/code2code/code2code.updatesite)

## Learn

See some [examples](http://github.com/srizzo/code2code.examples)