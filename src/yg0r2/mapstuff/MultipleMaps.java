package yg0r2.mapstuff;

import java.util.HashMap;
import java.util.Map;

public class MultipleMaps {

	public static void main(String[] args) {
		globalScopeVariables.put("alma", "almaValue");

		MultipleMaps multipleMaps = new MultipleMaps();
		multipleMaps.method(new HashMap<String, String>());
	}

	public MultipleMaps() {
		definitionScopeVariables.put("korte", "korteValue");
	}

	public void method(Map<String, String> environmentScopeVariables) {
		Map<String, String> commandScopeVariables =
			new HashMap<String, String>();

		commandScopeVariables.putAll(globalScopeVariables);
		commandScopeVariables.putAll(definitionScopeVariables);
		commandScopeVariables.putAll(environmentScopeVariables);

		commandScopeVariables.put("dinnye", "dinnyeValue");

		Map<String, String> executeScopeVariables;
		executeScopeVariables = new HashMap<String, String>();
		executeScopeVariables.putAll(commandScopeVariables);

		executeScopeVariables.put("répa", "répaValue");

		_execute(executeScopeVariables);
	}

	private void _execute(Map<String, String> executeScopeVariables) {
		for (Map.Entry<String, String> entry : executeScopeVariables.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}

	protected Map<String, String> definitionScopeVariables =
		new HashMap<String, String>();

	protected static Map<String, String> globalScopeVariables =
		new HashMap<String, String>();

}