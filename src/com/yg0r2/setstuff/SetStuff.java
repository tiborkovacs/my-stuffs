package com.yg0r2.setstuff;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class SetStuff {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Set<String> alma = new TreeSet<String>();
		alma.add("a");
		alma.add("a");
		alma.add("b");
		alma.add("b");

		Set<String> alma2 = new TreeSet<String>();
		alma2.add("c");
		alma2.add("c");
		alma2.add("d");
		alma2.add("d");

		alma.addAll(alma2);

		Set<String> alma3 = new TreeSet<String>();

		alma.addAll(alma3);

		System.out.println(alma.size());

		Iterator<String> iterator = alma.iterator();

		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}

		System.out.println(alma.size());
	}

}
