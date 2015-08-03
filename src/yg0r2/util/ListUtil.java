package yg0r2.util;

import java.util.Arrays;
import java.util.List;

public class ListUtil {

	public static boolean containsAll(
		List<String> base, List<String> contains) {

		for (String s : contains) {
			if (!base.contains(s)) {
				return false;
			}
		}

		return true;
	}

	public static boolean notContainsAll(
			List<String> base, List<String> notContains) {

			for (String s : notContains) {
				if (base.contains(s)) {
					return false;
				}
			}

			return true;
		}

	public static List<String> split(String s) {
		return split(s, ",");
	}

	public static List<String> split(String s, String delimeter) {
		String[] split = s.split(delimeter);

		return Arrays.asList(split);
	}

}