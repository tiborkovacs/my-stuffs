package com.yg0r2.inheritance;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		A a = new A();
		a.write();

		a = new A1();
		a.write();

		B b = new B();
		b.write();

		b = new B1();
		b.write();
	}

}
