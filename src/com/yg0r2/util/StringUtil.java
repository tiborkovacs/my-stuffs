package com.yg0r2.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author Yg0R2
 */
public class StringUtil {

	public static String generateChecksum(File f) throws Exception {
		return generateChecksum(new FileInputStream(f));
	}

	public static String generateChecksum(InputStream is) throws Exception {
		return DigestUtils.md5Hex(is);
	}

}