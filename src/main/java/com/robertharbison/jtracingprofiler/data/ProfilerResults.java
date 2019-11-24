package com.robertharbison.jtracingprofiler.data;

public class ProfilerResults {
	
	private String name;
	private long startTime;
	private long endTime;
	private long threadID;
	
	public ProfilerResults(String name, long startTime, long endTime, long threadID) {
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.threadID = threadID;
	}
	
	public long getThreadID() {
		return threadID;
	}

	public String getName() {
		return name;
	}

	public long getStartTime() {
		return startTime;
	}

	public long getEndTime() {
		return endTime;
	}
}
