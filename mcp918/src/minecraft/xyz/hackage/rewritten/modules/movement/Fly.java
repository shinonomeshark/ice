package xyz.hackage.rewritten.modules.movement;

import org.lwjgl.input.Keyboard;

import net.minecraft.network.play.client.C03PacketPlayer;
import xyz.hackage.rewritten.Client;
import xyz.hackage.rewritten.events.Event;
import xyz.hackage.rewritten.events.listeners.EventMotion;
import xyz.hackage.rewritten.events.listeners.EventUpdate;
import xyz.hackage.rewritten.modules.Module;
import xyz.hackage.rewritten.modules.settings.ModeSetting;
import xyz.hackage.rewritten.util.MoveUtil;

public class Fly extends Module {

	private ModeSetting mode = new ModeSetting("mode", "vanilla", "watchdog", "verus-dev", "dev", "watchdog");
	int t = 0;
	
	public Fly() {
		super("Flight", "nyom", Keyboard.KEY_F, Category.MOVEMENT);
		this.settings.add(mode);
	}
	
	public void onEnable() {
		t = 100;
		
		if(mode.getMode() == "verus-dev") {
			if(mc.theWorld.getCollidingBoundingBoxes(mc.thePlayer, mc.thePlayer.getEntityBoundingBox().offset(0, 3.0001, 0).expand(0, 0, 0)).isEmpty()) {
				mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 3.0001, mc.thePlayer.posZ, false));
	            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, false));
	            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, true));
			}
			mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.42, mc.thePlayer.posZ);
		} else if(mode.getMode() == "watchdog") {
			t = 0;
			mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY+0.1f, mc.thePlayer.posZ, true));
			mc.thePlayer.swingItem();
		} else if(mode.getMode() == "dev") {
			
		}
	}
	
	public void onDisable() {
		mc.thePlayer.motionX = 0;
		mc.thePlayer.motionZ = 0;
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			t++;
//			mc.thePlayer.motionY = 0.5f;
			
			if(mode.getMode() == "dev") {
				mc.thePlayer.motionY = 0;
				if(mc.gameSettings.keyBindJump.pressed) {
					mc.thePlayer.motionY = 1;
				} else if(mc.gameSettings.keyBindSneak.pressed) {
					mc.thePlayer.motionY = -1;
				}
				
				if(mc.thePlayer.moveForward != 0 || mc.thePlayer.moveStrafing != 0) {
					MoveUtil.strafe(3.5d);
				} else {
					mc.thePlayer.motionX = 0;
					mc.thePlayer.motionZ = 0;
				}
			} else if (mode.getMode() == "verus-dev") {
				mc.thePlayer.motionY = 0.004f;
				
				if(mc.thePlayer.hurtTime != 0) {
					MoveUtil.strafe(0.7873d);
				} else {
					MoveUtil.strafe(0.4873d);
				}
				
				if(mc.gameSettings.keyBindJump.pressed) {
//					mc.thePlayer.motionY = 0.1f;
				} else if(mc.gameSettings.keyBindSneak.pressed) {
					mc.thePlayer.motionY = -1;
				}
				
				if(mc.thePlayer.moveForward != 0 || mc.thePlayer.moveStrafing != 0) {
					
				} else {
					mc.thePlayer.motionX = 0;
					mc.thePlayer.motionZ = 0;
				}
				mc.thePlayer.onGround = true;
				
			} else if (mode.getMode() == "watchdog") {
				t++;
				mc.thePlayer.motionY = 0f;
//				mc.thePlayer.motionY = -0.1f;
//				mc.thePlayer.onGround = true;
				double playerYaw = Math.toRadians(mc.thePlayer.rotationYaw);
				if(t % 20 == 0) {
					double move = 0.3f;
//					mc.thePlayer.setPosition(mc.thePlayer.posX - (((double) move * Math.sin(playerYaw))), mc.thePlayer.posY, mc.thePlayer.posZ + (((double) move * Math.cos(playerYaw))));   
					
					
				}
			}
		}
		
		if (e instanceof EventMotion && e.isPre()) {
			if(!(t % 1 == 0)) {
				e.setCancelled(true);
			}
		}
	}
}
