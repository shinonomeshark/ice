package xyz.hackage.rewritten.util;

public class Notification {
	public String text;
	public long length;
	private SystemTimerUtil tim;
	public int color;
	
	public Notification(String NotificationText, long NotificationTimeInMilliseconds, int colour) {
		color = colour;
		text = NotificationText;
		length = NotificationTimeInMilliseconds;
		tim = new SystemTimerUtil();
	}
	
	public SystemTimerUtil getTim() {
		return tim;
	}
}
