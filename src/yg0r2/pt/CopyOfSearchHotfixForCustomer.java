package yg0r2.pt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

/**
 * @author Yg0R2
 */
public class CopyOfSearchHotfixForCustomer {

	public static final String CUSTOMER_NAME = "BCBSAL";
	public static final String PORTAL_VERSION = "6110";
	public static final String REQUIRED_LPE = "LPE-7933";

	public static void main(String[] args) throws Exception {
		init();

		run();
	}

	public static void init() throws Exception {

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

	private static String _BASE_URL = "http://r2d2/~bzz/filelist/";
	private static String HOTFIXES_LIST_FILE_URL =
		_BASE_URL + "hotfix-list.txt";
	private static String _DOCUMENTATIONS_URL = _BASE_URL + PORTAL_VERSION;

}