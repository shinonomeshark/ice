package xyz.hackage.rewritten.modules.player;

import java.util.UUID;

import org.lwjgl.input.Keyboard;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C0FPacketConfirmTransaction;
import net.minecraft.network.play.client.C13PacketPlayerAbilities;
import net.minecraft.network.play.client.C18PacketSpectate;
import net.minecraft.network.play.client.C19PacketResourcePackStatus;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import xyz.hackage.rewritten.Client;
import xyz.hackage.rewritten.events.Event;
import xyz.hackage.rewritten.events.listeners.EventMotion;
import xyz.hackage.rewritten.events.listeners.EventUpdate;
import xyz.hackage.rewritten.modules.Module;
import xyz.hackage.rewritten.modules.movement.Longjump;

public class Experimental extends Module {

	double x,y,z;
	int t = 0;
	
	public Experimental() {
		super("Experimental", "wtf", Keyboard.KEY_GRAVE, Category.PLAYER);
	}
	
	public void onEnable() {
//		Client.addChat("" + );
		
		t = 0;
//		mc.playerController.onPlayerRightClick(mc.thePlayer, mc.theWorld, mc.thePlayer.getHeldItem(),  EnumFacing.UP, new Vec3(mc.thePlayer.posX,mc.thePlayer.posY,mc.thePlayer.posZ));
		
//		for(Entity e : mc.theWorld.loadedEntityList) {
//			if(e instanceof EntityPlayer) {
//				NetworkPlayerInfo targetInfo = null;
//                int ping = 0;
//                int loop = 0;
//                for(NetworkPlayerInfo info : mc.getNetHandler().getPlayerInfoMap()) {
//                	loop++;
//                    ping = info.getResponseTime();
//                    
////                    if(e.isInvisible()) {
////                    	Client.addChat(e.getName() + " has ping of " + ping + " and invis is " + e.isInvisible());
////                    }
//                    	if(e.getName().startsWith(ChatFormatting.RED.toString())) {
////                    		Client.addChat("bot");
//                    	}
//                    	break;
//                }
//              
//			}
////		}
//		if(mc.theWorld.getCollidingBoundingBoxes(mc.thePlayer, mc.thePlayer.getEntityBoundingBox().offset(0, 3.0001, 0).expand(0, 0, 0)).isEmpty()) {
////			mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.0001, mc.thePlayer.posZ, false));
//            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, false));
//            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, true));
//		}
		
		
//		this.toggle();
		
		Thread t = new Thread() {
			public void run() {
//				try {
					
//				}
//					for(Entity e : mc.theWorld.loadedEntityList) {
//						if(e instanceof EntityPlayer) {
//							if(!e.getName().startsWith(ChatFormatting.RED.toString()) && !(e.getName() == mc.thePlayer.getName())) {
//								Minecraft.getMinecraft().thePlayer.sendChatMessage("/wdr " + e.getName() + " cheating");
//								Client.addChat(e.getName() + " ");
//								Thread.sleep(150);
//							}
//						}
//					}
//				} catch (Exception e1) {
//					
//				}
				
			}
		};
		x = mc.thePlayer.posX;
		z = mc.thePlayer.posZ;
		
//		t.start();
//		this.toggle();
		mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(mc.thePlayer.getHeldItem()));
	}
	
	public void onDisable() 	{	}
	public void onEvent(Event e){
		if(e instanceof EventUpdate && e.isPre()) {
			t++;
//			mc.thePlayer.setPosition(mc.objectMouseOver.getBlockPos().getX(), mc.objectMouseOver.getBlockPos().getY()+2, mc.objectMouseOver.getBlockPos().getZ());
//			if(this.mc.objectMouseOver != null && this.mc.objectMouseOver.getBlockPos() != null) {
//				Client.addChat("" + mc.objectMouseOver.getBlockPos().getX() + "/" + mc.objectMouseOver.getBlockPos().getY() + "/" + mc.objectMouseOver.getBlockPos().getZ());
//			}
			
			if(mc.thePlayer.fallDistance > 3) {
//				mc.thePlayer.motionY = 0.3f;
//				mc.thePlayer.setPosition(mc.thePlayer.posX+5, mc.thePlayer.posY, mc.thePlayer.posZ+5);
//				mc.thePlayer.motionY = 0;
//				this.toggle();
			}
//			
//			mc.thePlayer.sendQueue.addToSendQueue(new C19PacketResourcePackStatus("iamlazy2", C19PacketResourcePackStatus.Action.ACCEPTED));
//			mc.thePlayer.sendQueue.addToSendQueue(new C19PacketResourcePackStatus("iamlazy2", C19PacketResourcePackStatus.Action.DECLINED));
//			mc.thePlayer.sendQueue.addToSendQueue(new C19PacketResourcePackStatus("iamlazy2", C19PacketResourcePackStatus.Action.FAILED_DOWNLOAD));
//			mc.thePlayer.sendQueue.addToSendQueue(new C19PacketResourcePackStatus("iamlazy2", C19PacketResourcePackStatus.Action.SUCCESSFULLY_LOADED));
			
//			mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(null, C02PacketUseEntity.Action.INTERACT));
			
			
			if (t > 3) {
				mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
//				this.toggle();
			}
			if(mc.thePlayer.hurtTime == 9) {
				Client.getMod(new Longjump().name).toggle();
				this.toggle();
			}
		}
		if(e instanceof EventMotion && e.isPre()) {
			((EventMotion) e).setPitch(-90);
			mc.thePlayer.setPositionAndRotation(x, mc.thePlayer.posY, z, mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch);
		}
	}
	

}