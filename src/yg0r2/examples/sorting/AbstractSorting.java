package yg0r2.examples.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author Yg0R2
 */
public abstract class AbstractSorting {

	public static final int INITIAL_CAPACITY = 40;

	public static void main(String[] args) throws Exception {
		Random rnd = new Random();

		List<Integer> baseList = new ArrayList<Integer>(INITIAL_CAPACITY);

		for (int i = 0; i < INITIAL_CAPACITY; i++) {
			baseList.add(rnd.nextInt(100));
		}

		for (Class<?> clazz : _SORTING_ALGORITHMS) {
			List<Integer> list = new ArrayList<Integer>(baseList);

			AbstractSorting sorting = (AbstractSorting) clazz.newInstance();

			list = sorting.run(list);
			sorting.printList(list);
		}

	}

	public void changeElement(List<Integer> list, int thisOne, int withThis) {
		int tmp = list.get(thisOne);
		list.set(thisOne, list.get(withThis));
		list.set(withThis, tmp);
	}

	public void printList(List<Integer> list) {
		System.out.println("- " + getClass().getSimpleName() + " -");

		System.out.println(
			Arrays.toString(list.toArray(new Integer[list.size()])));

		System.out.println("-");
	}

	public abstract List<Integer> run(List<Integer> list);

	private static final Class<?>[] _SORTING_ALGORITHMS = new Class<?>[] {
		DummySort.class, BubbleSort.class, MaximumSelectionSort.class,
		MergeSort.class, MinimumSelectionSort.class, Quicksort.class
	};

}