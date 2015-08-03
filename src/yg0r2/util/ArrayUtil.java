package yg0r2.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.google.common.base.Joiner;

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

	@SuppressWarnings("unchecked")
	public static <T> T[] concat (T[]...arrays) {
		List<T> output = new ArrayList<T>();

		for (T[] array : arrays) {
			output.addAll(Arrays.asList(array));
		}

		Class<?> componentType = arrays[0].getClass().getComponentType();

		return output.toArray(
			(T[]) Array.newInstance(componentType, output.size()));
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

	public static String merge(String[] array) {
		return merge(array, ",");
	}

	public static String merge(String[] array, String delimiter) {
		return Joiner.on(delimiter).join(array);
	}

}