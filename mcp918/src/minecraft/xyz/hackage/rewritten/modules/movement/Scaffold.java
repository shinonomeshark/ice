package xyz.hackage.rewritten.modules.movement;

//import javax.vecmath.Vector3d;

import org.lwjgl.input.Keyboard;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3i;
import xyz.hackage.rewritten.Client;
import xyz.hackage.rewritten.events.Event;
import xyz.hackage.rewritten.events.listeners.EventMotion;
import xyz.hackage.rewritten.events.listeners.EventUpdate;
import xyz.hackage.rewritten.modules.Module;
import xyz.hackage.rewritten.modules.settings.BooleanSetting;

// kill me

public class Scaffold extends Module {

	int ticks = 0;
	int tt = 0;
	int tta = 0;
	boolean flick = false;
	boolean sneakFlag = false;
	
	float[] yp = new float[] {0,0};
	
	
	BooleanSetting sneak = new BooleanSetting("sneak", true);
	
	
	ItemStack blockToHaveASeizureWith = null;
	
	public Scaffold() {
		super("Scaffold", "have a seizure", Keyboard.KEY_X, Category.MOVEMENT);
		settings.add(sneak);
	}
		
	public void onEnable() {
//		mc.thePlayer.setSneaking(false);
//		tt= (int) (mc.thePlayer.posY-1);
//		int slot = 0;
//		for(ItemStack i : mc.thePlayer.inventory.mainInventory) {
//			if(i != null) {
////				System.out.println(slot + "got a pickle chin a boi " + i.getDisplayName());
//				if(slot <= 8) {
//					if(i.getItem() instanceof ItemBlock) {
////					System.out.println(i.getDisplayName() + " go AISJfodafiuhadifhg");
//					blockToHaveASeizureWith = i;
////					mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(slot));
//					}
//				}
//			} else {
////				System.out.println("ice cream machine broke");
//			}
//			
////			mc.thePlayer.setPositionAndRotation(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, -130, 75);
//			
//			slot++;
//		}
		mc.timer.timerSpeed = 1f;
	}
		
	public void onDisable() {
		mc.gameSettings.keyBindSneak.pressed = false;
		mc.timer.timerSpeed = 1f;
//		mc.thePlayer.motionY = 0;
		mc.thePlayer.setSneaking(false);
		int slot = 0;
//		if(mc.thePlayer.getHeldItem() != null) {
//			for(ItemStack i : mc.thePlayer.inventory.mainInventory) {
//				if(i == mc.thePlayer.getHeldItem()) {
//					mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(slot));
//				}
//				slot++;
//			}
//		}
	}
	
	public void onEvent(Event e) {
		
		
		if(e instanceof EventUpdate) {
			ticks++;
			
			blockToHaveASeizureWith = null;
			
			if(mc.thePlayer.onGround) {
//				mc.timer.timerSpeed = 1.5f
//				;
//				mc.thePlayer.jump();
//				mc.thePlayer.motionY -= 0.1f;
			} else {
//				mc.timer.timerSpeed = 1f;
			}
			
//			if(ticks % 5 == 0) {
//				mc.timer.timerSpeed = 1.4f;
//			} else if(ticks % 2 == 0) {
//				mc.timer.timerSpeed = 0.9f;
//			}
			
//			mc.thePlayer.setSprinting(true);
			if(ticks % 30 == 0) {
//				mc.thePlayer.setSneaking(true);
			}
			
			
			
//			if(mc.thePlayer.getHeldItem().getItem() != null) {
//				if(mc.thePlayer.getHeldItem().getItem() instanceof ItemBlock) {
//					blockToHaveASeizureWith = mc.thePlayer.getHeldItem();
//				}
//			}
			
			
			
			if(getBlock(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY-1, mc.thePlayer.posZ)) instanceof BlockAir) {
//				if(!mc.thePlayer.onGround) {
//				mc.gameSettings.keyBindSneak.pressed = true;
//				mc.thePlayer.jump();
				if(!sneakFlag) {
					tta = 0;
					sneakFlag = true;
//					mc.thePlayer.sendQueue.addToSendQueue(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.START_SNEAKING));
//					mc.thePlayer.
//					mc.timer.timerSpeed = 1.7f;
					if(sneak.getValue())	
						mc.gameSettings.keyBindSneak.pressed = true;
//					mc.thePlayer.addChatMessage(new ChatComponentText(ChatFormatting.GREEN + "started sneak"));
//					mc.thePlayer.motionX *= 0.6f;
//					mc.thePlayer.motionZ *= 0.6f;
				}
				
			} else {
				if(sneakFlag) {
//					tta++;
//					if(tta >1) {
//						mc.thePlayer.sendQueue.addToSendQueue(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.STOP_SNEAKING));
//					mc.timer.timerSpeed = 1f;
					if(sneak.getValue())	
						mc.gameSettings.keyBindSneak.pressed = false;	
//						mc.thePlayer.addChatMessage(new ChatComponentText(ChatFormatting.RED + "stopped sneak"));
						sneakFlag = false;
						tta = 0;
//					}
				}
			}
			
			placeBlockSimple(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY-1, mc.thePlayer.posZ));
			
		}
		if(e instanceof EventMotion) {
//			((EventMotion) e).setPitch(75);
//			((EventMotion) e).setYaw(mc.thePlayer.rotationYaw-130);
//			if(flick) {
//				tt++;
//				if(tt < 5) {
			
//			BlockPos p = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY-1, mc.thePlayer.posZ);
			
			
//			float[] rots = new float[] {yaw, pitch};
			
//			return new float[] {yaw, pitch};
			((EventMotion) e).setYaw(yp[0]);
			((EventMotion) e).setPitch(yp[1]);

//				} else {
//					tt = 0;
//					flick = false;
//				}
			
//			mc.thePlayer.setPositionAndRotation(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, yp[0], yp[1]);
			
//			}
			if(getBlock(new BlockPos(mc.thePlayer.posX, tt, mc.thePlayer.posZ)) instanceof BlockAir) {
				
			}
			
		}
	}
	
	public boolean placeBlockSimple(BlockPos pos)
    {
		
//		System.out.println("sus");
		
        Vec3 eyesPos = new Vec3(mc.thePlayer.posX,
            mc.thePlayer.posY + mc.thePlayer.getEyeHeight(), mc.thePlayer.posZ);
        
        if(ticks % 2 == 0) {
        	return false;
        }
        
        if(!(getBlock(pos) instanceof BlockAir)) {
        	System.out.println("a");
        	return false;
        }
        
        for(EnumFacing side : EnumFacing.values())
        {
        	
            BlockPos neighbor = pos.offset(side);
            EnumFacing side2 = side.getOpposite();
            
            System.out.println(side + " : " + neighbor);
            
            // check if neighbor can be right clicked
            if(!getBlock(neighbor)
                .canCollideCheck(mc.theWorld.getBlockState(neighbor), false)) {
//            	System.out.println("b");
            	continue;
            }
            
            
//            if(eyesPos.squareDistanceTo(new Vec3(pos.getX(), pos.getX(), pos.getZ())) >= eyesPos
//    				.squareDistanceTo(new Vec3(neighbor.getX(), neighbor.getY(), neighbor.getZ())))
//    				continue;
            
//            if(eyesPos.squaredDistanceTo(Vec3i.ce) >= eyesPos
//    				.squaredDistanceTo(Vec3d.ofCenter(neighbor)))
//    				continue;
            
//            if(eyesPos.squareDistanceTo(new Vec3(pos.getX(), pos.getY(), pos.getZ())) > eyesPos.squareDistanceTo(new Vec3(neighbor.getX(), neighbor.getY(), neighbor.getZ()))) {
//            	continue;
//            }
            
            
            System.out.println(getBlock(pos));
                
            
            Vec3 hitVec = new Vec3(neighbor.getX(), neighbor.getY(), neighbor.getZ()).addVector(0.5, 0.5, 0.5)
                .add(new Vec3(side2.getFrontOffsetX()*0.5, side2.getFrontOffsetY()*0.5, side2.getFrontOffsetZ()*0.5));
            
            // check if hitVec is within range (6 blocks)
            if(eyesPos.squareDistanceTo(hitVec) > 36)
//            	System.out.println(eyesPos.squareDistanceTo(hitVec));
                continue;
            
            
            
//            if(ticks % 2 == 0) {
//				mc.thePlayer.setSneaking(false);
//				if(mc.gameSettings.keyBindJump.pressed) {
//					mc.thePlayer.jump();
//				}
//			} else {
//				mc.thePlayer.setSneaking(true);
//			}
            // place block
            // blockToHaveASeizureWith != null && 
            

            BlockPos rotPos = new BlockPos(new Vec3(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ));
            
            if(side == EnumFacing.EAST) {
            	rotPos = new BlockPos(new Vec3(mc.thePlayer.posX+0.3f, mc.thePlayer.posY-5f, mc.thePlayer.posZ));
			} else if (side == EnumFacing.WEST) {
				rotPos = new BlockPos(new Vec3(mc.thePlayer.posX-0.3f, mc.thePlayer.posY-5f, mc.thePlayer.posZ));
			} else if(side == EnumFacing.NORTH) {
				rotPos = new BlockPos(new Vec3(mc.thePlayer.posX, mc.thePlayer.posY+0f, mc.thePlayer.posZ+121f));
			} else if(side == EnumFacing.SOUTH) {
				rotPos = new BlockPos(new Vec3(mc.thePlayer.posX, mc.thePlayer.posY+1f, mc.thePlayer.posZ+15f));
//				Client.addChat("saso");
			}
            
            
            
			double deltaX = rotPos.getX() /*+ (p.getX()-e.lastTickrotPosX)*/ - mc.thePlayer.posX+0.5f;
			double deltaY = rotPos.getY() - 3.5 - mc.thePlayer.posY + mc.thePlayer.getEyeHeight();
			double deltaZ = rotPos.getZ() - mc.thePlayer.posZ+0.5f;
			
//			if(side == EnumFacing.EAST) {
//				deltaX = rotPos.getX() /*+ (p.getX()-e.lastTickrotPosX)*/ - mc.thePlayer.posX;
//			} else if (side == EnumFacing.WEST) {
//				deltaX = rotPos.getX() /*+ (p.getX()-e.lastTickrotPosX)*/ - mc.thePlayer.posX - 1f;
//			} else if(side == EnumFacing.NORTH) {
//				deltaZ = rotPos.getZ() - mc.thePlayer.posZ;
//			} else if(side == EnumFacing.SOUTH) {
//				deltaZ = rotPos.getZ() - mc.thePlayer.posZ - 1f;
//			}
			
			double distance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaX, 2));
			
			float yaw = (float) Math.toDegrees(-Math.atan(deltaX / deltaZ));
			float pitch = (float) -Math.toDegrees(Math.atan(deltaY / distance));
			
			if(deltaX < 0 && deltaZ < 0) {
				yaw = (float) (90 + Math.toDegrees(Math.atan(deltaZ / deltaX)));
			} else if(deltaX > 0 && deltaZ < 0) {
				yaw = (float) (-90 + Math.toDegrees(Math.atan(deltaZ / deltaX)));
			}
			
			yp = new float[] {yaw, pitch};
            
            if(ticks % 1 == 0) {
            	mc.thePlayer.setSneaking(false);
//            	mc.thePlayer.swingItem();
            	mc.thePlayer.sendQueue.addToSendQueue(new C0APacketAnimation());
	            mc.playerController.onPlayerRightClick(mc.thePlayer, mc.theWorld,
	                mc.thePlayer.getHeldItem(), neighbor, side2, hitVec);
	            flick = true;
            } else {
            	mc.thePlayer.setSneaking(true);
            }
            
            return true;
        }
//        System.out.println("e");
        return false;
    }
	
	public static Block getBlock(BlockPos pos)
    {
        return Minecraft.getMinecraft().theWorld.getBlockState(pos).getBlock();
    }

}
