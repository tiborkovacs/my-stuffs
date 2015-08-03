package yg0r2.pt;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FilenameUtils;

import yg0r2.util.FileUtil;
import yg0r2.util.StringUtil;

/**
 * @author Yg0R2
 */
public class ComparePortalFiles {

	public static void main(String[] args) throws Exception {
		String portal1Dir =
			"l:\\bundles\\liferay-portal-6.2.10.1-ee-ga1-SP6\\tomcat-7.0.42";

		String portal2Dir =
			"l:\\bundles\\liferay-portal-6.2-ee-sp6\\tomcat-7.0.42";

		ComparePortalFiles comparePortalFiles = new ComparePortalFiles();

		comparePortalFiles.run(portal1Dir, portal2Dir);
	}

	public ComparePortalFiles() {
		_allowedZipFileExtensions = new ArrayList<String>();
		_allowedZipFileExtensions.add("jar");
		_allowedZipFileExtensions.add("zip");

		_resultFileName = "l:\\result.txt";

		_skipDirs = new ArrayList<String>();
		_skipDirs.add("logs");
		_skipDirs.add("temp");
		_skipDirs.add("work");

		_skipFiles = new ArrayList<String>();
		_skipFiles.add("patcher.properties");
		_skipFiles.add("patcher-service.properties");
		_skipFiles.add("patching-backup.zip");
		_skipFiles.add("portal-patched.properties");

		_skipPluginsDiff = true;
	}

	public void run(String portal1Dir, String portal2Dir) throws Exception {
		System.out.println("Start check '" + portal1Dir + "'");

		Map<String, String> fileHashMap1 = walk(
			new File(portal1Dir), portal1Dir);

		System.out.println("Start check '" + portal2Dir + "'");

		Map<String, String> fileHashMap2 = walk(
			new File(portal2Dir), portal2Dir);

		Map<String, String> iteratorMap = new HashMap<String, String>();
		iteratorMap.putAll(fileHashMap1);

		Iterator<Entry<String, String>> iterator =
			iteratorMap.entrySet().iterator();

		PrintWriter printWriter = new PrintWriter(new File(_resultFileName));

		System.out.println("Start compare the files");

		printWriter.println("Diff:");
		while(iterator.hasNext()) {
			Map.Entry<String, String> mapEntry = iterator.next();

			String fileName = mapEntry.getKey();
			String checksum = mapEntry.getValue();

			if (fileHashMap2.containsKey(fileName)) {
				if (!checksum.equals(fileHashMap2.get(fileName))) {
					if (allowedToWriteResult(fileName)) {
						printWriter.println("fileName: " + fileName);
						printWriter.println(fileHashMap1.get(fileName));
						printWriter.println(fileHashMap2.get(fileName));
						printWriter.println("---------------------------");
					}
				}

				fileHashMap1.remove(fileName);
				fileHashMap2.remove(fileName);
			}
		}
		printWriter.println();

		printWriter.println("Contains just the '" + portal1Dir + "':");
		iterator = fileHashMap1.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry<String, String> mapEntry = iterator.next();

			String fileName = mapEntry.getKey();

			if (allowedToWriteResult(fileName)) {
				printWriter.println("fileName: " + fileName);
			}
		}
		printWriter.println();

		printWriter.println("Contains just the '" + portal2Dir + "':");
		iterator = fileHashMap2.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry<String, String> mapEntry = iterator.next();

			String fileName = mapEntry.getKey();

			if (allowedToWriteResult(fileName)) {
				printWriter.println("fileName: " + fileName);
			}
		}
		printWriter.println();

		printWriter.close();

		System.out.println("Finished.");
	}

	protected boolean allowedToWriteResult(String path) {
		if (_skipPluginsDiff) {
			if (!path.contains("webapps")) {
				return true;
			}
			else if (path.contains("ROOT")) {
				return true;
			}

			return false;
		}

		return true;
	}

	protected Map<String, String> walk(File dir, String baseDir)
		throws Exception {

		Map<String, String> fileHashMap = new HashMap<String, String>();

		File[] fileList = dir.listFiles();

		if (fileList.length == 0) {
			FileUtil.delete(dir);

			return fileHashMap;
		}

		for (File f : fileList) {
			if (f.isDirectory()) {
				if (_skipDirs.contains(f.getName())) {
					continue;
				}

				fileHashMap.putAll(walk(f, baseDir));
			}
			else {
				String fileExt = FilenameUtils.getExtension(f.getName());

				if (_skipFiles.contains(f.getName())) {
					continue;
				}

				String normalizedPath = f.getPath();
				normalizedPath = normalizedPath.replace(baseDir, "");

				if (_allowedZipFileExtensions.contains(fileExt)) {
					fileHashMap.putAll(walkZipFile(f, normalizedPath));
				}
				else {
					fileHashMap.put(
						normalizedPath, StringUtil.generateChecksum(f));
				}
			}
		}

		return fileHashMap;
	}

	protected Map<String, String> walkZipFile(
		File zipFile, String basePath) throws Exception {

		Map<String, String> fileHashMap = new HashMap<String, String>();

		ZipInputStream zipIS = new ZipInputStream(new FileInputStream(zipFile));

		ZipEntry zipEntry;
		while ((zipEntry = zipIS.getNextEntry()) != null) {
			if (zipEntry.isDirectory()) {
				continue;
			}

			String fileExt = FilenameUtils.getExtension(zipEntry.getName());
			String fileName = FilenameUtils.getName(zipEntry.getName());

			if (_skipFiles.contains(fileName)) {
				continue;
			}

			if (_allowedZipFileExtensions.contains(fileExt)) {
				String tempDir = System.getProperty("java.io.tmpdir");

				String extractFileName = tempDir + fileName;

				FileUtil.unzipFile(
					zipIS,
					new BufferedOutputStream(
						new FileOutputStream(extractFileName)));

				File extractedFile = new File(extractFileName);

				fileHashMap.putAll(
					walkZipFile(
						extractedFile, basePath + "!" + zipEntry.getName()));

				FileUtil.delete(extractedFile);
			}

			fileHashMap.put(
				basePath + "!" +  zipEntry.getName(),
				StringUtil.generateChecksum(zipIS));
		}

		return fileHashMap;
	}

	private List<String> _allowedZipFileExtensions;
	private String _resultFileName;
	private List<String> _skipDirs;
	private List<String> _skipFiles;
	private boolean _skipPluginsDiff;

}