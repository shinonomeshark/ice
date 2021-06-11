package xyz.hackage.rewritten.events;

import xyz.hackage.rewritten.Client;
import xyz.hackage.rewritten.events.listeners.EventChatSent;
import xyz.hackage.rewritten.modules.Module;

public class EventManager {
	public static void callEvent(Event e) {
		if(e instanceof EventChatSent) {
			Client.cmdmgr.handleCmd((EventChatSent) e);
		}
		
		for(Module m : Client.mods) {
			if(!m.toggled) {
				continue;
			}
			m.onEvent(e);
		}
	}
}
