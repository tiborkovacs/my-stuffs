package yg0r2.stringstuff;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * @author Yg0R2
 */
public class RegexTests {

	public static void main(String[] args) {
		//fixpack();

		//String s = "//div[@class='diagram-builder-drop-container']/div[contains(@class,'form-builder-field') and div/label[.='${ddmFieldName?replace('_',' ')}']]|//div[@class='diagram-builder-drop-container']/div[contains(@class,'form-builder-field') and div/label[@for='fields_field_${ddmFieldName}']]";
		//System.out.println(s.replaceAll("\\$\\{(.*?)(\\?.*?)*\\}", "$1"));

		System.out.println(StringEscapeUtils.unescapeJava("[^a-z\\\\\\\\d]"));
		System.out.println("11126".replaceAll("[^a-zd]", "-"));

		getNormalizedFriendlyURL();
	}

	public static void getNormalizedFriendlyURL() {
		String friendlyURL = "123456";

		if (friendlyURL.startsWith("/")) {
			friendlyURL = friendlyURL.substring(1);
		}

		friendlyURL = friendlyURL.toLowerCase();
		friendlyURL = friendlyURL.replaceAll("[^a-z\\d]", "-");

		while(friendlyURL.contains("--")) {
			friendlyURL = friendlyURL.replace("--", "-");
		}

		System.out.println("/" + friendlyURL);
	}

	public static void fixpack() {
		/*String a = "https://files.liferay.com/private/ee/fix-packs/incubation/liferay-fix-pack-portal-40-6130-build2-src.zip";
		System.out.println("1: " + a.replaceAll("[\\-build\\d+]*", ""));
		System.out.println("2: " + a.replaceAll("liferay-.*-\\d+-\\d+[\\-build\\d+]*", ""));
		System.out.println("3: " + a.replaceAll(".*(liferay-.*-\\d+-\\d+.*?)[\\-src]*\\.zip", "$1"));

		System.out.println("4: " + a.replaceAll(".*(liferay-.*-\\d+-\\d+[\\-build\\d+]*?)[\\-src]*\\.zip", "$1"));*/


		String[] fixes = new String[] {
			"https://files.liferay.com/private/ee/fix-packs/6.1.30/administration/liferay-fix-pack-administration-1-6130-src.zip",
			"https://files.liferay.com/private/ee/fix-packs/6.1.30/administration/liferay-fix-pack-administration-1-6130.zip",
			"https://files.liferay.com/private/ee/fix-packs/6.1.30/administration/liferay-fix-pack-administration-11-6130.zip",
			"https://files.liferay.com/private/ee/fix-packs/incubation/liferay-fix-pack-portal-51-6130-build1.zip",
			"https://files.liferay.com/private/ee/fix-packs/incubation/liferay-fix-pack-portal-40-6130-build233-src.zip"
		};

		for (String FIX_PACK_ZIP_URL : fixes) {
			String FIX_PACK_NAME = FIX_PACK_ZIP_URL.replaceAll(".*(liferay-.*-\\d+-\\d+(\\-build\\d+)*)(\\-src)*\\.zip", "$1");
			System.out.println(FIX_PACK_NAME);

			if (FIX_PACK_NAME.matches(".*build\\d+")) {
				String FIX_PACK = FIX_PACK_NAME.replaceAll("liferay(\\-fix\\-pack)*-(.*)", "$2");
				System.out.println(FIX_PACK);
			}
			else {
				String FIX_PACK = FIX_PACK_NAME.replaceAll("liferay(\\-fix\\-pack)*-(.*\\d+)-\\d+", "$2");
				System.out.println(FIX_PACK);
			}

			String PORTAL_VERSION = FIX_PACK_NAME.replaceAll(".*-(\\d)(\\d)(\\d+)(\\-build\\d+)*", "$1.$2.$3");
			System.out.println(PORTAL_VERSION);

			System.out.println(":" + FIX_PACK_ZIP_URL.replaceAll(".*?(liferay-)*(fix-pack-)*(.*?)-6130(-build\\d+)*(-src)*(\\.zip)*", "$3"));
		}
	}

	public static void xPaht() {
		System.out.println("DOCUMENTS_AND_MEDIA_FIELD_ENTRY_a_BUTTON".matches("DOCUMENTS_AND_MEDIA_FIELD_ENTRY_.*?_BUTTON"));


		System.out.println("------------------------------------------");

		String jobName = "test-deprecated-portal-fixpack-frontend[asset-publisher](ee-6.1.10..ee-6.1.30)";
		String regexPattern = ".*[^\\[]+\\[[a-z\\-]+\\].*";
		String replacePattern = "\\[[a-z\\-]+\\]";

		System.out.println(jobName.matches(regexPattern));

		System.out.println(jobName.replaceAll(replacePattern, "[component.name]"));

		System.out.println("//li/div[div[contains(.,'${pageName}')]]/div[input[//li/div[div[contains(.,'${pageName}')]]/div/input]]".matches(".*/div\\[input.*?\\]"));

		String s = "//li/div[div[contains(.,'${pageName}')]]/div[input[//li/div[div[contains(.,'${pageName}')]]/div/input]]";
		String regex = "/div\\[((/)*input.*)\\]";

		System.out.println(s.matches(regex));

		System.out.println(s.replaceAll(regex, "/$1"));

		System.out.println("ezt");
		System.out.println("//div/div[input]".replaceAll("/div\\[((/)*input.*)\\]", "/div/$1"));
		String s1 = "//div/div[input]";
		String regex1 = "/div\\[((/)*input.*)\\]" ;

		System.out.println(s1.matches(".*" + regex1));
		System.out.println(s1.replaceAll(regex1, "/div/$1"));

		Pattern statementPattern = Pattern.compile("(.*)\\?(.*?)\\((.*)\\)");
		Matcher matcher = statementPattern.matcher("locator?replaceAll('/div[((/)*input.*)]','/div/$1')");
		System.out.println(matcher.matches());

		Pattern p = Pattern.compile("\\$\\{([^\\}\\{]*?)\\}");
		Matcher m = p.matcher("${locator?replaceAll('/div[((/)*input.*)]','/div/$1')}");
		System.out.println(m.matches());

		Pattern p1 = Pattern.compile("((?:(?!AND|OR|FALSE|TRUE).)*)");
		Matcher m1 = p1.matcher("!6130 OR (${apple} OR (kiskutya AND FALSE))");
		System.out.println("m1: " + m1.matches());

		System.out.println("]]/div/div[input]".matches(".*/div\\[(/)*input.*\\].*"));

		System.out.println("CLOSE_CATEGORY_SELECT_DIALOG_BTN".matches("CLOSE_.*DIALOG_BTN"));
		System.out.println("CLOSE_DIALOG_BTN".matches("CLOSE_.*DIALOG_BTN"));
	}

}