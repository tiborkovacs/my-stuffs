/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package yg0r2.util;

import java.io.StringReader;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.python.apache.xerces.parsers.SAXParser;
import org.xml.sax.SAXException;

/**
 * @author Tibor Kovacs
 */
public class SAXReaderUtil {

	public static Document read(String xml, boolean validate)
		throws DocumentException, SAXException {

		SAXReader saxReader = new SAXReader(new SAXParser(), validate);

		saxReader.setFeature(
			"http://apache.org/xml/features/validation/dynamic", validate);

		saxReader.setFeature(
			"http://xml.org/sax/features/external-general-entities", validate);

		saxReader.setFeature(
			"http://apache.org/xml/features/nonvalidating/load-dtd-grammar",
			validate);

		saxReader.setFeature(
			"http://apache.org/xml/features/nonvalidating/load-external-dtd",
			validate);

		saxReader.setFeature(
			"http://xml.org/sax/features/validation", validate);

		saxReader.setFeature(
			"http://apache.org/xml/features/validation/schema", validate);

		saxReader.setFeature(
			"http://apache.org/xml/features/validation/schema-full-checking",
			validate);

		return saxReader.read(new StringReader(xml));
	}

}