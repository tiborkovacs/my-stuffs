package yg0r2.pt;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;

/**
 * @author Yg0R2
 */
public class StoreAllFixCustomerLPE {

	private static final String _hotfixRegex =
		"liferay-hotfix-([0-9]+)-([0-9]{4})\\.zip";
	private static final String[] _portalVersions = new String[] {
		"6012", "6110", "6120", "6130", "6210"
	};

	public static void main(String[] args) throws IOException {
		StoreAllFixCustomerLPE storeAllFixCustomerLPE =
			new StoreAllFixCustomerLPE();

		Map<String, Storage> alma = storeAllFixCustomerLPE._getHotfixFixedIssues(new File("l:\\bundles\\6110-patched\\patching-tool\\patches"));

		System.out.println((alma.get("hotfix-1795")).getFixedIssues());
	}

	public StoreAllFixCustomerLPE() {
		
	}

	public void run() throws IOException {
		String patchesMainDir = "f:\\";

		for (String version : _portalVersions) {
			String hotfixesDir = patchesMainDir + "\\" + version;

			if ((version == "6130") || (version == "6210")) {
				hotfixesDir += "\\hotfix";
			}

			_all.put(version, _getHotfixFixedIssues(new File(hotfixesDir)));
		}
	}

	private Map<String, Storage> _getHotfixFixedIssues(File dir)
		throws IOException {

		Map<String, Storage> hotfixFixedIssues =
			new HashMap<String, Storage>();

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

			List<String> fixedIssues = HotfixUtil.getFixedIssued(f);

			String hotfixBaseName = FilenameUtils.getBaseName(f.getName());
			hotfixBaseName = hotfixBaseName.substring(
				8, hotfixBaseName.length() - 5);

			Storage storage = new Storage(fixedIssues);

			hotfixFixedIssues.put(hotfixBaseName, storage);
		}

		return hotfixFixedIssues;
	}

	private Map<String, Map<String, Storage>> _all;

	class Storage {
		public Storage(List<String> fixedIssues) {
			_fixedIssues = fixedIssues;
		}

		public List<String> getFixedIssues() {
			return _fixedIssues;
		}

		private List<String> _customerTickets = new ArrayList<String>();
		private List<String> _fixedIssues = new ArrayList<String>();
		private List<String> _lpps = new ArrayList<String>();
	}

}