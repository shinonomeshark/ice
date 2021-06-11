package xyz.hackage.rewritten.modules.movement;

import org.lwjgl.input.Keyboard;

import com.ibm.icu.text.DecimalFormat;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.util.BlockPos;
import xyz.hackage.rewritten.events.Event;
import xyz.hackage.rewritten.events.listeners.EventMotion;
import xyz.hackage.rewritten.events.listeners.EventTeleport;
import xyz.hackage.rewritten.events.listeners.EventUpdate;
import xyz.hackage.rewritten.modules.Module;
import xyz.hackage.rewritten.util.MoveUtil;
import xyz.hackage.rewritten.util.Notification;
import xyz.hackage.rewritten.util.NotificationManager;
import xyz.hackage.rewritten.util.SystemTimerUtil;

public class PearlFly extends Module {

	SystemTimerUtil tim = new SystemTimerUtil();
	int ticks = 0;
	double x = 0;
	double y = 0;
	double z = 0;
	float p = 90;
	boolean flag1 = false;
	boolean flag2 = false;
	boolean flag3 = false;
	boolean flag4 = false;
	boolean flag5 = false;
	boolean notifFlag = false;
	ItemStack stack = null;
	
	public PearlFly() {
		super("PearlFly", "scuffed", Keyboard.KEY_NONE, Category.MOVEMENT);
	}
	
	public void onEnable() {
		x = mc.thePlayer.posX;
		y = mc.thePlayer.posY;
		z = mc.thePlayer.posZ;
//		p = mc.thePlayer.rotationPitch;
		flag1 = false;
		flag2 = false;
		flag3 = false;
		flag4 = false;
		flag5 = false;
		notifFlag = false;
		
		ticks = 0;
		p = mc.thePlayer.rotationPitch;
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			ticks++;
			
			x = mc.thePlayer.posX;
			y = mc.thePlayer.posY;
			z = mc.thePlayer.posZ;
//			p = mc.thePlayer.rotationPitch;
			
			if(flag3) {
				flag3 = false;
				System.out.println(p);
				mc.thePlayer.setPositionAndRotation(x, y, z, mc.thePlayer.rotationYaw, p);
				flag4 = true;
				tim.resetTime();
			}
			if(flag4) {
				if(tim.getTime() < 1300) {
					if(!notifFlag) {
						NotificationManager.AssignNotification(new Notification("", 3000l, 0xfffdfd96));
						int slot = 0;
						if(mc.thePlayer.getHeldItem() != null) {
							for(ItemStack i : mc.thePlayer.inventory.mainInventory) {
								if(i == mc.thePlayer.getHeldItem()) {
									mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(slot));
								}
								slot++;
							}
						}
						notifFlag = true;
					}
					mc.thePlayer.motionY = 0;
					if(mc.thePlayer.moveForward > 0) {
						MoveUtil.strafe(2d);
					}
					NotificationManager.currentNotification.text = "flying for " + (1300 - ((int)((float)tim.getTime())));
				} else {
					NotificationManager.currentNotification.text = "flying for 0";
					toggle();
				}
			}
		}
		if(e instanceof EventMotion) {
			x = mc.thePlayer.posX;
			y = mc.thePlayer.posY;
			z = mc.thePlayer.posZ;
//			p = mc.thePlayer.rotationPitch;
			
			if(!flag1) {
				if(ticks > 3) {
					
					if(!flag2) {
						int slot = 0;
						boolean done = false;
						for(ItemStack i : mc.thePlayer.inventory.mainInventory) {
							if(!done) {
								if(i != null) {
									if(i.getItem() instanceof ItemEnderPearl && slot <= 8) {
										stack = i;
										mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(slot));
									}
								}
							}
							slot++;
						}
						
						mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(new BlockPos(x,y-1,z), 255, stack, 0.1326625f, 0.3326625f, 0.0326625f));
						flag2 = true;
						flag5 = true;
					}
				} else if(ticks > 6){
					if(!flag5) {
						NotificationManager.AssignNotification(new Notification("failed to autopearl" +p, 2000l, 0xffff8080));
						toggle();
					}
				}
				((EventMotion) e).setPitch(90);
			} 
		}
		if(e instanceof EventTeleport) {
			ticks = 0;
			flag1 = true;
//			NotificationManager.AssignNotification(new Notification("pearled" +p, 2000l, 0xff80ff80));
			flag3 = true;
			
		}
	}
	
	public void onDisable() {
		
	}

	

}
