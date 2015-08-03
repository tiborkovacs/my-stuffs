package yg0r2.logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yg0r2.util.FileUtil;



public class Logger {
	public static final String AFTER_METHOD = "AfterMethod";
	public static final String AFTER_TEST = "AfterTest";
	public static final String BEFORE_METHOD = "BeforeMethod";
	public static final String BEFORE_TEST = "BeforeTest";
	public static final String CLEAN_UP_METHOD = "CleanUpMethod";
	public static final String TEST_METHOD = "TestMethod";

	public static final String[] METHODS = new String[] {
		BEFORE_METHOD, TEST_METHOD, AFTER_METHOD, CLEAN_UP_METHOD
	};

	public Logger() {
		StringBuilder footerSB = new StringBuilder(3);
		footerSB.append("</tbody></table>\n");
		footerSB.append("</body>\n");
		footerSB.append("</html>\n");

		StringBuilder headerSB = new StringBuilder(4);
		headerSB.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		headerSB.append("<html>\n");
		headerSB.append("<body>\n");
		headerSB.append("<table><tbody>\n");

		_ouptutFileFooter = footerSB.toString();
		_ouptutFileHeader = headerSB.toString();

		_testCases = new ArrayList<String>();
		_testSuite = new HashMap<String, String>();
	}

	public void append(String msg) throws IOException {
		FileUtil.write(_outputFile, "<!-- " + msg + "-->\n", true);
	}

	public void append(String command, String locator) throws IOException {
		append(command, locator, "");
	}

	public void append(String command, String locator, String value)
		throws IOException {

		if (command.equals("getxPathLocation") || command.startsWith("is")) {
			return;
		}

		if (command.startsWith("get")) {
			command = "store" + command.substring(3);
		}

		StringBuilder sb = new StringBuilder(5);
		sb.append("<tr>\n");
		sb.append("    <td>" + command + "</td>\n");
		sb.append("    <td>" + locator + "</td>\n");
		sb.append("    <td>" + value + "</td>\n");
		sb.append("</tr>\n");

		FileUtil.write(_outputFile, sb.toString(), true);

		/*if (TestPropsValues.SELENIUM_LOGGER_ENABLED) {
			System.out.println(sb.toString());
		}*/
	}

	public void closeFile() throws IOException {
		FileUtil.write(_outputFile, _ouptutFileFooter, true);
	}

	public void createTestSuiteFile(String testSute) throws IOException {
		File testSuiteFile = new File(_baseDir + testSute + ".html");

		FileUtil.write(testSuiteFile, _ouptutFileHeader);

		if (_testSuite.containsKey(BEFORE_TEST)) {
			StringBuilder sb = new StringBuilder(5);
			sb.append("<tr><td><a href=\"");
			sb.append(_testSuite.get(BEFORE_TEST));
			sb.append("\">");
			sb.append(BEFORE_TEST);
			sb.append("</a></td></tr>\n");

			FileUtil.write(testSuiteFile, sb.toString(), true);
		}

		for (String testCase : _testCases) {
			for (String method : METHODS) {
				String mapKey = testCase + method;

				if (_testSuite.containsKey(mapKey)) {
					StringBuilder sb = new StringBuilder(5);
					sb.append("<tr><td><a href=\"");
					sb.append(_testSuite.get(mapKey));
					sb.append("\">");
					sb.append(method);
					sb.append("</a></td></tr>\n");

					FileUtil.write(testSuiteFile, sb.toString(), true);

					_testSuite.remove(mapKey);
				}
			}
		}

		if (_testSuite.containsKey(AFTER_TEST)) {
			StringBuilder sb = new StringBuilder(5);
			sb.append("<tr><td><a href=\"");
			sb.append(_testSuite.get(AFTER_TEST));
			sb.append("\">");
			sb.append(AFTER_TEST);
			sb.append("</a></td></tr>\n");

			FileUtil.write(testSuiteFile, sb.toString(), true);
		}

		FileUtil.write(testSuiteFile, _ouptutFileFooter, true);
	}

	public void useAfterMethod(String testCase) throws IOException {
		String fileName = testCase + "/" + AFTER_METHOD + ".html";

		_outputFile = new File(_baseDir + fileName);

		FileUtil.write(_outputFile, _ouptutFileHeader);

		_testCases.add(testCase);
		_testSuite.put(testCase + AFTER_METHOD, fileName);
	}

	public void useAfterTest() throws IOException {
		String fileName = AFTER_TEST + ".html";

		_outputFile = new File(_baseDir + fileName);

		FileUtil.write(_outputFile, _ouptutFileHeader);

		_testSuite.put(AFTER_TEST, fileName);
	}

	public void useBeforeMethod(String testCase) throws IOException {
		String fileName = testCase + "/" + BEFORE_METHOD + ".html";

		_outputFile = new File(_baseDir + fileName);

		FileUtil.write(_outputFile, _ouptutFileHeader);

		_testCases.add(testCase);
		_testSuite.put(testCase + BEFORE_METHOD, fileName);
	}

	public void useBeforeTest() throws IOException {
		String fileName = BEFORE_TEST + ".html";

		_outputFile = new File(_baseDir + fileName);

		FileUtil.write(_outputFile, _ouptutFileHeader);

		_testSuite.put(BEFORE_TEST, fileName);
	}

	public void useCleanUpMethod(String testCase) throws IOException {
		String fileName = testCase + "/" + CLEAN_UP_METHOD + ".html";

		_outputFile = new File(_baseDir + fileName);

		FileUtil.write(_outputFile, _ouptutFileHeader);

		_testCases.add(testCase);
		_testSuite.put(testCase + CLEAN_UP_METHOD, fileName);
	}

	public void useTestMethod(String testCase) throws IOException {
		String fileName = testCase + "/" + TEST_METHOD + ".html";

		_outputFile = new File(_baseDir + fileName);

		FileUtil.write(_outputFile, _ouptutFileHeader);

		_testCases.add(testCase);
		_testSuite.put(testCase + TEST_METHOD, fileName);
	}

	//private static String _baseDir = TestPropsValues.OUTPUT_DIR + "/";
	private static String _baseDir = "w:/alma/";

	private File _outputFile;
	private String _ouptutFileFooter;
	private String _ouptutFileHeader;
	private List<String> _testCases;
	private Map<String, String> _testSuite;

}