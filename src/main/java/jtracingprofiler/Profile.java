package jtracingprofiler;

import java.io.IOException;

public class Profile {
	private String name;	
	private boolean stopped;
	private long startTime;
	
	public Profile() {
		this(ProfilerUtils.profileMethod());
	}
	
	public Profile(String name) {
		this.name = name;
		this.stopped = false;
		startTime = System.nanoTime();
	}

	public void stop() {
		long endTime = System.nanoTime();
				
		try {
			new ProfileResults(name, startTime, endTime, Thread.currentThread().getId()).writeProfile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Profiler.INSTANCE.incrementProfileCount();
		stopped = true;
	}
	
	public boolean isStopped() {
		return stopped;
	}
}
