package com.robertharbison.jtracingprofiler;

import com.robertharbison.jtracingprofiler.data.ProfilerResults;

/*
 * Represents the test.
 */
public class Profile {
	private String name;
	private boolean stopped;
	private long startTime;
	private String category;

	/*
	 * Creates and starts running a profile using the default generated name following this scheme [file]:[method]:[line].
	 */
	public Profile() {
		this(generateProfileName());
	}
	
	/*
	 * Creates and starts running a profile using a custom name.
	 * 
	 * @param name The profile name.
	 */
	public Profile(String name) {
		this(name, "function");
	}

	/*
	 * Creates and starts running a profile using a custom name and category.
	 * 
	 * @param name Profile name.
	 * @param categroy Category name.
	 */
	public Profile(String name, String category) {
		this.name = name;
		this.stopped = false;
		this.startTime = System.nanoTime();
		this.category = category;
	}

	/*
	 * Stops profiling and writes results to file.
	 */
	public void stop() {
		long endTime = System.nanoTime();
		ProfilerSession session = JTracingProfiler.getCurrentSession();
		session.getFile().writeProfile(new ProfilerResults(name, startTime, endTime, Thread.currentThread().getId(), category));
		session.incrementProfileCount();
		stopped = true;
	}
	
	
	/*
	 * Generates a name for the profile using this structure [file]:[method]:[line].
	 */
	private static final String generateProfileName() {
		StackTraceElement trace = new Exception().getStackTrace()[2];
		return trace.getFileName() + ":" + trace.getMethodName() + ":" + trace.getLineNumber();
	}

	/*
	 * @return If this profiler is running or not.
	 */
	protected boolean isStopped() {
		return stopped;
	}
}
