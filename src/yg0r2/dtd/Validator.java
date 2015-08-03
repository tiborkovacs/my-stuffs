package yg0r2.dtd;

import java.io.File;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.SchemaFactory;

import org.dom4j.io.SAXReader;

public class Validator {

	/*public static void main(String[] args) throws DocumentException {
		SAXReader reader = new SAXReader();
		reader.setValidation(true);
		reader.setErrorHandler(new SimpleErrorHandler());
		reader.read("src/yg0r2/dtd/contacts.xml");

		System.out.println("Validation finished.");
	}*/

	public static final String BASE_DIR = (new File("")).getAbsolutePath();

	public static void main(String[] args) throws Exception {
		SAXParserFactory factory = SAXParserFactory.newInstance();

		/*SchemaFactory schemaFactory = SchemaFactory.newInstance(
			"http://www.w3.org/2001/XMLSchema");

		factory.setSchema(
			schemaFactory.newSchema(
				new URL("http://www.w3.org/2001/XMLSchema.xsd")));*/

		SAXParser parser = factory.newSAXParser();

		SAXReader reader = new SAXReader(parser.getXMLReader());

		reader.setValidation(true);
		reader.setErrorHandler(new SimpleErrorHandler());
		reader.read(BASE_DIR + "/src/yg0r2/dtd/BaseLiferay.macro");

		System.out.println("Validation finished.");
	}

}