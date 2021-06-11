package xyz.hackage.rewritten.util;

public class TimerButBetterBecauseICantCodeLmao {
	public long lastMS = System.currentTimeMillis();
	
	public void reset() {
		lastMS = System.currentTimeMillis();
	}
	
	public boolean hasTimeElapsed(long time, boolean reset) {
		if(System.currentTimeMillis()-lastMS > time) {
			if(reset)
				reset();
			
			return true;
		}
		return false;
	}
}
