package yg0r2.examples;

public class InterestInPairs {

	public static void main(String[] args) {
		for (int i1 = 10; i1 < 100; i1++) {
			for (int j1 = 10; j1 < 100; j1++) {
				if (i1 == j1) {
					continue;
				}

				int i2 = _changeDigits(i1);

				int j2 = _changeDigits(j1);

				if ((i1 == i2) || (j1 == j2) || (i1 == j2) || (j1 == i2)) {
					continue;
				}

				int multiplication1 = i1 * j1;

				int multiplication2 = i2 * j2;

				if (multiplication1 == multiplication2) {
					System.out.printf("%2d * %2d = ", i1, j1);
					System.out.printf("%5d", multiplication1);
					System.out.printf(" = %2d * %2d", i2, j2);
					System.out.println();

					/*sb.append(i1);
					sb.append(" * ");
					sb.append(j1);
					sb.append(" = ");
					sb.append(multiplication1);
					sb.append(" = ");
					sb.append(i2);
					sb.append(" * ");
					sb.append(j2);

					System.out.println(sb.toString());*/
				}
			}
		}
	}

	private static int _changeDigits(int n) {
		char[] nChars = String.valueOf(n).toCharArray();

		StringBuilder sb = new StringBuilder(nChars.length);
		for (int i = nChars.length - 1; i >= 0; i--) {
			sb.append(nChars[i]);
		}

		return Integer.valueOf(sb.toString());
	}

}