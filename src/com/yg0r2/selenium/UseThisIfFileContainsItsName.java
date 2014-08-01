package com.yg0r2.selenium;

import com.yg0r2.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

/**
 * @author Yg0R2
 */
public class UseThisIfFileContainsItsName {

	public static void main(String[] args) throws Exception {
		String mainDir = "l:\\selenium\\qa\\functional\\test-src\\";

		String[] functionalDirs = new String[] {
			"functional-6110-macro", "functional-6120-macro",
			"functional-6130-macro"
		};

		UseThisIfFileContainsItsName useThisIfFileContainsItsName =
			new UseThisIfFileContainsItsName();

		System.out.println("Start change name to 'this' files");

		for (String subBaseDir : functionalDirs) {
			useThisIfFileContainsItsName.run(new File(mainDir + subBaseDir));
		}

		System.out.println("Finished");
	}

	public void run(File dir) throws IOException {
		File[] fileList = dir.listFiles();

		for (File f : fileList) {
			if (f.isDirectory()) {
				run(f);
			}
			else {
				String fileAbsolutePath = f.getAbsolutePath();
				String fileName = f.getName();

				String baseName = FilenameUtils.getBaseName(fileName);
				String extension = FilenameUtils.getExtension(fileName);

				String content = FileUtil.readFileContent(fileAbsolutePath);

				if (content.contains(extension + "=\"" + baseName + "#")) {
					System.out.println("change: " + fileName);

					content = content.replace(
						extension + "=\"" + baseName + "#",
						extension + "=\"this#");

					PrintWriter writer = new PrintWriter(
						fileAbsolutePath, "UTF-8");

					writer.print(content);
					writer.close();
				}
			}
		}

	}

}