package yg0r2.examples.sorting;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yg0R2
 */
public class MergeSort extends AbstractSorting {

	@Override
	public List<Integer> run(List<Integer> list) {
		if (list.size() <= 1) {
			return list;
		}

		int x = list.size() / 2;

		List<Integer> left = run(list.subList(0, x));
		List<Integer> right = run (list.subList(x, list.size()));

		int leftIndex = 0;
		int rightIndex = 0;

		List<Integer> merge = new ArrayList<Integer>(list.size());

		while ((leftIndex < left.size()) && (rightIndex < right.size())) {
			if (left.get(leftIndex) < right.get(rightIndex)) {
				merge.add(left.get(leftIndex));

				leftIndex++;
			}
			else {
				merge.add(right.get(rightIndex));

				rightIndex++;
			}
		}

		merge.addAll(left.subList(leftIndex, left.size()));
		merge.addAll(right.subList(rightIndex, right.size()));

		return merge;
	}

}