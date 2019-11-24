package jtracingprofiler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public enum Profiler {
	INSTANCE;
	
	private BufferedWriter writer;
	private boolean hasSessionStarted = false;
	private int profileCount; // This should be in a session file because multiple begin sessions for different files will cause a problem.
	
	// TODO: Implement try catch not throws
	public void beginSession(File file) throws IOException {
		this.writer = new BufferedWriter(new FileWriter(file));
		this.writer.write("[\n");
		this.hasSessionStarted = true; 
		this.profileCount = 0;
	}
	
	public void endSession() throws IOException {
		this.writer.write("\n]"); // TODO: Not a good place because if it crashes and this doesnt get reached the file is invalid
		this.writer.close();
		this.hasSessionStarted = false;
	}
	
	public boolean hasSessionStarted() {
		return hasSessionStarted;
	}
	
	public BufferedWriter getWriter() {
		return writer;
	}
	
	protected void incrementProfileCount() {
		this.profileCount++;
	}
	
	public int getProfileCount() {
		return profileCount;
	}
}
