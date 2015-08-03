package yg0r2.examples.sorting;

import java.util.List;

/**
 * @author Yg0R2
 */
public class BubbleSort extends AbstractSorting {

	@Override
	public List<Integer> run(List<Integer> list) {
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				if (list.get(i) < list.get(j)) {
					changeElement(list, i, j);
				}
			}
		}

		return list;
	}

}