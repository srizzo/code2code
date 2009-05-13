
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




