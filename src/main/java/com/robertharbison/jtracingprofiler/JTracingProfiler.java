package com.robertharbison.jtracingprofiler;

import java.io.File;

public class JTracingProfiler {
	
	private static ProfilerSession currentSession;

	/*
	 * Creates a new profiler session.
	 * 
	 * @param name The name of the session.
	 * @return ProfilerSession The new session.
	 */
	public static ProfilerSession createProfilerSession(String name) {
		return new ProfilerSession(name, new File("profiler-" + name + ".json"));
	}
	
	/*
	 * Creates a new profiler session.
	 * 
	 * @param name The name of the session.
	 * @param outputFile The file in which to output the results to.
	 * @return ProfilerSession The new session.
	 */
	public static ProfilerSession createProfilerSession(String name, File outputFile) {
		return new ProfilerSession(name, outputFile);
	}
	
	/*
	 * Begins a new session if one is already started it stops it then starts the new  one.
	 * 
	 * @param session The session to begin.
	 */
	public static void beginSession(ProfilerSession session) {
		if (currentSession == null) {
			currentSession = session;
			session.beginSession();
		} else {
			endSession();
			beginSession(session);
		}
	}
	
	/*
	 * Ends the current session.
	 */
	public static void endSession() {
		currentSession.endSession();
		currentSession = null;
	}
	
	/*
	 * @return ProfilerSession Get the current profiler session.
	 */
	public static ProfilerSession getCurrentSession() {
		return currentSession;
	}
}
