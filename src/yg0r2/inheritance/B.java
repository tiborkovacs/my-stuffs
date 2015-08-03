package yg0r2.inheritance;

public class B extends A implements I{

	protected String version = "b";

	public void write() {
		System.out.println(version);
	}

}