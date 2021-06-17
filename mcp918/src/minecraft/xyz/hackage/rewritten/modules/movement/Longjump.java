package xyz.hackage.rewritten.modules.movement;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemStack;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C03PacketPlayer.C04PacketPlayerPosition;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.network.play.client.C0CPacketInput;
import net.minecraft.network.status.client.C01PacketPing;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import xyz.hackage.rewritten.Client;
import xyz.hackage.rewritten.events.Event;
import xyz.hackage.rewritten.events.listeners.EventMotion;
import xyz.hackage.rewritten.events.listeners.EventTeleport;
import xyz.hackage.rewritten.events.listeners.EventUpdate;
import xyz.hackage.rewritten.modules.Module;
import xyz.hackage.rewritten.util.MoveUtil;
import xyz.hackage.rewritten.util.Notification;
import xyz.hackage.rewritten.util.NotificationManager;

public class Longjump extends Module {

	int t = 0;
	double startY = 0;
	boolean hasBeenHit = false;
	
	public Longjump () {
		super("LongJump", "hypixel best anticheat!1!1!!1!11", Keyboard.KEY_G, Category.MOVEMENT);
	}
	
	public void onEnable() {
		int damage = 1;
//		mc.thePlayer.performHurtAnimation();
		
//		for(int i = 0; i < (65*damage); i++) {
//			mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY+0.049, mc.thePlayer.posZ, false));
//			mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, false));
//		}
//		mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, true));
//		
//		int slot = 0;
//		for(ItemStack i : mc.thePlayer.inventory.mainInventory) {
//			if(i != null) {
//				System.out.println(slot + " has " + i.getDisplayName());
//			}
//			slot++;
//		}
//		
		if(mc.thePlayer.onGround) {
			mc.thePlayer.jump();
			mc.thePlayer.motionY = 0.35f;
		}	
		
//		mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY-4, mc.thePlayer.posZ);
//		this.toggle();
		
		mc.timer.timerSpeed = 0.8f;
		t = 0;
		System.out.println("a " + mc.thePlayer.posY);
		startY = mc.thePlayer.posY;
		hasBeenHit = false;
	}
	public void onDisable() {
//		mc.thePlayer.capabilities.isFlying = false;
//		mc.thePlayer.capabilities.allowFlying = false;
		mc.timer.timerSpeed = 1f;
//		mc.thePlayer.motionX = 0;
//		mc.thePlayer.motionZ = 0;
	}

	
	public void onEvent(Event e) {
		if(e instanceof EventMotion) {
//			if(t == 1) {
//				((EventMotion) e).setY(startY+0.34999999403954d);
//			} else if(t == 2) {
//				((EventMotion) e).setY(startY+0.71000000834465d);
//			} else if(t == 3) {
//				((EventMotion) e).setY(startY+1.07000002264977);
//			} else if(t == 4) {
//				((EventMotion) e).setY(startY+1.43000003695488);
//			} else if(t == 5) {
//				((EventMotion) e).setY(startY+1.70440005631447);
//			} else if(t == 6) {
//				((EventMotion) e).setY(startY+2.00321386332928);
//			} else if(t == 7) {
//				((EventMotion) e).setY(startY+2.03094961251693);
//			} else if(t == 8) {
//				((EventMotion) e).setY(startY+2.979730645723965);
//			} else if(t == 9) {
//				((EventMotion) e).setY(startY+2.851136055764057);
//			} else if(t == 10) {
//				((EventMotion) e).setY(startY+2.6467133536247207);
//			} else if(t == 11) {
//				((EventMotion) e).setY(startY+2.3679791001032386);
//			} else if(t == 12) {
//				((EventMotion) e).setY(startY+2.0164195248098737);
//			} else if(t == 13) {
//				((EventMotion) e).setY(startY+2.5934911327910304);
//			} else if(t == 14) {
//				((EventMotion) e).setY(startY+2.100621299019966);
//			}
		}
		if(e instanceof EventUpdate) {
			if(e.isPre()) {
				t++;
//				mc.thePlayer.posY = startY;
				if(t == 14) {
					this.toggle();
				}
				if(!hasBeenHit) {
					
					
					
					
	//				mc.thePlayer.sendQueue.addToSendQueue(new C0);
					
					if(t > 5) {
	//					mc.timer.timerSpeed = 1f;
						
						if(mc.thePlayer.onGround) {
							toggle();
						}
					}
					
					if(t < 4) {
						mc.thePlayer.motionY = 0.36f;
						
					} else {
						if(mc.thePlayer.motionY < 0f) {
	//						this.toggle();
						}
	//					mc.timer.timerSpeed = 0.1f;
					}
					
					if(t > 7) {
	//					this.toggle();
						hasBeenHit = true;
					}
					System.out.println("b " + mc.thePlayer.posY);
					
					
	//				mc.thePlayer.motionY = 0f;
	//				if(t % 4 == 0) {
	//					startY = 0.1f;
	//				} else if(t % 2 == 0) {
	//					startY = -0.1f;
	//				}
					;
	//				mc.thePlayer.motionX = 0f;
	//				mc.thePlayer.motionZ = 0f;
	//				mc.thePlayer.onGround = true;
					MoveUtil.strafe(0.55d);
				}
			}
		} else if(e instanceof EventTeleport) {
			this.toggle();
			Client.addChat("lagback momento");
		}
	}

}
