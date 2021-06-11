package xyz.hackage.rewritten.events.listeners;

import xyz.hackage.rewritten.events.Event;

public class EventRender extends Event<EventRender> {

	public static float partialTicks;
	


	public EventRender(float partialTicks2) {
		partialTicks = partialTicks2;
	}
	
}
