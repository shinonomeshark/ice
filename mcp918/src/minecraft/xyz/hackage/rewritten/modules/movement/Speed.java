package xyz.hackage.rewritten.modules.movement;

import org.lwjgl.input.Keyboard;

import net.minecraft.network.play.client.C03PacketPlayer;
import xyz.hackage.rewritten.events.Event;
import xyz.hackage.rewritten.events.listeners.EventUpdate;
import xyz.hackage.rewritten.modules.Module;
import xyz.hackage.rewritten.modules.settings.ModeSetting;
import xyz.hackage.rewritten.util.MoveUtil;

public class Speed extends Module {

	int t = 0;
	ModeSetting mode = new ModeSetting("mode", "scuffed", "scuffed", "scuffed2");
	
	public Speed() {
		super("Speed", "hypixel idk", Keyboard.KEY_V, Category.MOVEMENT);
		this.settings.add(mode);
	}
	
	public void onEnable() {
		t = 0;
		if(mode.getMode() == "scuffed") {
			mc.timer.timerSpeed = 1f;
		}
	}
	
	public void onDisable() {
		mc.timer.timerSpeed = 1f;
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			t++;
			
			mc.thePlayer.setSprinting(true);
			
			if(mc.thePlayer.moveForward != 0 || mc.thePlayer.moveStrafing != 0) {
				if(mc.thePlayer.onGround) {
					mc.thePlayer.jump();
//					MoveUtil.strafe(1d);
				} else {
					MoveUtil.strafe(0.2873d);
				}
				
				if(mc.thePlayer.motionY < 0.3) {
//					mc.thePlayer.motionY = -0.5f;
					mc.timer.timerSpeed = 1f;
				} else {
					mc.timer.timerSpeed = 1f;
				}
			}
			
			
//			if(t > 15) {
//				t = 0;
////				mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 3.0001, mc.thePlayer.posZ, false));
////	            mc.thePlayer.send?Queue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, false));
////	            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, true));
//			} else if(t <= 5) {
//				MoveUtil.strafe(1d);
//			} else if(t > 5) {
//				MoveUtil.strafe(0.5d);
//			}
		}
	}



}
