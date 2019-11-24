package jtracingprofiler;

public class ProfilerUtils {

	public static final String profileMethod() {
		StackTraceElement trace = new Exception().getStackTrace()[2];
		return trace.getFileName() + ":" + trace.getMethodName() + ":" + trace.getLineNumber();
	}
}
