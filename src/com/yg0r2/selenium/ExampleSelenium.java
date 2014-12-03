package com.yg0r2.selenium;

import com.thoughtworks.selenium.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ExampleSelenium {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium(
			"localhost", 14444, "*chrome", "http://localhost:8080");

		selenium.start();
	}

	@Test
	public void test01() throws Exception {
		String baseURL = "http://localhost:8080";

		selenium.open(baseURL + "/web/guest/home");
		selenium.clickAt("link=Sign In", "Sign In");
		selenium.waitForPageToLoad("30000");

		assertTrue(selenium.getLocation().startsWith("http://localhost:8080"));
		Thread.sleep(5000);

		baseURL = "http://localhost:9090";

		selenium.open(baseURL + "/web/guest/home");
		selenium.clickAt("link=Sign In", "Sign In");
		selenium.waitForPageToLoad("30000");

		assertTrue(selenium.getLocation().startsWith("http://localhost:9090"));
		Thread.sleep(5000);
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
