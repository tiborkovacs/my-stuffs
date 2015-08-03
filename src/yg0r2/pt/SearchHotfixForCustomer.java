package yg0r2.pt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

/**
 * @author Yg0R2
 */
public class SearchHotfixForCustomer {

	public static final String CUSTOMER_NAME = "GWU";
	public static final String PORTAL_VERSION = "6120";
	public static final String REQUIRED_LPE = "LPE-8091";

	public static File FP_DEV_TOOL_DIR = new File("l:/fp-dev-tool");
	public static File FP_DEV_TOOL_CACHE_DIR = new File(
		FP_DEV_TOOL_DIR + "/cache/" + PORTAL_VERSION);
	public static File HOTFIXES_LIST_FILE;
	public static String HOTFIXES_LIST_FILE_URL =
		"http://files.liferay.com/private/ee/fix-packs/development/fix-pack-cache/hotfix-list.txt";
	public static List<String> POSSIBLE_HOTFIXES;

	public static void main(String[] args) throws Exception {
		System.out.println("Update fp-dev-tool..");

		updateFPDevTool();

		System.out.println("Update finished");

		System.out.println("Start..");

		init();
		run();

		System.out.println("Finished.");

		HOTFIXES_LIST_FILE.deleteOnExit();
	}

	public static void init() throws Exception {
		HOTFIXES_LIST_FILE = File.createTempFile("hotfix_list", "tmp");

		FileUtils.copyURLToFile(
			new URL(HOTFIXES_LIST_FILE_URL), HOTFIXES_LIST_FILE);

		POSSIBLE_HOTFIXES = new ArrayList<String>();

		File[] fileList = FP_DEV_TOOL_CACHE_DIR.listFiles();

		for (File f : fileList) {
			if (f.isDirectory()) {
				continue;
			}

			if (!FilenameUtils.getExtension(f.getName()).equals("xml")) {
				continue;
			}

			List<String> fixedIssues = HotfixUtil.getFixedIssuedFromXml(f);

			if (fixedIssues.contains(REQUIRED_LPE)) {
				String hotfixBaseName = FilenameUtils.getBaseName(f.getName());
				hotfixBaseName = hotfixBaseName.substring(
					9, hotfixBaseName.length() - 5);

				POSSIBLE_HOTFIXES.add(hotfixBaseName);
			}
		}
	}

	public static void run() throws Exception {
		BufferedReader br = new BufferedReader(
			new FileReader(HOTFIXES_LIST_FILE));

		String line;
		while ((line = br.readLine()) != null) {
			for (String hotfix : POSSIBLE_HOTFIXES) {
				if (line.matches(".*" + hotfix + ".*" + CUSTOMER_NAME + ".*")) {
					System.out.println(line);

					break;
				}
			}
		}

		br.close();
	}

	public static void updateFPDevTool() {
		String[] command = new String[] {
			"cmd.exe", "/c", FP_DEV_TOOL_DIR + "fp-dev-tool.bat",
			PORTAL_VERSION};

		Runtime runtim = Runtime.getRuntime();

		try {
			Process process = runtim.exec(command);

			process.waitFor();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}