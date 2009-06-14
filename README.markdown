
# What is it

code2code is an eclipse plugin to generate code from templates. It allows Rails generators like functionality on java (or whatever) projects. Its main goal is easy generator creation by using known template engines, such as Freemarker, Velocity and Groovy Template Engine and very few configuration. See it in action  [here](http://elsethenif.wordpress.com/2009/06/12/quickly-cruding-with-code2code-plugin-and-vraptor2/).

# A Generator Example

Generator structure:

    generators
    |-- GerenatorName.generator
        |-- templates
            |-- SomeTeplate.txt.ftl
            ...
        |-- templates.ftl
        |-- params.ftl


SomeTemplate.txt.ftl:

    Hello ${name}!!!
    ...


templates.ftl:

    templates/SomeTemplate.txt.ftl=src/DestinationFile.txt
    ...

params.ftl:

    name=some default value
    ...


# Usage

code2code adds a “Generate…” option to your project context menu. When clicked, it will let you choose one of your generators (on the generator folder), ask you the params you’ve specified (the params file), and then generate the result to the places you’ve told it (at the templates file).

# Installation

Current version is 0.9.1.1 beta, tested against Eclipse Ganymede 3.4.2.

You can install it from the [Update Site](http://srizzo.github.com/code2code/updatesite)

# Documentation

[http://wiki.github.com/srizzo/code2code](http://wiki.github.com/srizzo/code2code)

