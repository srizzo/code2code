templates/story_text_file.ftl=${destination}/${story?lower_case?replace(" ", "_")}
templates/StoryJavaClass.java.ftl=${destination}/${story?capitalize?replace(" ", "")}.java
templates/TestSuite.java.ftl