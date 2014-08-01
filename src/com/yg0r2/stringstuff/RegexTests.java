package com.yg0r2.stringstuff;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Yg0R2
 */
public class RegexTests {

	public static void main(String[] args) {
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
	}

}