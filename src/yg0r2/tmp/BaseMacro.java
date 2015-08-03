package yg0r2.tmp;

public class BaseMacro {

	public BaseMacro(LiferaySelenium liferaySelenium) {
		this.liferaySelenium = liferaySelenium;
	}

	public static void write() {
		System.out.println("BaseMacro.write()");
	}

	protected LiferaySelenium liferaySelenium;

}