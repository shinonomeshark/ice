package xyz.hackage.rewritten.modules.combat;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;
import xyz.hackage.rewritten.HUD;
import xyz.hackage.rewritten.events.Event;
import xyz.hackage.rewritten.events.listeners.EventMotion;
import xyz.hackage.rewritten.events.listeners.EventUpdate;
import xyz.hackage.rewritten.modules.Module;
import xyz.hackage.rewritten.modules.Module.Category;
import xyz.hackage.rewritten.modules.settings.ModeSetting;
import xyz.hackage.rewritten.util.RotateUtil;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.lwjgl.input.Keyboard;

public class HitboxAura extends Module {

	private EntityLivingBase target;
    private long current, last;
    private int delay = 0;
    private float yaw, pitch;
    private boolean others;
    
//    private ModeSetting mode = new ModeSetting("mode", "hitbox", "hitbox", "rotational");

    public HitboxAura() {
		super("KillAura", "haha brr", Keyboard.KEY_R, Category.COMBAT);
		// TODO Auto-generated constructor stub
	}
    
    public void onEvent(Event e) {
    	if(e instanceof EventUpdate && e.isPre()) {
//    		target = getClosest(mc.playerController.getBlockReachDistance());
    		target = getClosest(10);
            if(target == null) {
//            	this.suffix = "hitbox";
            	HUD.attacking = null;
                return;
            }
            updateTime();
            yaw = mc.thePlayer.rotationYaw;
            pitch = mc.thePlayer.rotationPitch;
            HUD.attacking = (Entity) target;
            if(current - (Math.random() * ( 100 - 1 )) - last > delay) {
//            	mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C05PacketPlayerLook(target.rotationYaw, target.cameraPitch, true));
                if(mc.thePlayer.onGround) {
//                	mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY+0.1, mc.thePlayer.posZ);
                }
            	attack(target);
//            	mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C05PacketPlayerLook(RotateUtil.getRotations(target)[0], RotateUtil.getRotations(target)[1], mc.thePlayer.onGround));
//                mc.thePlayer.setPositionAndRotation(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, RotateUtil.getRotations(target)[0], RotateUtil.getRotations(target)[1]);
            	resetTime();
            }
            
            if(target == null)
                return;
//            mc.thePlayer.rotationYaw = yaw;
//            mc.thePlayer.rotationPitch = pitch;
    	} else if(e instanceof EventMotion) {
    		
    	}
    }
    
   

    private void attack(Entity e) {
//    	mc.thePlayer.setPosition(e.posX, e.posY, e.posZ);
//    	System.out.println(e.getDisplayName().getUnformattedText().contains("??8NPC"));
//    	if(e.getDisplayName() == "SHOP") {
    		
//    	} else {
    	
    	mc.thePlayer.swingItem();
        mc.playerController.attackEntity(mc.thePlayer, e);
//    	}
    }

    private void updateTime() {
        current = (System.nanoTime() / 1000000L);
    }

    private void resetTime() {
        last = (System.nanoTime() / 1000000L);
    }

    private EntityLivingBase getClosest(double range) {
        double dist = range;
        EntityLivingBase target = null;
        for(Object object : mc.theWorld.loadedEntityList) {
            Entity entity = (Entity)object;
            if(entity instanceof EntityLivingBase) {
                EntityLivingBase player = (EntityLivingBase)entity;
                if(canAttack(player)) {
                	
                	// !(player.isOnSameTeam(mc.thePlayer)) && 
                    if(!(player.isOnSameTeam(mc.thePlayer)) && !(player.getDisplayName().getUnformattedText().contains("NPC")) && !(player.isOnSameTeam(mc.thePlayer)) && !(player.isInvisible()) && !(player instanceof EntityArmorStand) && !(player instanceof EntitySlime) || others) {
                        double currentDist = mc.thePlayer.getDistanceToEntity(player);
                        if(currentDist <= dist) {
                            dist = currentDist;
                            target = player;
//                            this.suffix = "hitbox: " + player.getName() + "  ";
                        }
                    }
                }
            }
        }

        return target;
    }

    private boolean canAttack(EntityLivingBase player) {
        return player != mc.thePlayer && player.isEntityAlive() && mc.thePlayer.getDistanceToEntity(player) <= mc.playerController.getBlockReachDistance();
//    		return player instanceof EntityAnimal;
    }
    
    public void onDisable(){
    	HUD.attacking = null;
    }
}
