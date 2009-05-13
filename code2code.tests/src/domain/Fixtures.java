package domain;

public class Fixtures {
	public static String expectedGeneratedDir(String generator) {
		return "fixtures/expected/generated/" + generator ;
	}

	public static String exampleGeneratorDir(String generator) {
		return "fixtures/examples/generators/" + generator + ".generator";
	}

}
