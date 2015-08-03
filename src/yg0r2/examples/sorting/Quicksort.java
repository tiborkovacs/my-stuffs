package yg0r2.examples.sorting;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yg0R2
 */
public class Quicksort extends AbstractSorting {

	@Override
	public List<Integer> run(List<Integer> list) {
		if (list.size() <= 1) {
			return list;
		}

		int pivot = list.get(list.size() / 2);

		List<Integer> less = new ArrayList<Integer>();
		List<Integer> equals = new ArrayList<Integer>();
		List<Integer> greater = new ArrayList<Integer>();

		for (int i : list) {
			if (i < pivot) {
				less.add(i);
			}
			else if (i > pivot) {
				greater.add(i);
			}
			else {
				equals.add(i);
			}
		}

		less = run(less);
		greater = run(greater);

		List<Integer> result = new ArrayList<Integer>(less);
		result.addAll(equals);
		result.addAll(greater);

		return result;
	}

}