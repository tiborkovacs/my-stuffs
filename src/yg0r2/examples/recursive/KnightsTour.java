package yg0r2.examples.recursive;

import java.awt.Point;

public class KnightsTour {

	public static void main(String[] args) {
		KnightsTour knightsTour = new KnightsTour(9);

		knightsTour.runThour(0, 0);

		knightsTour.printGrid();
	}

	public KnightsTour(int n) {
		_grid = new boolean[n][n];
	}

	public boolean isAllTrue() {
		for(boolean[] row : _grid) {
			for (boolean cell : row) {
				if (!cell) {
					return false;
				}
			}
		}

		return true;
	}

	public void printGrid() {
		for(boolean[] row : _grid) {
			for (boolean cell : row) {
				if (cell) {
					System.out.print("K ");
				}
				else {
					System.out.print("* ");
				}
			}

			System.out.println();
		}

		System.out.println();
	}

	public boolean runThour(int x, int y) {
		_grid[x][y] = true;

		if (isAllTrue()) {
			return true;
		}

		for (Point p : _MOVES) {
			int nextX = x + p.x;
			int nextY = y + p.y;

			if ((nextX < 0) || (nextX >= _grid.length)) {
				continue;
			}

			if ((nextY < 0) || (nextY >= _grid.length)) {
				continue;
			}

			if (_grid[nextX][nextY]) {
				continue;
			}

			if (runThour(nextX, nextY)) {
				return true;
			}
		}

		//printGrid();

		_grid[x][y] = false;

		return false;
	}

	private static final Point[] _MOVES = new Point[]{
		new Point(-2, -1),
		new Point(-2, 1),
		new Point(2, -1),
		new Point(2, 1),
		new Point(-1, -2),
		new Point(-1, 2),
		new Point(1, -2),
		new Point(1, 2)
	};

	private boolean[][] _grid;

}