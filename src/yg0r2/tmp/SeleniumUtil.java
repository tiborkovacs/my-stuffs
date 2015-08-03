package yg0r2.tmp;


public class SeleniumUtil {

	public static LiferaySelenium getSelenium() {
		return _instance._getSelenium();
	}

	private LiferaySelenium _getSelenium() {
		if (_liLiferaySelenium == null) {
		}

		return _liLiferaySelenium;
	}

	private static SeleniumUtil _instance = new SeleniumUtil();

	private LiferaySelenium _liLiferaySelenium;

}