package com.yg0r2.stringstuff;

import com.yg0r2.util.ArrayUtil;
import com.yg0r2.util.StringUtil;

/**
 * @author Yg0R2
 */
public class LPEListChecker {

	public static void main(String[] args) {
		String[] lpes1 = StringUtil.split("");
		String[] lpes2 = StringUtil.split("");

		System.out.println();
		System.out.println("Just 1. contains:");
		for (String s : ArrayUtil.justFirstContains(lpes1, lpes2)) {
			System.out.print(s);
			System.out.print(", ");
		}

		System.out.println();
		System.out.println("Just 2. contains:");
		for (String s : ArrayUtil.justFirstContains(lpes2, lpes1)) {
			System.out.print(s);
			System.out.print(", ");
		}

		System.out.println();
		System.out.println("Both contains:");
		for (String s : ArrayUtil.compare(lpes1, lpes2)) {
			System.out.print(s);
			System.out.print(", ");
		}
	}

}