package xyz.hackage.rewritten.modules.render;

import java.awt.Color;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import xyz.hackage.rewritten.events.Event;
import xyz.hackage.rewritten.events.listeners.EventRender;
import xyz.hackage.rewritten.events.listeners.EventUpdate;
import xyz.hackage.rewritten.modules.Module;

public class Esp extends Module {

	public Esp() {
		super("Esp", "renders a box around players so you can see them through walls", Keyboard.KEY_C, Category.RENDER);
	}
	
	public void onEvent(Event ev) {
		if(ev instanceof EventRender) {
			for(Entity e : mc.theWorld.loadedEntityList) {
				if(e instanceof EntityPlayer && e != mc.thePlayer && !e.getName().startsWith(ChatFormatting.RED.toString())) {
					GlStateManager.pushMatrix();

					Color color = Color.getHSBColor((float)(System.currentTimeMillis() % 20000L)/1000, 0.8f, 1f);
					
					
//			        GL11.glTranslated(e.posX-mc.renderManager.viewerPosX, e.posY-mc.renderManager.viewerPosY, e.posZ-mc.renderManager.viewerPosZ);
//			        GL11.glRotatef(-mc.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
//			        GL11.glTranslated(-(e.posX-mc.renderManager.viewerPosX), -(e.posY-mc.renderManager.viewerPosY), -(e.posZ-mc.renderManager.viewerPosZ));

			        GL11.glDisable(GL11.GL_LIGHTING);
			        GL11.glDisable(GL11.GL_TEXTURE_2D);
			        GL11.glDisable(GL11.GL_DEPTH_TEST);
			        GL11.glDisable(GL11.GL_FOG);
			        GlStateManager.color(255,255,255);
			        if(!mc.thePlayer.isOnSameTeam((EntityLivingBase) e)) {
			        	GL11.glColor4f(color.getRed()/255f, color.getGreen()/255f, color.getBlue()/255f, 255);
			        } else {
			        	GL11.glColor4f(0.2f, 1f, 0.2f, 255);
			        }
			        GL11.glLineWidth(2);
			        
//			        GL11.glVertex3d(e.posX-mc.renderManager.viewerPosX-e.width, e.posY-mc.renderManager.viewerPosY, e.posZ-mc.renderManager.viewerPosZ);
//			        GL11.glVertex3d(e.posX-mc.renderManager.viewerPosX+e.width, e.posY-mc.renderManager.viewerPosY, e.posZ-mc.renderManager.viewerPosZ);
//			        GL11.glVertex3d(e.posX-mc.renderManager.viewerPosX+e.width, e.posY-mc.renderManager.viewerPosY+e.height, e.posZ-mc.renderManager.viewerPosZ);
//			        GL11.glVertex3d(e.posX-mc.renderManager.viewerPosX-e.width, e.posY-mc.renderManager.viewerPosY+e.height, e.posZ-mc.renderManager.viewerPosZ);
			       
//			        GL11.glVertex3d(e.lastTickPosX -0.5+ (e.posX - e.lastTickPosX)  - mc.renderManager.viewerPosX, e.lastTickPosY + (e.posY - e.lastTickPosY) * ((EventRender)ev).partialTicks  - mc.renderManager.viewerPosY + e.height, e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * ((EventRender)ev).partialTicks  - mc.renderManager.viewerPosZ);
//			        GL11.glVertex3d(e.lastTickPosX +0.5+ (e.posX - e.lastTickPosX)  - mc.renderManager.viewerPosX, e.lastTickPosY + (e.posY - e.lastTickPosY) * ((EventRender)ev).partialTicks  - mc.renderManager.viewerPosY + e.height, e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * ((EventRender)ev).partialTicks  - mc.renderManager.viewerPosZ);
//			        
//			        GL11.glVertex3d(e.lastTickPosX -0.5+ (e.posX - e.lastTickPosX) * ((EventRender)ev).partialTicks  - mc.renderManager.viewerPosX, e.lastTickPosY +(e.posY - e.lastTickPosY) * ((EventRender)ev).partialTicks  - mc.renderManager.viewerPosY, e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * ((EventRender)ev).partialTicks  - mc.renderManager.viewerPosZ);
//			        GL11.glVertex3d(e.lastTickPosX +0.5+ (e.posX - e.lastTickPosX) * ((EventRender)ev).partialTicks  - mc.renderManager.viewerPosX, e.lastTickPosY +(e.posY - e.lastTickPosY) * ((EventRender)ev).partialTicks  - mc.renderManager.viewerPosY, e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * ((EventRender)ev).partialTicks  - mc.renderManager.viewerPosZ);
			        
			        
//			        
//			        GL11.glBegin(GL11.GL_QUADS);
//			        
//			        GL11.glEnd();
			        
			        
			       
			        
			        
			        GlStateManager.pushMatrix();

			        GL11.glTranslated(e.posX-mc.renderManager.viewerPosX, e.posY-mc.renderManager.viewerPosY, e.posZ-mc.renderManager.viewerPosZ);
//			        GL11.glRotatef(-mc.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
			        GL11.glTranslated(-(e.posX-mc.renderManager.viewerPosX), -(e.posY-mc.renderManager.viewerPosY), -(e.posZ-mc.renderManager.viewerPosZ));

			        double x = e.lastTickPosX + (e.posX - e.lastTickPosX) * ((EventRender)ev).partialTicks;
			        
			        double y = e.lastTickPosY + (e.posY - e.lastTickPosY) * ((EventRender)ev).partialTicks-mc.renderManager.viewerPosY;
			        
			        double z = e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * ((EventRender)ev).partialTicks-mc.renderManager.viewerPosZ;
			        
//			        GL11.glDisable(GL11.GL_LIGHTING);
//			        GL11.glDisable(GL11.GL_TEXTURE_2D);
//			        GL11.glDisable(GL11.GL_DEPTH_TEST);
//			        GL11.glDisable(GL11.GL_FOG);
//			        GlStateManager.color(255,255,255);
////			        GL11.glColor4f(color.getRed(), color.getGreen(), color.getBlue(), 255);
//			        GL11.glLineWidth(2);
//			        GL11.glBegin(2);
//			        GL11.glVertex3d(x-mc.renderManager.viewerPosX, y, z);
//			        GL11.glVertex3d(x1-mc.renderManager.viewerPosX, y, z);
//			        GL11.glVertex3d(x1-mc.renderManager.viewerPosX, y+e.height, z);
//			        GL11.glVertex3d(x-mc.renderManager.viewerPosX, y+e.height, z);
//			        GL11.glEnd();
//
			        
			        
				double size = 0.5d;
			        
			        GL11.glBegin(GL11.GL_QUADS);
			        GL11.glVertex3d(x - mc.renderManager.viewerPosX+size, y - 0.2f, z+size);
			        GL11.glVertex3d(x - mc.renderManager.viewerPosX+size, y + e.height + 0.2f, z+size);
//			        GL11.glEnd();
			        
//			        GL11.glBegin(2);
			        GL11.glVertex3d(x - mc.renderManager.viewerPosX-size, y + e.height + 0.2f, z+size);
			        GL11.glVertex3d(x - mc.renderManager.viewerPosX-size, y - 0.2f, z+size);
			        GL11.glEnd();
			        
			        GL11.glBegin(GL11.GL_QUADS);
			        GL11.glVertex3d(x - mc.renderManager.viewerPosX-size, y - 0.2f, z-size);
			        GL11.glVertex3d(x - mc.renderManager.viewerPosX-size, y + e.height + 0.2f, z-size);
//			        GL11.glEnd();
			        
//			        GL11.glBegin(2);
			        GL11.glVertex3d(x - mc.renderManager.viewerPosX+size, y + e.height + 0.2f, z-size);
			        GL11.glVertex3d(x - mc.renderManager.viewerPosX+size, y - 0.2f, z-size);
			        GL11.glEnd();
			        
			        GL11.glBegin(GL11.GL_QUADS);
			        GL11.glVertex3d(x - mc.renderManager.viewerPosX-size, y - 0.2f, z+size);
			        GL11.glVertex3d(x - mc.renderManager.viewerPosX-size, y + e.height + 0.2f, z+size);
//			        GL11.glEnd();
			        
//			        GL11.glBegin(2);
			        GL11.glVertex3d(x - mc.renderManager.viewerPosX-size, y + e.height + 0.2f, z-size);
			        GL11.glVertex3d(x - mc.renderManager.viewerPosX-size, y - 0.2f, z-size);
			        GL11.glEnd();
			        
			        
			        GL11.glTranslated(x - mc.renderManager.viewerPosX+size, y +e.height+ 0.2f, z);
			        GL11.glRotated(180, 0, 0, 1);
			        GL11.glRotated(180, 0, 1, 0);
			        
			        GL11.glTranslated(-(x - mc.renderManager.viewerPosX+size), -(y +e.height+ 0.2f), -(z));
			        
			        GL11.glBegin(GL11.GL_QUADS);
			        GL11.glVertex3d(x - mc.renderManager.viewerPosX-size, y+e.height+0.2f, z-size);
			        GL11.glVertex3d(x - mc.renderManager.viewerPosX +size, y+e.height+0.2f, z-size);
			        GL11.glVertex3d(x - mc.renderManager.viewerPosX +size, y+e.height+0.2f, z+size);
			        GL11.glVertex3d(x - mc.renderManager.viewerPosX-size, y+e.height+0.2f, z+size);
			        
			        
			        GL11.glEnd();
			        
			        GL11.glTranslated(x - mc.renderManager.viewerPosX+size, y +e.height+ 0.2f, z);
			        GL11.glRotated(-180, 0, 0, 1);
			        GL11.glRotated(-180, 0, 1, 0);
			        GL11.glTranslated(-(x - mc.renderManager.viewerPosX+size), -(y +e.height+ 0.2f), -(z));
			        
			        
			        GL11.glTranslated(x - mc.renderManager.viewerPosX+size, y - 0.2f, z);
			        GL11.glRotated(180, 0, 1, 0);
			        GL11.glTranslated(-(x - mc.renderManager.viewerPosX+size), -(y - 0.2f), -(z));
			        
			        
			        GL11.glBegin(GL11.GL_QUADS);
			        GL11.glVertex3d(x - mc.renderManager.viewerPosX+size, y - 0.2f, z+size);
			        GL11.glVertex3d(x - mc.renderManager.viewerPosX+size, y + e.height + 0.2f, z+size);
//			        GL11.glEnd();
			        
//			        GL11.glBegin(2);
			        GL11.glVertex3d(x - mc.renderManager.viewerPosX+size, y + e.height + 0.2f, z-size);
			        GL11.glVertex3d(x - mc.renderManager.viewerPosX+size, y - 0.2f, z-size);
			        GL11.glEnd();
			        
//			        GL11.glTranslated(x - mc.renderManager.viewerPosX+size, y - 0.2f, z);
//			        GL11.glRotated(-180, 0, 1, 0);
//			        GL11.glTranslated(-(x - mc.renderManager.viewerPosX+size), -(y - 0.2f), -(z));
			        
			        
			        
			        
			        
			        GL11.glRotatef(-mc.renderManager.playerViewY, 0F, 1F, 0F);
			        GL11.glRotatef(mc.renderManager.playerViewX, 1F, 0F, 0F);
			        GL11.glEnable(GL11.GL_DEPTH_TEST);
			        GL11.glEnable(GL11.GL_FOG);
			        GL11.glEnable(GL11.GL_TEXTURE_2D);
			        GlStateManager.popMatrix();
			        

			        GL11.glRotatef(-mc.renderManager.playerViewY, 0F, 1F, 0F);
			        GL11.glRotatef(mc.renderManager.playerViewX, 1F, 0F, 0F);
			        
//			        GL11.glLineWidth(2900);
//			        GL11.glBegin(2);
//			        GL11.glVertex3d(0, mc.thePlayer.getEyeHeight(), 0);
//			        GL11.glVertex3d(e.posX-mc.thePlayer.posX, e.posY-mc.thePlayer.posY, e.posZ-mc.thePlayer.posZ);
//			        
//			        GL11.glEnd();
			        
			        GL11.glEnable(GL11.GL_DEPTH_TEST);
			        GL11.glEnable(GL11.GL_FOG);
			        GL11.glEnable(GL11.GL_TEXTURE_2D);
			        GlStateManager.popMatrix();
				}
			}
		}
	}

}
