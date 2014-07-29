package com.yg0r2.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yg0R2
 */
public class ArrayUtil {

	public static String[] compare(String[] array1, String[] array2) {
		List<String> common = new ArrayList<String>();

		for (String s : array1) {
			if (contains(array2, s)) {
				common.add(s);
			}
		}

		return common.toArray(new String[common.size()]);
	}

	public static boolean contains(String[] array, String s) {
		for (String s1 : array) {
			if (s1.equals(s)) {
				return true;
			}
		}

		return false;
	}

	public static String[] justFirstContains(String[] array1, String[] array2) {
		List<String> common = new ArrayList<String>();

		for (String s : array1) {
			if (!contains(array2, s)) {
				common.add(s);
			}
		}

		return common.toArray(new String[common.size()]);
	}

}