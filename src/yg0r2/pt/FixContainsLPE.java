package yg0r2.pt;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import yg0r2.util.ListUtil;

import jcifs.smb.SmbFile;

/**
 * @author Yg0R2
 */
public class FixContainsLPE {

	public static void main(String[] args) throws IOException, URISyntaxException {
		List<String> contains = ListUtil.split("LPE-8859");
		List<String> notContains = ListUtil.split("");

		FixContainsLPE fixContainsLPE = new FixContainsLPE();

		System.out.println("Start");

		fixContainsLPE.search(contains, notContains);

		System.out.println("Done");
	}

	public void search(List<String> contains, List<String> notContains)
		throws IOException, URISyntaxException {

		SmbFile r2d2SMB = new SmbFile(
			"smb://192.168.211.1/data/Support/Fix Packs/6120/");

		Pattern hotfixPattern = Pattern.compile(
			"liferay-(.*?)-([0-9]+)-([0-9]{4})\\.zip");

		SmbFile[] fixesList = r2d2SMB.listFiles();
		for (SmbFile fix : fixesList) {
			if (fix.isDirectory()) {
				continue;
			}

			Matcher hotfixMatcher = hotfixPattern.matcher(fix.getName());

			if (!hotfixMatcher.find()) {
				continue;
			}

			List<String> fixedIssues = HotfixUtil.getFixedIssued(
				fix.getInputStream());

			if (ListUtil.containsAll(fixedIssues, contains) &&
				ListUtil.notContainsAll(fixedIssues, notContains)) {

				System.out.println(fix.getName());
			}
		}
	}

}