package yg0r2.mapstuff;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class KeyValue {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Pair<String, String> keyValuePair = new ImmutablePair<String, String>("keyValue", "valueValue");

		System.out.println(keyValuePair.getKey());
		System.out.println(keyValuePair.getValue());

		Map<String, Pair<String, String>> alma = new HashMap<String, Pair<String,String>>();

	}

}
