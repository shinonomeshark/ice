package xyz.hackage.rewritten.modules.movement;

import org.lwjgl.input.Keyboard;

import xyz.hackage.rewritten.Client;
import xyz.hackage.rewritten.events.Event;
import xyz.hackage.rewritten.events.listeners.EventUpdate;
import xyz.hackage.rewritten.modules.Module;

public class Sprint extends Module {

	public Sprint() {
		super("Sprint", "automatically sprints for you", Keyboard.KEY_SEMICOLON, Category.MOVEMENT);
		// TODO Auto-generated constructor stub
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate && e.isPre()) {
			if(mc.thePlayer.moveForward > 0 && !mc.thePlayer.isCollidedHorizontally) {
//				if(!Client.modOn(new Scaffold().name)) {
					mc.thePlayer.setSprinting(true);
//				} else {
//					mc.thePlayer.setSprinting(true);
//				}
				
			}
		}
	}
}

