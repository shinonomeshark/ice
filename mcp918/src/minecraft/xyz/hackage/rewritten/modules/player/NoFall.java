package xyz.hackage.rewritten.modules.player;

import java.util.UUID;

import org.lwjgl.input.Keyboard;

import com.mojang.authlib.GameProfile;

import net.minecraft.network.play.client.C03PacketPlayer.C05PacketPlayerLook;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.network.play.client.C0FPacketConfirmTransaction;
import net.minecraft.network.play.client.C18PacketSpectate;
import xyz.hackage.rewritten.events.Event;
import xyz.hackage.rewritten.events.listeners.EventMotion;
import xyz.hackage.rewritten.events.listeners.EventUpdate;
import xyz.hackage.rewritten.modules.Module;

public class NoFall extends Module {

	public NoFall() {
		super("NoFall", "portal moment", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
//			mc.thePlayer.sendQueue.addToSendQueue(new C18PacketSpectate(mc.thePlayer.getUniqueID()));
//			mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging());
//			mc.thePlayer.sendQueue.addToSendQueue(new C05PacketPlayerLook());
//			mc.thePlayer.sendQueue.addToSendQueue(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.STOP_SLEEPING));
			
		}
		if(e instanceof EventMotion) {
//			((EventMotion) e).setY(((EventMotion) e).getY()+0.1f);
			if(mc.thePlayer.fallDistance > 1 && mc.thePlayer.fallDistance < 64) {
				((EventMotion) e).setOnGround(true);
//				((EventMotion) e).setY(((EventMotion) e).getY()-0.1f);
			} 
		}
	}

}
