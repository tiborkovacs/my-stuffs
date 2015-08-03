package yg0r2.pt;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FilenameUtils;

import yg0r2.util.FileUtil;
import yg0r2.util.StringUtil;


public class HotfixUtil {
	public static List<String> getFixedIssuedFromXml(File descriptionXmlFile)
		throws IOException {

		String content = FileUtil.readFileContent(descriptionXmlFile);

		return _getFixedIssues(content);
	}

	public static List<String> getFixedIssued(File hotfixFile)
		throws IOException {

		InputStream is = new FileInputStream(hotfixFile);

		List<String> fixedIssues;

		try {
			fixedIssues = getFixedIssued(is);
		}
		finally {
			is.close();
		}

		return fixedIssues;
	}

	public static List<String> getFixedIssued(
		InputStream is) throws IOException {

		List<String> fixedIssues = new ArrayList<String>();

		ZipInputStream zipIS = new ZipInputStream(is);

		ZipEntry zipEntry;
		while ((zipEntry = zipIS.getNextEntry()) != null) {
			if (zipEntry.isDirectory()) {
				continue;
			}

			String fileName = FilenameUtils.getName(zipEntry.getName());

			if (fileName.equals("fixpack_documentation.xml")) {
				String content = StringUtil.convertIsToString(zipIS);

				fixedIssues = _getFixedIssues(content);

				break;
			}
		}

		return fixedIssues;
	}

	private static List<String> _getFixedIssues(String content) {
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

}