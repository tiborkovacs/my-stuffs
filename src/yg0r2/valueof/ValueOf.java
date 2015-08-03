package yg0r2.valueof;

public class ValueOf {

	public static void main(String[] args) {
		String operandValue = "2";
		String i = "2.0";
		String result;

		Number operandNumber = _getNumberValue(operandValue);
		Number iNumber = _getNumberValue(i);

		if ((operandNumber instanceof Double) || (iNumber instanceof Double)) {
			result = String.valueOf(
				operandNumber.doubleValue() + iNumber.doubleValue());
		}
		else {
			result = String.valueOf(
				operandNumber.intValue() + iNumber.intValue());
		}

		System.out.println(result);

System.out.println(5 % 1);



		try {
			if (operandValue.contains(".") || i.contains(".")) {
				result = String.valueOf(
					Double.valueOf(operandValue) + Double.valueOf(i));
			}
			else {
				result = String.valueOf(
					Integer.valueOf(operandValue) + Integer.valueOf(i));
			}
		}
		catch (NumberFormatException e) {
			result = "0";
		}

		System.out.println(result);
	}

	private static Number _getNumberValue(String stringValue) {
		try {
			if (stringValue.contains(".")) {
				return Double.valueOf(stringValue);
			}

			return Integer.valueOf(stringValue);
		}
		catch (NumberFormatException nfe) {
			return 0;
		}
	}

}