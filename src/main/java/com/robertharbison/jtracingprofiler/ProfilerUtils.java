package com.robertharbison.jtracingprofiler;

public class ProfilerUtils {
	
	public static final String NULL_STRING = "NULL"; 

	/*
	 * Generates a name for the profile using this structure [file]:[method]:[line].
	 */
	public static final String generateProfileName() {
		StackTraceElement trace = new Exception().getStackTrace()[2];
		return trace.getFileName() + ":" + trace.getMethodName() + ":" + trace.getLineNumber();
	}
}
