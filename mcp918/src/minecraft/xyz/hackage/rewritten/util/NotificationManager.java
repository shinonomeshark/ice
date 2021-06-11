package xyz.hackage.rewritten.util;

import xyz.hackage.rewritten.HUD;

public class NotificationManager {
	public static Notification currentNotification;
	
	public static void AssignNotification(Notification notification) {
		currentNotification = notification;
		HUD.triggerNotifUpdate();
	}
}
