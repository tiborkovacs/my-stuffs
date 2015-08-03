package yg0r2.examples;

import java.util.ArrayList;
import java.util.List;

public class FriendlyNumbers {

	public static void main(String[] args) {
		int max = 10000;

		System.out.printf("%8s", "Original");
		System.out.print(" | ");
		System.out.printf("%16s", "Summ of devisors");
		System.out.println();

		for (int i = 1; i < max; i++) {
			int divisorsSumm = _sum(_getDivisors(i));

			int divisorsDivisorsSumm = _sum(_getDivisors(divisorsSumm));

			if ((divisorsDivisorsSumm == i) && (divisorsSumm != i)) {
				System.out.printf("%8d", i);
				System.out.print(" | ");
				System.out.printf("%16d", divisorsSumm);

				System.out.println();
			}

		}
	}

	private static List<Integer> _getDivisors(int n) {
		List<Integer> divisors = new ArrayList<Integer>();

		for (int i = 1; i < n; i++) {
			if (n%i == 0) {
				divisors.add(i);
			}
		}

		return divisors;
	}

	private static int _sum(List<Integer> array) {
		int summ = 0;

		for (int i : array) {
			summ += i;
		}

		return summ;
	}

}