package yg0r2.examples.recursive;

import org.apache.commons.lang.ArrayUtils;

public class MergesortAlgorithm {

	public static void main(String[] args) {
		System.out.println(
			ArrayUtils.toString(
				treeMergesortAlgorithm(new int[] {2, 3, 1, 5, 7, 6, 4, 0})));
	}

	public static int[] treeMergesortAlgorithm(int[] x) {
		if (x.length <= 1) {
			return x;
		}
		else if (x.length == 2) {
			if (x[0] > x[1]) {
				return new int[] {x[1], x[0]};
			}
		}

		int index = x.length / 2;

		int left[] = ArrayUtils.subarray(x, 0, index);
		int right[] = ArrayUtils.subarray(x, index, x.length);

		left = treeMergesortAlgorithm(left);
		right = treeMergesortAlgorithm(right);

		return _mergeArrays(left, right);
	}

	private static int[] _mergeArrays(int[] left, int[] right) {
		int[] x = new int[left.length + right.length];

		int leftIndex = 0;
		int rightIndex = 0;
		int xIndex = 0;

		while ((leftIndex < left.length) && (rightIndex < right.length)) {
			if (left[leftIndex] < right[rightIndex]) {
				x[xIndex] = left[leftIndex];

				leftIndex++;
			}
			else {
				x[xIndex] = right[rightIndex];

				rightIndex++;
			}

			xIndex++;
		}

		for (;leftIndex < left.length; leftIndex++) {
			x[xIndex] = left[leftIndex];

			xIndex++;
		}

		for (; rightIndex < right.length; rightIndex++) {
			x[xIndex] = right[rightIndex];

			xIndex++;
		}

		return x;
	}

	

}