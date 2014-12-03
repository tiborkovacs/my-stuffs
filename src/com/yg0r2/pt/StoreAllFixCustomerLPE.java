package com.yg0r2.pt;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FilenameUtils;

import com.yg0r2.util.FileUtil;

/**
 * @author Yg0R2
 */
public class StoreAllFixCustomerLPE {

	private static final String _documentationFileName =
		"fixpack_documentation.xml";
	private static final String _hotfixRegex =
		"liferay-hotfix-([0-9]+)-([0-9]{4})\\.zip";
	private static final String[] _portalVersions = new String[] {
		"6012", "6110", "6120", "6130", "6210"
	};

	public static void main(String[] args) throws IOException {
		StoreAllFixCustomerLPE storeAllFixCustomerLPE =
			new StoreAllFixCustomerLPE();

		Map<String, List<String>> alma = storeAllFixCustomerLPE.getHotfixFixedIssues(new File("l:\\bundles\\6120\\patching-tool\\patches"));

		System.out.println("valami");
	}

	public StoreAllFixCustomerLPE() {
		
	}

	public void run () throws IOException {
		String patchesMainDir = "f:\\";

		for (String version : _portalVersions) {
			String hotfixesDir = patchesMainDir + "\\" + version;

			if ((version == "6130") || (version == "6210")) {
				hotfixesDir += "\\hotfix";
			}

			_all.put(version, getHotfixFixedIssues(new File(hotfixesDir)));
		}
	}

	protected Map<String, List<String>> getHotfixFixedIssues(File dir)
		throws IOException {

		Map<String, List<String>> hotfixFixedIssues =
			new HashMap<String, List<String>>();

		Pattern hotfixPattern = Pattern.compile(_hotfixRegex);

		File[] fileList = dir.listFiles();

		for (File f : fileList) {
			if (f.isDirectory()) {
				continue;
			}

			Matcher hotfixMatcher = hotfixPattern.matcher(f.getName());

			if (!hotfixMatcher.find()) {
				continue;
			}

			List<String> fixedIssues = new ArrayList<String>();

			ZipInputStream zipIS = new ZipInputStream(new FileInputStream(f));

			ZipEntry zipEntry;
			while ((zipEntry = zipIS.getNextEntry()) != null) {
				if (zipEntry.isDirectory()) {
					continue;
				}

				String fileName = FilenameUtils.getName(zipEntry.getName());

				if (fileName.equals(_documentationFileName)) {
					String content = FileUtil.readZipFileEntryContent(
						new ZipFile(f.getAbsolutePath()), zipEntry);

					fixedIssues = getFixedIssues(content);

					break;
				}
			}

			String hotfixBaseName = FilenameUtils.getBaseName(f.getName());

			hotfixFixedIssues.put(hotfixBaseName, fixedIssues);
		}

		return hotfixFixedIssues;
	}

	protected List<String> getFixedIssues(String content) {
		List<String> fixedIssues = new ArrayList<String>();

		Pattern fixedIssuesPattern = Pattern.compile(
			"<fixed-issues>(.*?)</fixed-issues>");

		Matcher fixedIssuesMatcher = fixedIssuesPattern.matcher(content);

		if (fixedIssuesMatcher.find()) {
			String lpes = fixedIssuesMatcher.group(1);

			for (String lpe : lpes.split(",")) {
				fixedIssues.add(lpe);
			}
		}

		return fixedIssues;
	}

	private Map<String, Map<String, List<String>>> _all;

}