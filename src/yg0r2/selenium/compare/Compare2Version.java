package yg0r2.selenium.compare;

public class Compare2Version {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

	public static void oldRuntimeVariable() {
		definitionScopeVariables.put("ddmStructureXML",
				String.valueOf(DynamicDataMappingMacro.getXML(executeScopeVariables)));
	}

	public static void newRuntimeVariable() {
		RuntimeVariables.put("ddmStructureXML",
				DynamicDataMappingMacro.getXML(executeScopeVariables),
				definitionScopeVariables);
	}

}