package yg0r2.examples;

public class ScarryChameleon {

	public static void main(String[] args) {
		ScarryChameleon scarryChameleon = new ScarryChameleon(100);

		scarryChameleon.start();
	}

	public ScarryChameleon(int maxCount) {
		//|c1-c2|%3 == 0
		//|c1-c3|%3 == 0
		//|c2-c3|%3 == 0

		//c1=? c2=? c3=?

		_maxCount = maxCount;
	}

	public void start() {
		for (int i = 1; i <= _maxCount - 2; i++) {
			for (int j = 1; j <= (_maxCount - i); j++) {
				for (int k = 1; k <= (_maxCount - i - j); k++) {
					int c1 = Math.abs(i - j)%3;
					int c2 = Math.abs(i - k)%3;
					int c3 = Math.abs(j - k)%3;

					if ((c1 == 0) || (c2 == 0) || (c3 == 0)) {
						System.out.println(counter++);
						System.out.print("Brown: " + i);
						System.out.print(", Gray: " + j);
						System.out.print(", Green: " + k);
						System.out.println();
					}
				}
			}
		}
	}

	private int counter = 1;
	private int _maxCount;

}