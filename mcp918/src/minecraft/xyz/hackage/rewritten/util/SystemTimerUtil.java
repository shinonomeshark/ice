package xyz.hackage.rewritten.util;

public class SystemTimerUtil {
	private static long time = 0;
	private static long startTime = 0;
	private boolean stopped = false;
	
	public SystemTimerUtil() {
		this.time = 0;
		this.startTime = System.currentTimeMillis();
	}
	
	public long getTime() {
		if(!stopped) {
			this.time = System.currentTimeMillis() - startTime;
		}
		return this.time;
	}
	
	public void setTime(long Time) {
		this.time = Time;
		this.startTime = System.currentTimeMillis() - Time;
	}
	
	public void stop() {
		this.stopped = true;
	}
	
	public void start() {
		this.stopped = false;
	}

	public void resetTime() {
		this.time = 0;
		this.startTime = System.currentTimeMillis();
	}
}
