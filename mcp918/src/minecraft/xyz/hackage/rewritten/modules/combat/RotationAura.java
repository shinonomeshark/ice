package xyz.hackage.rewritten.modules.combat;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.lwjgl.input.Keyboard;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;
import xyz.hackage.rewritten.HUD;
import xyz.hackage.rewritten.events.Event;
import xyz.hackage.rewritten.events.listeners.EventMotion;
import xyz.hackage.rewritten.modules.Module;
import xyz.hackage.rewritten.util.TimerButBetterBecauseICantCodeLmao;

public class RotationAura extends Module {

	public float range = 3;
	public float cps = 19;
	
	public TimerButBetterBecauseICantCodeLmao timButWithABrain = new TimerButBetterBecauseICantCodeLmao();
	public TimerButBetterBecauseICantCodeLmao timWorksInRotationInc = new TimerButBetterBecauseICantCodeLmao();
	
	
	public RotationAura() {
		super("KillAura-DEV", "same ting youkno", Keyboard.KEY_NONE, Category.COMBAT);
	}

	public void onEvent(Event e) {
		if(e instanceof EventMotion) {
			
			EventMotion em = (EventMotion) e;
			
			List<Entity> targets = mc.theWorld.loadedEntityList.stream().filter(EntityLivingBase.class::isInstance).collect(Collectors.toList());
			targets = targets.stream().filter(ent -> ent.getDistanceToEntity(mc.thePlayer) < 4 && ent != mc.thePlayer && !ent.isDead && ((EntityLivingBase)ent).getHealth() > 0).collect(Collectors.toList());
			
			targets.sort(Comparator.comparingDouble(ent -> ((EntityLivingBase)ent).getDistanceToEntity(mc.thePlayer)));
			
			targets = targets.stream().filter(EntityPlayer.class::isInstance).collect(Collectors.toList());
			
			if(!targets.isEmpty()) {
				
				HUD.attacking = targets.get(0);
				
				EntityLivingBase target = (EntityLivingBase) targets.get(0);
				
				em.setYaw(getRotations(target)[0]);
				em.setPitch(getRotations(target)[1]);
//				
//				mc.thePlayer.rotationYaw = getRotations(target)[0];
//				mc.thePlayer.rotationPitch = getRotations(target)[1];
				
				
				if(timButWithABrain.hasTimeElapsed((long) (1000/cps), true)) {
					mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(target, Action.ATTACK));
					mc.thePlayer.swingItem();
				}
			} else {
				HUD.attacking = null;
			}
		}
	}
	
	public float[] getRotations(Entity e) {
		double deltaX = e.posX + (Math.random()/4) + (e.posX-e.lastTickPosX) - mc.thePlayer.posX;
		double deltaY = e.posY - 3.5 + (Math.random()/4) + e.getEyeHeight() - mc.thePlayer.posY + mc.thePlayer.getEyeHeight();
		double deltaZ = e.posZ + (Math.random()/4) +(e.posZ-e.lastTickPosZ) - mc.thePlayer.posZ;
		double distance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaX, 2));
		
		float yaw = (float) Math.toDegrees(-Math.atan(deltaX / deltaZ));
		float pitch = (float) -Math.toDegrees(Math.atan(deltaY / distance));
		
		if(deltaX < 0 && deltaZ < 0) {
			yaw = (float) (90 + Math.toDegrees(Math.atan(deltaZ / deltaX)));
		} else if(deltaX > 0 && deltaZ < 0) {
			yaw = (float) (-90 + Math.toDegrees(Math.atan(deltaZ / deltaX)));
		}
		
		return new float[] {yaw, pitch};
	}
	
}
