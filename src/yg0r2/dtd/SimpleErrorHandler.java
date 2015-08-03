package yg0r2.dtd;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class SimpleErrorHandler implements ErrorHandler {

	public void warning(SAXParseException e) throws SAXException {
		System.out.println(e.getMessage());
	}

	public void error(SAXParseException e) throws SAXException {
		System.out.println(e.getMessage());
		//System.out.println(e.getSystemId());
		System.out.println(
			"line: " + e.getLineNumber() + ", column: " + e.getColumnNumber());
	}

	public void fatalError(SAXParseException e) throws SAXException {
		System.out.println(e.getMessage());
	}

}