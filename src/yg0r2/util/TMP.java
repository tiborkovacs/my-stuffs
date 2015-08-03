package yg0r2.util;

import java.util.Arrays;

import org.apache.commons.lang.ArrayUtils;

import com.google.common.base.Joiner;

public class TMP {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (String s : StringUtil.split("KISKUTYA,kiskutya,Kiskutya,KISkutya,KISKutya,KISKUTYA,kISKUTYA")) {
			String format = _format(s);
			String formatI =_formatI(s);

			System.out.println(s + "-" + formatI + "-" + format + "-" + formatI.equals(format));
		}

		System.out.println("link=\u00AB Back".equals("link=« Back"));
		System.out.println("link=\u00C2\u00AB Back".equals("link=Â« Back"));
	}

	private static String _format(String s) {
		StringBuilder sb = new StringBuilder(s);

		for (int i = 0; i < s.length(); i++) {
			if (Character.isUpperCase(s.charAt(i)) & (i > 1)) {
				break;
			}
			else if (Character.isLowerCase(s.charAt(i))) {
				break;
			}
			else  {
				sb.setCharAt(i, Character.toLowerCase(s.charAt(i)));
			}
		}

		return sb.toString();
	}

	private static String _formatI(String s) {
		if (s.length() == 1) {
			return s.toLowerCase();
		}

		if (Character.isLowerCase(s.charAt(0))) {
			return s;
		}

		if (Character.isUpperCase(s.charAt(0)) &&
			Character.isLowerCase(s.charAt(1))) {

			return s = s.substring(0, 1).toLowerCase().concat(s.substring(1));
		}

		StringBuilder sb = new StringBuilder(s);

		for (int i = 0; i < s.length(); i++) {
			if (((i + 1) != s.length()) &&
				Character.isLowerCase(s.charAt(i + 1))) {

				break;
			}
			else {
				char c = Character.toLowerCase(s.charAt(i));

				sb.setCharAt(i, c);
			}
		}

		return sb.toString();
	}

}
