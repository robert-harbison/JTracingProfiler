package jtracingprofiler;

import java.io.BufferedWriter;
import java.io.IOException;

public class ProfileResults {
	
	private String name;
	private long startTime;
	private long endTime;
	private long threadID;
	
	public ProfileResults(String name, long startTime, long endTime, long threadID) {
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.threadID = threadID;
	}

	public void writeProfile() throws IOException {
		BufferedWriter writer = Profiler.INSTANCE.getWriter();
		
		if (Profiler.INSTANCE.getProfileCount() > 0) {
			writer.write(",\n");
		}
		
		writer.write("\t{\n");
		writer.write("\t\t\"cat\": \"function\",\n");
		writer.write("\t\t\"dur\": " + (endTime -  startTime) + ",\n");
		writer.write("\t\t\"name\": \"" + name + "\",\n");
		writer.write("\t\t\"ph\": \"X\",\n");
		writer.write("\t\t\"pid\": 0,\n");
		writer.write("\t\t\"tid\": " + threadID + ",\n");
		writer.write("\t\t\"ts\": " + startTime + "\n");
		writer.write("\t}");
		
		writer.flush();
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
