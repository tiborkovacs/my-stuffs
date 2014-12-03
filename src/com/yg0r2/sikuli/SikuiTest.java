package com.yg0r2.sikuli;

import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.lang.reflect.Field;

import org.junit.Test;
import org.sikuli.api.robot.Key;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

public class SikuiTest {
	public static void main(String[] args) throws Exception {
		_core();
	}

	@Test
	public void sikuliTest() throws Exception {
		_core();
	}

	private static void _core() throws Exception {
		System.out.println(System.getenv().get("Path"));
		System.out.println(GraphicsEnvironment.isHeadless());

		Screen s = new Screen();

		System.out.println(GraphicsEnvironment.isHeadless());

		try{
			s.click("imgs/FF_icon.png", 0);

			Thread.sleep(2000);

			s.paste("c:\\alma");
			s.type(Key.ENTER);
			System.out.println(Key.ENTER.length());
			//type("CALMAL", "Alama");
			//s.type(Key.ENTER);
			//s.wait("imgs/spotlight-input.png");
			//s.type(null, "hello world\n", 0);
		}
		catch(FindFailed e){
			e.printStackTrace();
		}
	}

}