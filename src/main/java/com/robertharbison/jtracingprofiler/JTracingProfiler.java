package com.robertharbison.jtracingprofiler;

import java.io.File;

/*
 * Main class for interacting with and using the profiler.
 */
public class JTracingProfiler {
	
	private static ProfilerSession currentSession;

	/*
	 * Creates a new profiler session.
	 * 
	 * @param name The name of the session.
	 * @return ProfilerSession The new session.
	 */
	public static ProfilerSession createProfilerSession(String name) {
		// TODO: Allow the use of custom paths.
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
	 * Starts a new session if one is already started it stops it then starts the new  one.
	 * 
	 * @param session The session to begin.
	 */
	public static void startSession(ProfilerSession session) {
		if (currentSession == null) {
			currentSession = session;
			session.startSession();
		} else {
			stopSession();
			startSession(session);
		}
	}
	
	/*
	 * Stops the current session.
	 */
	public static void stopSession() {
		currentSession.stopSession();
		currentSession = null;
	}
	
	/*
	 * @return ProfilerSession Get the current profiler session.
	 */
	public static ProfilerSession getCurrentSession() {
		return currentSession;
	}
}
