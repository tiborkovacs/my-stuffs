package yg0r2.stringstuff;

import java.util.ArrayList;
import java.util.List;

public class GetHotfixFromURL {

	public static void main(String[] args) {
		String testBuildFixPackZipUrl = "http://mirrors/files.liferay.com/private/ee/fix-packs/6.2.10/asset-framework/liferay-fix-pack-asset-framework-1-6210-src.zip";

		String FIX_FULL_NAME = testBuildFixPackZipUrl.replaceAll(".*(liferay-.*-\\d+-\\d+)[\\-src]*\\.zip", "$1");

		String FIX_WITH_VERSION = FIX_FULL_NAME.replaceAll("liferay[\\-fix\\-pack]*-", "");

		String PORTAL_VERSION = FIX_WITH_VERSION.replaceAll(".*-(\\d)(\\d)(\\d*)", "$1.$2.$3");

		String FIX_PACK = FIX_WITH_VERSION.replaceAll("(.*)-(\\d)*", "$1");

		System.out.format("%20s", "testBuildFixPackZipUrl: " + testBuildFixPackZipUrl);
		System.out.println();
		System.out.format("%20s", "FIX_FULL_NAME: " + FIX_FULL_NAME);
		System.out.println();
		System.out.format("%20s", "FIX_WITH_VERSION: " + FIX_WITH_VERSION);
		System.out.println();
		System.out.format("%20s", "PORTAL_VERSION: " + PORTAL_VERSION);
		System.out.println();
		System.out.format("%20s", "FIX_PACK: " + FIX_PACK);
		System.out.println();

/*		List<String> fixes = new ArrayList<String>();
		fixes.add("http://mirrors/files.liferay.com/private/ee/fix-packs/6.2.10/hotfix/liferay-hotfix-4771-6210.zip");
		fixes.add("http://mirrors/files.liferay.com/private/ee/fix-packs/6.2.10/hotfix/liferay-hotfix-4771-6210-src.zip");
		fixes.add("https://files.liferay.com/private/ee/fix-packs/incubation/liferay-fix-pack-business-productivity-5-6210.zip");
		fixes.add("https://files.liferay.com/private/ee/fix-packs/incubation/liferay-fix-pack-business-productivity-5-6210-src.zip");
		fixes.add("https://files.liferay.com/private/ee/fix-packs/6.1.30/platform/liferay-fix-pack-platform-31-6130.zip");
		fixes.add("https://files.liferay.com/private/ee/fix-packs/6.1.30/platform/liferay-fix-pack-platform-31-6130-src.zip");
		fixes.add("https://files.liferay.com/private/ee/fix-packs/6.1.20/security-hotfix/liferay-security-hotfix-3-6120.zip");
		fixes.add("https://files.liferay.com/private/ee/fix-packs/6.1.20/security-hotfix/liferay-security-hotfix-3-6120-src.zip");
		fixes.add("https://files.liferay.com/private/ee/fix-packs/6.1.20/security-hotfix-lsv/liferay-security-hotfix-lsv-37-6120.zip");
		fixes.add("https://files.liferay.com/private/ee/fix-packs/6.1.20/security-hotfix-lsv/liferay-security-hotfix-lsv-37-6120-src.zip");
		fixes.add("https://files.liferay.com/private/ee/fix-packs/6.1.10/core/liferay-fix-pack-core-2-6110.zip");
		fixes.add("https://files.liferay.com/private/ee/fix-packs/6.1.10/core/liferay-fix-pack-core-2-6110-src.zip");

		for (String testBuildFixPackZipUrl : fixes) {
			String FIX_FULL_NAME = testBuildFixPackZipUrl.replaceAll(".*(liferay-.*-\\d+-\\d+)[\\-src]*\\.zip", "$1");

			String FIX_WITH_VERSION = FIX_FULL_NAME.replaceAll("liferay[\\-fix\\-pack]*-", "");

			String PORTAL_VERSION = FIX_WITH_VERSION.replaceAll(".*-(\\d)(\\d)(\\d*)", "$1.$2.$3");

			String FIX_PACK = FIX_WITH_VERSION.replaceAll("(.*)-(\\d)*", "$1");

			System.out.format("%50s", FIX_FULL_NAME);
			System.out.print(", ");
			System.out.format("%30s", FIX_WITH_VERSION);
			System.out.print(", ");
			System.out.format("%5s", PORTAL_VERSION);
			System.out.print(", ");
			System.out.format("%25s", FIX_PACK);
			System.out.println();
		}*/
	}

}
