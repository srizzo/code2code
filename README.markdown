
# What it is

code2code is an eclipse plugin to generate source code from templates.

You can easily create your custom generators using known template engines: Groovy (JSP like), Freemarker or Velocity.

See it in action  [here](http://elsethenif.wordpress.com/2009/06/12/quickly-cruding-with-code2code-plugin-and-vraptor2/).

# Creating a Generator

The files:

    HelloWorld.generator
      |-- templates
          |-- HelloWorldTemplate.txt.ftl
      |-- templates.ftl
      |-- params.ftl


Contents of HelloWorldTemplate.txt.ftl:

    Hello ${name}!!!


Contents of params.ftl:

    name=World


Contents of templates.ftl:

    templates/HelloWorldTemplate.txt.ftl=src/destinationPath/HelloWorld.txt



# Usage

<strong>Installing generators:</strong> just create a folder named "generators" at the root of your project, and place your generators there.

<strong>Running generators</strong>: The plugin adds a “Generate…” option to your project context menu (right-click menu). Run it, choose one of your installed generators, set its parameters, and the plugin will generate the files to the right place.

See it in action here: [Quickly cruding with code2code plugin and VRaptor2](http://elsethenif.wordpress.com/2009/06/12/quickly-cruding-with-code2code-plugin-and-vraptor2/).


# Installation

Current version is 0.9.2 beta, tested against Eclipse Ganymede 3.4.2.

You can install it from the [Update Site](http://srizzo.github.com/code2code/updatesite)


# Documentation

[http://wiki.github.com/srizzo/code2code](http://wiki.github.com/srizzo/code2code)


# Issues/Features

[http://github.com/srizzo/code2code/issues](http://github.com/srizzo/code2code/issues)


# Generator Examples

[http://github.com/srizzo/code2code-example-generators/downloads](http://github.com/srizzo/code2code-example-generators/downloads)
