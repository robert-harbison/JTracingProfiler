package com.robertharbison.jtracingprofiler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.robertharbison.jtracingprofiler.data.ProfilerResults;

/*
 * Represents the file that the session uses.
 */
public class ProfilerFile {
	private BufferedWriter writer;
	
	/*
	 * Create a new ProfilerFile.
	 * 
	 * @param outputFile The file location to output to.
	 */
	public ProfilerFile(String outputFile) {
		this(new File(outputFile));
	}

	/*
	 * Create a new ProfilerFile.
	 * 
	 * @param outputFile The file to output to.
	 */
	public ProfilerFile(File outputFile) {
		try {
			this.writer = new BufferedWriter(new FileWriter(outputFile));
			writeFileHeader();
			this.writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * Writes the results to the file. (The writer is flushed when this is called).
	 * 
	 * @param ProfilerResults The results from the profiler to add to the file.
	 */
	protected void writeProfile(ProfilerResults results) {
		if (JTracingProfiler.getCurrentSession() != null) {
			try {
				// Adds comma between entries
				if (JTracingProfiler.getCurrentSession().getProfileCount() > 0) {
					writeLine(",");
				}
				
				writeLine("{");
				writeLine("\"cat\": \"" + results.getCategory() + "\",");
				writeLine("\"dur\": " + (results.getEndTime() - results.getStartTime()) + ",");
				writeLine("\"name\": \"" + results.getName() + "\",");
				writeLine("\"ph\": \"X\",");
				writeLine("\"pid\": 0,");
				writeLine("\"tid\": " + results.getThreadID() + ",");
				writeLine("\"ts\": " + results.getStartTime() + "");
				writer.write("}");
				
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * Writes JSON header to the file.
	 */
	private void writeFileHeader() throws IOException {
		this.writeLine("[");
		this.writer.flush();
	}
	
	/*
	 * Writes JSON footer to the file.
	 */
	private void writeFileFooter() throws IOException {
		this.writeLine("]");
		this.writer.flush();
	}
	
	/*
	 * Appends a new line to the line you want to write to the file.
	 */
	private void writeLine(String line) throws IOException {
		this.writer.write(line + "\n");
	}

	// Note gets called on end session
	protected void close() {
		try {
			writeFileFooter();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * @return BufferedWriter The writer for the profiler file.
	 */
	protected BufferedWriter getWriter() {
		return writer;
	}
}
