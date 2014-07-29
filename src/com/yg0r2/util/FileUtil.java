package com.yg0r2.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Yg0R2
 */
public class FileUtil {

	public static void delete(File f) throws IOException{
		try {
			Files.delete(Paths.get(f.toURI()));
		}
		catch (IOException ioe) {
			if (ioe.getMessage().contains("The process cannot access")) {
				f.delete();
				f.deleteOnExit();
			}
			else {
				throw ioe;
			}
		}
	}

	public static void delete(String fileFullPath) throws IOException{
		Files.delete(Paths.get(fileFullPath));
	}

	public static String readFileContent(String fileFullPath)
		throws IOException {

		return new String(Files.readAllBytes(Paths.get(fileFullPath)));
	}

	public static void unzipFile(InputStream is, OutputStream os)
		throws IOException {

		byte[] buffer = new byte[1024];
		int len;

		while((len = is.read(buffer)) >= 0) {
			os.write(buffer, 0, len);
		}

		os.close();
	}

}