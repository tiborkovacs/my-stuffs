package yg0r2.examples.sorting;

import java.util.List;

/**
 * @author Yg0R2
 */
public class Selection {

	public static int getMaximumIndex(List<Integer> list) {
		int maxIndex = 0;

		for (int i = 1; i < list.size(); i++) {
			if (list.get(maxIndex) < list.get(i)) {
				maxIndex = i;
			}
		}

		return maxIndex;
	}

	public static int getMinimumIndex(List<Integer> list) {
		int minIndex = 0;

		for (int i = 1; i < list.size(); i++) {
			if (list.get(minIndex) > list.get(i)) {
				minIndex = i;
			}
		}

		return minIndex;
	}

}