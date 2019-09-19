package org.Pacificorp.Test;

import org.testng.TestNG;

public class TestRunnerClass {

	public static TestNG  testng;

	public static void main(String[] args) {

		testng = new TestNG();
		testng.setTestClasses(new Class[] { org.Pacificorp.Test.WebXPTest.class });
		testng.run();
	}

}
