package com.robertharbison.jtracingprofiler;

import java.io.File;

/*
 * Main class for interacting with and using the profiler.
 */
public class JTracingProfiler {

	public static final boolean SHOULD_PROFILE = Boolean.parseBoolean(System.getProperty("profile"));

	private static ProfilerSession currentSession;

	/*
	 * Creates a new profiler session. Returns null if profiling is disabled.
	 * 
	 * @param name The name of the session.
	 * 
	 * @return ProfilerSession The new session.
	 */
	public static ProfilerSession createProfilerSession(String name) {
		// TODO: Allow the use of custom paths.
		if (SHOULD_PROFILE) {
			return new ProfilerSession(name, new File("profiler-" + name + ".json"));
		} else {
			return null;
		}
	}

	/*
	 * Creates a new profiler session. Returns null if profiling is disabled.
	 * 
	 * @param name The name of the session.
	 * 
	 * @param outputFile The file in which to output the results to.
	 * 
	 * @return ProfilerSession The new session.
	 */
	public static ProfilerSession createProfilerSession(String name, File outputFile) {
		if (SHOULD_PROFILE) {
			return new ProfilerSession(name, outputFile);
		} else {
			return null;
		}
	}

	/*
	 * Starts a new session if one is already started it stops it then starts the
	 * new one.
	 * 
	 * @param session The session to begin.
	 */
	public static void startSession(ProfilerSession session) {
		if (SHOULD_PROFILE) {
			if (currentSession == null) {
				currentSession = session;
				session.startSession();
			} else {
				stopSession();
				startSession(session);
			}
		}
	}

	/*
	 * Stops the current session.
	 */
	public static void stopSession() {
		if (SHOULD_PROFILE) {
			currentSession.stopSession();
			currentSession = null;
		}
	}

	/*
	 * @return ProfilerSession Get the current profiler session.
	 */
	public static ProfilerSession getCurrentSession() {
		return currentSession;
	}
}
