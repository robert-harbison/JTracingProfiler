package jtracingprofiler;

import java.io.IOException;

public class Profile {
	private String name;	
	private boolean stopped;
	private long startTime;
	
	public Profile(String name) {
		this.name = name;
		this.stopped = false;
		startTime = System.nanoTime();
	}

	public void stop() {
		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;
		
		System.out.println("[" + name + "] took " + elapsedTime + " nanoseconds | " + TimeUtils.convertNanoToMicroSeconds(elapsedTime) + " microseconds | " + TimeUtils.convertNanoToMilliSeconds(elapsedTime) + " milliseonds");
		
		try {
			new ProfileResults(name, startTime, endTime).writeProfile();
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
