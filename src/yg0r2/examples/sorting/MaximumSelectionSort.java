package yg0r2.examples.sorting;

import java.util.List;

/**
 * @author Yg0R2
 */
public class MaximumSelectionSort extends AbstractSorting {

	@Override
	public List<Integer> run(List<Integer> list) {
		for (int i = 0; i < list.size(); i++) {
			int index = list.size() - 1 - i;

			int maxIndex = Selection.getMaximumIndex(
				list.subList(0, index + 1));

			changeElement(list, index, maxIndex);
		}

		return list;
	}

}