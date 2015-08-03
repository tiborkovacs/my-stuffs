package yg0r2.sikuli;

import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.File;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
		/* Nem jó, mert nem lehet megváltoztatni

		Map<String, String> env = System.getenv();
		Map<String, String> newEnv = new HashMap<String, String>(env);

		String path = System.getenv("Path");
		String sikuliHome = (new File("")).getAbsolutePath() + "\\lib";

		System.out.println(path);

		newEnv.put("SIKULI_HOME", sikuliHome);
		newEnv.put("Path", path + ";%SIKULI_HOME%\\libs");

		setEnv(newEnv);

		System.out.println(System.getenv("Path"));
		System.out.println(System.getenv().get("Path"));
		System.out.println(System.getenv("SIKULI_HOME"));

		newEnv.put("Path", path);
		setEnv(newEnv);

		System.out.println(System.getenv("Path"));
		*/

		/*String path = System.getenv().get("Path");
		String pathExt = (new File("")).getAbsolutePath() + "lib\\libs";
		System.out.println(path);

		System.getenv().put("alma", path + ";" + pathExt);

		System.out.println(System.getenv().get("alma"));

		System.out.println(GraphicsEnvironment.isHeadless());

		System.out.println(GraphicsEnvironment.isHeadless());*/

		try{
			Screen s = new Screen();

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

	protected static void setEnv(Map<String, String> newenv) {
		try {
					Class<?> processEnvironmentClass = Class.forName("java.lang.ProcessEnvironment");
					Field theEnvironmentField = processEnvironmentClass.getDeclaredField("theEnvironment");
					theEnvironmentField.setAccessible(true);
					Map<String, String> env = (Map<String, String>) theEnvironmentField.get(null);
					env.putAll(newenv);
					Field theCaseInsensitiveEnvironmentField = processEnvironmentClass.getDeclaredField("theCaseInsensitiveEnvironment");
					theCaseInsensitiveEnvironmentField.setAccessible(true);
					Map<String, String> cienv = (Map<String, String>) theCaseInsensitiveEnvironmentField.get(null);
					cienv.putAll(newenv);
			}
			catch (NoSuchFieldException e) {
				try {
					Class[] classes = Collections.class.getDeclaredClasses();
					Map<String, String> env = System.getenv();
					for(Class cl : classes) {
							if("java.util.Collections$UnmodifiableMap".equals(cl.getName())) {
									Field field = cl.getDeclaredField("m");
									field.setAccessible(true);
									Object obj = field.get(env);
									Map<String, String> map = (Map<String, String>) obj;
									map.clear();
									map.putAll(newenv);
							}
					}
				}
				catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			catch (Exception e1) {
					e1.printStackTrace();
		} 
	}

}