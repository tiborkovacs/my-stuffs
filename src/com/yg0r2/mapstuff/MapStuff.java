package com.yg0r2.mapstuff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author Yg0R2
 */
public class MapStuff {

	public static void main(String[] args) {
		MapStuff mapStuff = new MapStuff();

		mapStuff.run3();
	}

	public void run() {
		_useThis = _sb1;

		//_useThis.append("Valami1");
		append(_useThis, "Valami1");

		_useThis = _sb2;

		//_useThis.append("Valami2");
		//_useThis.append(",Valami3");
		append(_useThis, "Valami2");
		append(_useThis, ",Valami3");

		_useThis = _sb1;

		//_useThis.append(",Valami4");
		append(_useThis, ",Valami4");

		System.out.println(_sb1.toString());
		System.out.println(_sb2.toString());
	}

	public void run2() {
		List<Map<String, String>> all = new ArrayList<Map<String, String>>();

		for (int i = 0; i < 4; i++) {
			Map<String, String> listElem = new HashMap<String, String>();

			all.add(listElem);

			listElem.put("valamiKey1", "valamiValue1-" + i);
		}

		for (int i = 0; i < 4; i++) {
			Map<String, String> listElem = all.get(i);

			all.add(listElem);

			listElem.put("valamiKey2", "valamiValue2-" + i*10);
		}


		for (int i = 0; i < 4; i++) {
			System.out.println(all.get(i).get("valamiKey1"));
			System.out.println(all.get(i).get("valamiKey2"));
		}
	}

	public void run3() {
		List<Map<String, StringBuilder>> testPlan = new ArrayList<Map<String, StringBuilder>>();
		List<String> counter = new ArrayList<String>();

		for (Integer i = 0; i < 10; i++) {
			Map<String, StringBuilder> testCase = new HashMap<String, StringBuilder>();

			if (i != 9) {
				testCase.put("testCaseName" + i, new StringBuilder("testCaseValue" + i));
			}

			/*if (testCase.isEmpty()) {
				testCase.put("", null);
			}*/

			counter.add(i.toString());
			testPlan.add(testCase);
		}

		for (int i = 0; i < counter.size(); i++) {
			System.out.print(counter.get(i) + ": ");

			Map<String, StringBuilder> testCase = testPlan.get(i);

			Set<Entry<String, StringBuilder>> mapEntrySet = testCase.entrySet();

			for (Entry<String, StringBuilder> mapEntry : mapEntrySet) {
				System.out.println(mapEntry.getKey() + ", " + mapEntry.getValue());
			}
		}

		System.out.println();

		for (Map<String, StringBuilder> map : testPlan) {
			Set<Entry<String, StringBuilder>> mapEntrySet = map.entrySet();

			for (Entry<String, StringBuilder> mapEntry : mapEntrySet) {
				System.out.println(mapEntry.getKey() + ", " + mapEntry.getValue());
			}
		}

		System.out.println(counter.size());
		System.out.println(testPlan.size());
	}

	protected void append(StringBuilder sb, String msg) {
		sb.append(msg);
	}

	private StringBuilder _sb1 = new StringBuilder();
	private StringBuilder _sb2 = new StringBuilder();

	private StringBuilder _useThis;

}