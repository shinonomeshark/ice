package xyz.hackage.rewritten.modules.player;

import org.lwjgl.input.Keyboard;

import xyz.hackage.rewritten.events.Event;
import xyz.hackage.rewritten.modules.Module;
import xyz.hackage.rewritten.modules.settings.ModeSetting;

public class Phase extends Module {

	private ModeSetting mode = new ModeSetting("mode", "hypixel", "hypixel", "dev");
	
	public Phase() {
		super("Phase", "noclip", Keyboard.KEY_B, Category.PLAYER);
		this.settings.add(mode);
	}
		
	public void onEnable() {
		if(mode.getMode() == "hypixel") {
			mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY-4, mc.thePlayer.posZ);
			this.toggle();
		}
	}
		
	public void onDisable() {
		
	}	
	
	public void onEvent(Event e) {
		
	}	


}
