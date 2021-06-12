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
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
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
import xyz.hackage.rewritten.util.SystemTimerUtil;

public class Experimental extends Module {

	double x,y,z;
	int t = 0;
	SystemTimerUtil tween = new SystemTimerUtil(); // used for rotations otherwise wd has an absolute seizure
	long workTime = 0;
	int old = 0;
	
	public Experimental() {
		super("Experimental", "tf", Keyboard.KEY_GRAVE, Category.PLAYER);
	}
	
	public void onEnable() {
//		Client.addChat("" + );
		
		t = 0;
		tween.resetTime();
		workTime = tween.getTime();
		
		x = mc.thePlayer.posX;
		z = mc.thePlayer.posZ;
		int slot = 0;
		for(ItemStack i : mc.thePlayer.inventory.mainInventory) {
			if(i != null) {
//				System.out.println(slot + "got a pickle chin a boi " + i.getDisplayName());
				if(slot <= 8) {
					if(i.getItem() instanceof ItemBow) {
//					mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(slot));
					}
					
					
				}
			} else {
//				System.out.println("ice cream machine broke");
			}
			
//			mc.thePlayer.setPositionAndRotation(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, -130, 75);
			
			slot++;
		}
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
			
			
			
			
			if(t == 5) {
				mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(mc.thePlayer.getHeldItem()));
			}
			
			if (t > 7) {
				mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
//				this.toggle();
			}
			if(mc.thePlayer.hurtTime == 9) {
				Client.getMod(new Longjump().name).toggle();
				this.toggle();
			}
		}
		if(e instanceof EventMotion && e.isPre()) {
			if(tween.getTime() < 300) {
				workTime = tween.getTime();
			} else if (tween.getTime() >= 300) {
				workTime = 300;
			}
			mc.thePlayer.moveForward = 0;
			mc.thePlayer.setPositionAndRotation(x, mc.thePlayer.posY, z, mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch);
			((EventMotion) e).setPitch((mc.thePlayer.rotationPitch+((-90-mc.thePlayer.rotationPitch)*(workTime/300f))));
		}
	}
	

}
