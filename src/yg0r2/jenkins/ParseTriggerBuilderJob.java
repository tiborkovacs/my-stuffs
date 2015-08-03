package yg0r2.jenkins;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @author Yg0R2
 */
public class ParseTriggerBuilderJob {

	private static final String _JOB_CONFIG_FILE =
		"w:/Workspace/tools/liferay-jenkins-ee/template/jobs/callconfig.xml";

	public static void main(String[] args) throws Exception {
		ParseTriggerBuilderJob parseTriggerBuilderJob = new ParseTriggerBuilderJob();

		System.out.println("'" + parseTriggerBuilderJob.run() + "'");
	}

	public ParseTriggerBuilderJob() throws Exception {
		childJobNamesMap = new HashMap<String, String>();
		jobContent = FileUtils.readFileToString(new File(_JOB_CONFIG_FILE));

	}

	public String run() {
		Pattern triggerBuilderPattern = Pattern.compile(
			"hudson\\.plugins\\.parameterizedtrigger\\.(TriggerBuilder|BuildTrigger)(.*?)/hudson\\.plugins\\.parameterizedtrigger\\.(TriggerBuilder|BuildTrigger)", Pattern.DOTALL);

		Matcher triggerBuilderMatcher = triggerBuilderPattern.matcher(jobContent);

		Set<String> triggerChildJobSet = new TreeSet<String>();

		while (triggerBuilderMatcher.find()) {
			String triggetBuilder = triggerBuilderMatcher.group(2);

			int x = triggetBuilder.indexOf("<projects>");
			int y = triggetBuilder.indexOf("</projects>", x + 1);

			String triggetChildJobName = triggetBuilder.substring(x + 10, y);

			triggetChildJobName = triggetChildJobName.replace("(@!job.variation.name!@)", "(ee-6.0.12..ee-6.2.10)");

			if (!triggetChildJobName.equals("@!child.job.names!@")) {
				triggerChildJobSet.add(triggetChildJobName);
			}
		}

		return StringUtils.join(triggerChildJobSet, ',');
	}

	protected Map<String, String> childJobNamesMap;
	protected String jobContent;

}