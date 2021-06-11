package xyz.hackage.rewritten.events.listeners;

import xyz.hackage.rewritten.events.Event;

public class EventChatSent extends Event<EventChatSent> {

	public String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public EventChatSent(String msg) {
		this.message = msg;
	}
	
}
