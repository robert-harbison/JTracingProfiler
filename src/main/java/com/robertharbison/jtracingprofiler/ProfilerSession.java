package com.robertharbison.jtracingprofiler;

import java.io.File;

/*
 * Represents a series of profiling tests and the file to write the data to.
 */
public class ProfilerSession {

	private String name;
	private boolean hasSessionStarted = false;
	private ProfilerFile file;

	/*
	 * How many entries in this session there are.
	 */
	private int profileCount;
	

	/*
	 * Creates a profiler session.
	 * 
	 * @param name The profiler session name.
	 * @param outputFile The file to create and output the results to.
	 */
	public ProfilerSession(String name, File outputFile) {
		if (JTracingProfiler.SHOULD_PROFILE) {
			this.name = name;
			this.file = new ProfilerFile(outputFile);
			this.profileCount = 0;
		}
	}
	
	/*
	 * Creates a profiler session.
	 * 
	 * @param name The profiler session name.
	 * @param outputFile The path to create and output the results to.
	 */
	public ProfilerSession(String name, String outputFile) {
		if (JTracingProfiler.SHOULD_PROFILE) {
			this.name = name;
			this.file = new ProfilerFile(outputFile);
			this.profileCount = 0;
		}
	}

	/*
	 * Start the session.
	 */
	public void startSession() {
		this.hasSessionStarted = true;
	}

	/*
	 * Stop the session.
	 */
	public void stopSession() {
		if (getFile() != null) {
			getFile().close();
		}
		this.hasSessionStarted = false;
	}

	/*
	 * Increments the profileCount by 1.
	 */
	protected void incrementProfileCount() {
		this.profileCount++;
	}

	/*
	 * @return int The number of entries in this session.
	 */
	public int getProfileCount() {
		return profileCount;
	}

	/*
	 * @return boolean Whether this session is running or not.
	 */
	public boolean hasSessionStarted() {
		return hasSessionStarted;
	}

	/*
	 * @return String Gets the session name.
	 */
	public String getName() {
		return name;
	}

	/*
	 * @return ProfilerFile Gets the profiler file.
	 */
	public ProfilerFile getFile() {
		return file;
	}
}
