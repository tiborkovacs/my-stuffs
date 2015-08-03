package yg0r2.exception;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			throw new BaseException();
		}
		catch (ExtendexException ee) {
			System.out.println("extend");
		}
		catch (BaseException be) {
			System.out.println("base");
		}
	}

}
