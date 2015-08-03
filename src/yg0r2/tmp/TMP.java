package yg0r2.tmp;

import java.awt.im.InputContext;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.openqa.selenium.Keys;

public class TMP {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		/*System.out.println("link=┬ź Back");
		System.out.println("link=\u00C2\u00AB Basic");

		StringBuilder sb = new StringBuilder();
		sb.append("link=\u00AB Basic");

		System.out.println(sb.toString());

		System.out.println("link=┬ź Back".equals("link=\u00AB Basic"));
		System.out.println("\u00E1");

		System.out.println("a[normalize-space(text())='â†? First']");
		System.out.println("a[normalize-space(text())='\uFFFD? First']");

		System.out.println("&#36;".contains("$"));

		System.out.println("'" + Keys.ENTER.toString() + "'");

		InputContext context = InputContext.getInstance();
		System.out.println(context.getLocale().toString());

		System.out.println("replace: '" + ("".replace("$", "sd")) + "'");

		Set<Integer> set = new TreeSet<Integer>();
		set.add(2);
		set.add(4);
		set.add(1);

		for (Integer i : set) {
			System.out.print(i + ",");
		}
		System.out.println();

		int i = 1;
		Double d = 1.0;
		System.out.println(Double.valueOf(d + 0.1));
		System.out.println(Integer.valueOf(i + 1));*/

		//System.out.println("11/16/1984".substring(3,5));

		/*_instance((String)null);
		_instance((Integer)null);
		_instance(null);*/

		/*Number n = 1;

		System.out.println(String.valueOf(n));

		StringBuilder sb = new StringBuilder(7);
		sb.insert(0, "<!--");
		sb.insert(5, "-->");

		sb.append("1");
		sb.append("2");
		sb.append("3");
		sb.append("4");
		sb.append("5");

		System.out.println(sb.toString());*/

		/*Map<String, String> s = new HashMap<String, String>();
		System.out.println(s.get("a"));*/

		Element e = DocumentHelper.createElement("div");
		e.addComment("comment msg");

		Document d = DocumentHelper.createDocument(e);

		write(d);

		System.out.println(e.getText());


		_valami("1", "2");
	}

	public static void write(Document document) throws IOException {

        // lets write to a file
        XMLWriter writer = new XMLWriter(
            new FileWriter( "output.xml" )
        );
        writer.write( document );
        writer.close();

        // Pretty print the document to System.out
        OutputFormat format = OutputFormat.createPrettyPrint();
        writer = new XMLWriter( System.out, format );
        writer.write( document );

        // Compact format to System.out
        format = OutputFormat.createCompactFormat();
        writer = new XMLWriter( System.out, format );
        writer.write( document );
    }
	private static void _valami(String... strs) {
		for (int i = 0; i < 3; i++) {
			try {
				System.out.println(strs[i]);
			}
			catch (ArrayIndexOutOfBoundsException e) {
			}
		}
	}

	private static void _instance(Integer a) {
		System.out.println("int");
	}

	private static void _instance(String b) {
		System.out.println("string");
	}

}