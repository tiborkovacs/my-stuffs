package yg0r2.examples.sorting;

import java.util.List;

/**
 * @author Yg0R2
 */
public class MinimumSelectionSort extends AbstractSorting {

	@Override
	public List<Integer> run(List<Integer> list) {
		int size = list.size();

		for (int i = 0; i < size; i++) {
			int maxIndex = Selection.getMinimumIndex(list.subList(i, size));

			changeElement(list, i, (i + maxIndex));
		}

		return list;
	}

}