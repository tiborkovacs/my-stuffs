package com.yg0r2.mapstuff;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapStuff2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		_context = new HashMap<String, String[]>();

		_context.put("01", new String[] {"0", "1"});
		_context.put("02", new String[] {"3", "4"});
		_context.put("03", new String[] {"6", "7"});

		_context.put("02", new String[] {"9", "10"});

		for (String s : "01,02,03".split(",")) {
			String[] sArray = _context.get(s);

			System.out.print(s + ": ");
			for (String s2 : sArray) {
				System.out.print(s2 + ",");
			}

			System.out.println();
		}
	}

	private static Map<String, String[]> _context;

}
