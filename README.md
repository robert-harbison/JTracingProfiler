JTracingProfiler is a simple profiling library for Java that uses [Chromium tracing frame viewer](https://www.chromium.org/developers/how-tos/trace-event-profiling-tool/frame-viewer "Chromium tracing frame viewer") for data visualization. 

## How To Use
#### Basic Example
	public static void main(String[] args) {
		/*
		 * Creates and starts a new session with a custom name. This name is also used\
		 * in the output files name
		 */
		JTracingProfiler.startSession(JTracingProfiler.createProfilerSession("example"));

		method();

		// Stop the session when you are done to ensure the data gets saved correctly.
		JTracingProfiler.stopSession();
	}

	public static void method() {
		/*
		 * Creates a new profile using the default naming scheme [file]:[method]:[line].
		 * You can also pass in a String to use as name instead.
		 */
		Profile profile = new Profile();
		
		// [YOUR CODE HERE]
		
		// Stops the profiler and writes to the file.
		profile.stop();
	}

#### Viewing Data
Currently the data will output to a JSON file in the programs root directory. If you open up a Chromium based browser and type in [chrome://tracing/](chrome://tracing/) to the URL bar then drag the JSON file into it you will get a visual representation of the data.