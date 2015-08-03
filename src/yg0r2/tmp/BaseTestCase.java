package yg0r2.tmp;

public class BaseTestCase {

	public static void main(String[] args) {
		System.out.println();
		BaseTestCase baseTestCase = new BaseTestCase();
	}

	public BaseTestCase() {
		liferaySelenium = SeleniumUtil.getSelenium();

		BaseMacro baseMacro = new BaseMacro(liferaySelenium);

		baseMacro.write();
	}

	protected LiferaySelenium liferaySelenium;

}