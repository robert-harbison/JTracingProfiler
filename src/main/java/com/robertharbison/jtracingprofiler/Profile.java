package com.robertharbison.jtracingprofiler;

import java.io.BufferedWriter;
import java.io.IOException;

import com.robertharbison.jtracingprofiler.data.ProfilerResults;

public class Profile {
	private String name;
	private boolean stopped;
	private long startTime;

	/*
	 * Creates and starts running a profile using the default generated name following this structure [file]:[method]:[line].
	 */
	public Profile() {
		this(generateProfileName());
	}

	/*
	 * Creates and starts running a profile using a custom name.
	 * @param name Profile name.
	 */
	public Profile(String name) {
		this.name = name;
		this.stopped = false;
		startTime = System.nanoTime();
	}

	/*
	 * Stops profiling and writes results to file.
	 */
	public void stop() {
		long endTime = System.nanoTime();
		writeProfile(new ProfilerResults(name, startTime, endTime, Thread.currentThread().getId()));
		JTracingProfiler.getCurrentSession().incrementProfileCount();
		stopped = true;
	}
	
	/*
	 * Writes the results to the file. (The writer is flushed when this is called).
	 * 
	 * @param ProfilerResults The results from the profiler to add to the file.
	 */
	private void writeProfile(ProfilerResults results) {
		if (JTracingProfiler.getCurrentSession() != null && stopped == false) {
			BufferedWriter writer = JTracingProfiler.getCurrentSession().getWriter();
			try {
				if (JTracingProfiler.getCurrentSession().getProfileCount() > 0) {
					writer.write(",\n");
				}

				writer.write("\t{\n");
				writer.write("\t\t\"cat\": \"function\",\n");
				writer.write("\t\t\"dur\": " + (results.getEndTime() - results.getStartTime()) + ",\n");
				writer.write("\t\t\"name\": \"" + name + "\",\n");
				writer.write("\t\t\"ph\": \"X\",\n");
				writer.write("\t\t\"pid\": 0,\n");
				writer.write("\t\t\"tid\": " + results.getThreadID() + ",\n");
				writer.write("\t\t\"ts\": " + results.getStartTime() + "\n");
				writer.write("\t}");
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * Generates a name for the profile using this structure [file]:[method]:[line].
	 */
	private static final String generateProfileName() {
		StackTraceElement trace = new Exception().getStackTrace()[2];
		return trace.getFileName() + ":" + trace.getMethodName() + ":" + trace.getLineNumber();
	}
}
