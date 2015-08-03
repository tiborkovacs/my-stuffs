package yg0r2.logger;

import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestSuite {

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
		System.out.println("BeforeSuite");

		_logger = new Logger();
	}

	@BeforeTest(alwaysRun = true)
	public void beforeTest() throws Exception {
		System.out.println("BeforeTest Started");

		_logger.useBeforeTest();
		try {
			_logger.append("command", "locator", "value");

			Thread.sleep(10);

			System.out.println("BeforeTest Finished");
		}
		catch (Exception e) {
			System.out.println("BeforeTest Failed");

			throw e;
		}
		finally {
			_logger.closeFile();
		}
	}

	@BeforeMethod
	public void beforeMethod(Method method) throws Exception {
		System.out.println("BeforeMethod '" + method.getName() + "' Started");

		_logger.useBeforeMethod(method.getName());
		try {
			_logger.append("command", "locator", "value");

			Thread.sleep(10);

			System.out.println("BeforeMethod '" + method.getName() + "' Finished");
		}
		catch (Exception e) {
			System.out.println("BeforeMethod '" + method.getName() + "' Failed");

			throw e;
		}
		finally {
			_logger.closeFile();
		}
	}

	@Test
	public void testMethod1() throws Exception {
		System.out.println("TestMethod '" + "testMethod1" + "' Starged");

		_logger.useTestMethod("testMethod1");
		try {
			_logger.append("command", "locator", "value");

			Thread.sleep(10);

			System.out.println("TestMethod '" + "testMethod1" + "' Finished");
		}
		catch (SkipException ske) {
			System.out.println("TestMethod '" + "testMethod1" + "' Skipped");
		}
		catch (Exception e) {
			System.out.println("Test Method '" + "testMethod1" + "' Failed");

			throw e;
		}
		finally {
			_logger.closeFile();
		}
	}

	@Test
	public void testMethod2() throws Exception {
		System.out.println("TestMethod '" + "testMethod2" + "' Starged");

		_logger.useTestMethod("testMethod2");
		try {
			_logger.append("command", "locator", "value");

			Thread.sleep(10);

			if (true) {throw new Exception("alma");}

			System.out.println("TestMethod '" + "testMethod2" + "' Finished");
		}
		catch (SkipException ske) {
			System.out.println("TestMethod '" + "testMethod2" + "' Skipped");
		}
		catch (Exception e) {
			System.out.println("Test Method '" + "testMethod2" + "' Failed");

			throw e;
		}
		finally {
			_logger.closeFile();
		}
	}

	@AfterMethod
	public void afterMethod(ITestResult result, Method method)
		throws Exception {

		int testResult = result.getStatus();

		try {
			if ((testResult == ITestResult.SKIP) ||
				(testResult == ITestResult.SUCCESS)) {

				System.out.println("AfterMethod '" + method.getName() + "' Started");

				_logger.useAfterMethod(method.getName());

				_logger.append("command", "locator", "value");

				Thread.sleep(10);

				System.out.println("AfterMethod '" + method.getName() + "' Finished");
			}
		}
		catch (Exception e) {
			testResult = ITestResult.FAILURE;

			System.out.println("AfterMethod '" + method.getName() + "' Failed");

			throw e;
		}
		finally {
			_logger.closeFile();

			if (testResult == ITestResult.FAILURE) {
				System.out.println("CleanUpMethod '" + method.getName() + "' Started");

				_logger.useCleanUpMethod(method.getName());

				try {
					_logger.append("command", "locator", "value");

					Thread.sleep(10);

					System.out.println("CleanUpMethod '" + method.getName() + "' Finished");
				}
				catch (Exception e) {
					System.out.println("CleanUpMethod '" + method.getName() + "' Failed");

					throw e;
				}
				finally {
					_logger.closeFile();
				}
			}
		}

	}

	@AfterTest(alwaysRun = true)
	public void afterTest() throws Exception {
		System.out.println("AfterTest Started");

		_logger.useAfterTest();
		try {
			_logger.append("command", "locator", "value");

			Thread.sleep(10);

			System.out.println("AfterTest Finished");
		}
		finally {
			_logger.closeFile();
		}
	}

	@AfterSuite(alwaysRun = true)
	public void afterSuit(ITestContext testContext) throws Exception {
		_logger.createTestSuiteFile(testContext.getName());
	}

	private static Logger _logger;

}