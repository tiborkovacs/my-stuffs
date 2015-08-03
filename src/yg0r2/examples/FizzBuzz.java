package yg0r2.examples;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {

	public static void main(String[] args) {
		List<Long> times = new ArrayList<Long>(10);

		for (int i = 0; i < 10; i++) {
			int max = 50000;

			long startTime = System.nanoTime();
			FizzBuzz5(max);
			long endTime = System.nanoTime();

			System.out.println();

			times.add(endTime - startTime);
		}

		for (long time : times) {
			System.out.println(time);
		}
	}

	public static void FizzBuzz1(int max) {
		for (int i = 1; i <= max; i++) {
			if ((i%5 == 0) && (i%7 == 0)) {
				System.out.println("FizzBuzz");
			}
			else if (i%5 == 0) {
				System.out.println("Fizz");
			}
			else if (i%7 == 0) {
				System.out.println("Buzz");
			}
			else {
				System.out.println(i);
			}
		}
	}

	public static void FizzBuzz2(int max) {
		for (int i = 1; i <= max; i++) {
			if ((i%5 == 0) && (i%7 == 0)) {
				System.out.println("FizzBuzz");

				continue;
			}

			if (i%5 == 0) {
				System.out.println("Fizz");

				continue;
			}

			if (i%7 == 0) {
				System.out.println("Buzz");

				continue;
			}

			System.out.println(i);
		}
	}

	public static void FizzBuzz3(int max) {
		for (int i = 1; i <= max; i++) {
			boolean devide5 = (i%5 == 0);
			boolean devide7 = (i%7 == 0);

			if (devide5 && devide7) {
				System.out.println("FizzBuzz");
			}

			else if (devide5) {
				System.out.println("Fizz");
			}

			else if (devide7) {
				System.out.println("Buzz");
			}

			else {
				System.out.println(i);
			}
		}
	}

	public static void FizzBuzz4(int max) {
		for (int i = 1; i <= max; i++) {
			if (i%5 == 0) {
				System.out.print("Fizz");
			}

			if (i%7 == 0) {
				System.out.print("Buzz");
			}

			if ((i%5 != 0) && (i%7 != 0)) {
				System.out.print(i);
			}

			System.out.println();
		}
	}

	public static void FizzBuzz5(int max) {
		for (int i = 1; i <= max; i++) {
			String result = "";

			if (i % 3 == 0)
			{
				result += "Fizz";
			}
			if (i % 5 == 0)
			{
				result += "Buzz";
			}

			System.out.println(result.length() > 0 ? result : i);
		}
	}

	public static void FizzBuzz6(int max) {
		StringBuilder sb = new StringBuilder(max * 2);

		for (int i = 1; i <= max; i++) {
			if ((i%5 == 0) && (i%7 == 0)) {
				sb.append("FizzBuzz");
			}
			else if (i%5 == 0) {
				sb.append("Fizz");
			}
			else if (i%7 == 0) {
				sb.append("Buzz");
			}
			else {
				sb.append(i);
			}

			sb.append("\n");
		}

		System.out.println(sb.toString());
	}

}