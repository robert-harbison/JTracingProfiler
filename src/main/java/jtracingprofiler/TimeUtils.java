package jtracingprofiler;

public class TimeUtils {
	
	public static final int NANOSECONDS_IN_MICROSECOND = 1000;
	public static final int MICROSECONDS_IN_MILLISECOND = 1000;

	public static long convertNanoToMicroSeconds(long nanoseconds) {
		return nanoseconds / NANOSECONDS_IN_MICROSECOND;
	}
	
	public static long convertNanoToMilliSeconds(long nanoseconds) {
		return convertNanoToMicroSeconds(nanoseconds) / MICROSECONDS_IN_MILLISECOND;
	}
}
