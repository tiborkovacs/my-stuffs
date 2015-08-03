package yg0r2.examples.recursive;

import java.util.ArrayList;
import java.util.List;

public class NQueens {

	public static void main(String[] args) {
		NQueens nQueens = new NQueens(8);

		nQueens.addQueens();

		nQueens.printValidGrids();
	}

	public NQueens(int n) {
		_size = n;

		_validGrids = new ArrayList<boolean[][]>();
	}

	public void addQueens() {
		_addQueens(0, new boolean[_size][_size]);
	}

	public void printValidGrids() {
		for (int i = 0; i < _validGrids.size(); i ++) {
			System.out.println((i + 1) + ". variation:");

			for(boolean[] row : _validGrids.get(i)) {
				for (boolean cell : row) {
					if (cell) {
						System.out.print("Q ");
					}
					else {
						System.out.print("* ");
					}
				}

				System.out.println();
			}

			System.out.println();
		}
	}

	private void _addQueens(int queenCount, boolean[][] grid) {
		if (queenCount == grid.length) {
			_storeGrid(grid);

			return;
		}

		for (int y = 0; y < grid.length; y++) {
			if (_isSafePlace(queenCount, y, grid)) {
				grid[queenCount][y] = true;

				_addQueens(queenCount + 1, grid);

				grid[queenCount][y] = false;
			}
		}
	}

	private boolean _isSafePlace(int x, int y, boolean[][] grid) {
		//Current position
		if (grid[x][y]) {
			return false;
		}

		for (int i = 0; i < grid.length; i++) {
			//Row or Cell
			if (grid[x][i] || grid[i][y]) {
				return false;
			}

			//Diagonal
			if (((x - i) >= 0) && ((y - i) >= 0)) {
				if (grid[x - i][y - i]) {
					return false;
				}
			}

			if (((x - i) >= 0) && ((y + i) < grid.length)) {
				if (grid[x - i][y + i]) {
					return false;
				}
			}

			if (((x + i) < grid.length) && ((y - i) >= 0)){
				if (grid[x + i][y - i]) {
					return false;
				}
			}

			if (((x + i) < grid.length) && ((y + i) < grid.length)){
				if (grid[x + i][y + i]) {
					return false;
				}
			}
		}

		return true;
	}

	private void _storeGrid(boolean[][] grid) {
		boolean[][] newArray = new boolean[grid.length][grid.length];

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				newArray[i][j] = grid[i][j];
			}
		}

		_validGrids.add(newArray);
	}


	private int _size;
	private List<boolean[][]> _validGrids;

}