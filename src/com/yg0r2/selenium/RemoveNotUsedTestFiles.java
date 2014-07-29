package com.yg0r2.selenium;

import com.yg0r2.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yg0R2
 */
public class RemoveNotUsedTestFiles {

	public static void main(String[] args) throws Exception {
		String mainDir = "l:\\selenium\\qa\\functional\\test-src\\";

		String[] functionalDirs = new String[] {
			"functional-6110-macro", "functional-6120-macro",
			"functional-6130-macro"
		};

		RemoveNotUsedTestFiles removeNotUsedTestFiles =
			new RemoveNotUsedTestFiles();

		System.out.println("Start delete files");

		for (String subBaseDir : functionalDirs) {
			removeNotUsedTestFiles.run(mainDir + subBaseDir);
		}

		System.out.println("Finished");
	}

	public void run(String mainDir) throws IOException {
		File baseDir = new File(mainDir + "\\");

		List<String> filePaths = walk(baseDir);

		List<String> testCases = new ArrayList<String>();
		List<String> javas = new ArrayList<String>();

		for (String s : filePaths) {
			String filename = (new File(s)).getName();

			if (filename.matches("Base[0-9]*TestCase.java")) {
				continue;
			}
			else if (filename.endsWith("TestCase.java")) {
				testCases.add(s);
			}
			else if (filename.endsWith(".java")) {
				javas.add(s);
			}
		}

		for (String s : javas) {
			String fileName = s.replace(mainDir + "\\", "");

			String classPath = fileName.replace(".java", "");
			classPath = classPath.replace("\\", ".");

			boolean javaUsed = false;
			for (String testCase : testCases) {
				String content = FileUtil.readFileContent(testCase);

				if (content.contains(classPath)) {
					javaUsed = true;
					break;
				}
			}

			if (!javaUsed) {
				FileUtil.delete(mainDir + "\\" + fileName);
			}
		}

		walk(new File(mainDir + "\\"));
	}

	protected List<String> walk(File dir) throws IOException {
		List<String> filePaths = new ArrayList<String>();

		File[] fileList = dir.listFiles();

		if (fileList.length == 0) {
			FileUtil.delete(dir);

			return filePaths;
		}

		for (File f : fileList) {
			if (f.isDirectory()) {
				filePaths.addAll(walk(f));
			}
			else {
				filePaths.add(f.getPath());
			}
		}

		return filePaths;
	}

}