package com.robertharbison.jtracingprofiler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ProfilerSession {

	private String name;
	private boolean hasSessionStarted = false;
	private int profileCount;
	private File outputFile;
	private BufferedWriter writer;

	public ProfilerSession(String name, File outputFile) {
		this.name = name;
		this.outputFile = outputFile;
		this.profileCount = 0;
	}

	// TODO: Implement try catch not throws
	public void beginSession() {
		try {
			this.writer = new BufferedWriter(new FileWriter(outputFile));
			this.writer.write("[\n");
		} catch (IOException e) {
			e.printStackTrace();
		} finally { // TODO: Does this make the file usable even if the program crashes?
			endSession();
		}
		this.hasSessionStarted = true;
	}

	public void endSession() {
		try {
			this.writer.write("\n]"); // TODO: Not a good place because if it crashes and this doesnt get reached the file is invalid
			this.writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.hasSessionStarted = false;
	}

	protected void incrementProfileCount() {
		this.profileCount++;
	}

	public int getProfileCount() {
		return profileCount;
	}

	public boolean hasSessionStarted() {
		return hasSessionStarted;
	}

	public String getName() {
		return name;
	}

	public BufferedWriter getWriter() {
		return writer;
	}
}
