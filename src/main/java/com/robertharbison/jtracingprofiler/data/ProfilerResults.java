package com.robertharbison.jtracingprofiler.data;

/*
 * Represents the results of a profile test.
 */
public class ProfilerResults {
	
	private String name;
	private long startTime;
	private long endTime;
	private long threadID;
	private String category;
	
	/*
	 * @param name The name profile.
	 * @param startTime The time when the test started.
	 * @param endTime The time when the test ended.
	 * @param threadID The thread id that was being ran on.
	 * @param category The category this test is in.
	 */
	public ProfilerResults(String name, long startTime, long endTime, long threadID, String category) {
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.threadID = threadID;
		this.category = category;
	}
	
	/*
	 * @return long The thread id that was being ran on.
	 */
	public long getThreadID() {
		return threadID;
	}

	/*
	 * @return String The name profile.
	 */
	public String getName() {
		return name;
	}

	/*
	 * @return long The time when the test started.
	 */
	public long getStartTime() {
		return startTime;
	}

	/*
	 * @return long The time when the test ended.
	 */
	public long getEndTime() {
		return endTime;
	}

	/*
	 * @return The category this test is in.
	 */
	public String getCategory() {
		return category;
	}
}
