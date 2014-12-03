package com.yg0r2.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

/**
 * @author Yg0R2
 */
public class StringUtil {

	public static String convertIsToString(InputStream is) throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		try {
			IOUtils.copy(is, os);

			return os.toString();
		}
		finally {
			os.close();
		}
	}

	public static String generateChecksum(File f) throws Exception {
		return generateChecksum(new FileInputStream(f));
	}

	public static String generateChecksum(InputStream is) throws Exception {
		return DigestUtils.md5Hex(is);
	}

	public static String[] split(String s) {
		return split(s, ",");
	}

	public static String[] split(String s, String delimeter) {
		return s.split(delimeter);
	}

}