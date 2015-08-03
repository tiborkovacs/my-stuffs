package yg0r2.xml;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import yg0r2.util.FileUtil;
import yg0r2.util.SAXReaderUtil;

public class XMLTest {

	public static void main(String[] args) throws Exception {
		String content = FileUtil.readNormalizedContent(
			"src/yg0r2/xml/Categories.macro");

		Document document = SAXReaderUtil.read(content, true);

		Element rootElement = document.getRootElement();

		List<Element> elements = rootElement.elements();

		for (Element element : elements) {
			System.out.println(element.getName());
		}
	}

}