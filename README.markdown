
# What is it

code2code is an eclipse plugin to allow Rails generators like functionality on java (or other) projects. Its main goal is to allow anyone to create very easily custom generators.

# A Generator Example

Structure:

    generators/
      GerenatorName.generator/
        templates/
          SomeTeplate.txt.ftl
          ...
        templates.ftl
        params.ftl


SomeTemplate.txt.ftl:

    Hello ${name}!!!


templates.ftl:

    templates/SomeTemplate.txt.ftl=src/DestinationFile.txt
    ...

params.ftl:

    name=some default value
    ...


# Usage

code2code adds a “Generate…” option to your project context menu. When clicked, it will let you choose one of your generators (on the generator folder of your project), ask you the params you’ve specified (on the params file from your generator), and then generate the result to the places you’ve told it (on the templates file of your generator).

# Template engines

code2code will process each file through a template engine, indicated by its extension (.groovy = Groovy, .ftl = Freemarker and .vm = Velocity)


# Installation

Current version is: 0.0.6 Alpha. It's already totally functional, but you can expect limited functionality and not a really beautiful layout. Also, you can expect major changes in how it works if needed. 

You can download/install it from the [Update Site](http://srizzo.github.com/code2code/code2code.updatesite)



