package xyz.hackage.rewritten.gui;

import java.awt.Color;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import xyz.hackage.rewritten.Client;
import xyz.hackage.rewritten.HUD;
import xyz.hackage.rewritten.modules.Module;
import xyz.hackage.rewritten.util.AnimationUtil;
import xyz.hackage.rewritten.util.GuiUtil;
import xyz.hackage.rewritten.util.Notification;
import xyz.hackage.rewritten.util.NotificationManager;
import xyz.hackage.rewritten.util.SystemTimerUtil;

public class GuiBind extends GuiScreen {

	private Module m;
	private GuiScreen g = null;
	private ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
	private SystemTimerUtil tim = new SystemTimerUtil();
	
	public GuiBind (Module moduleToBind) {
		m = moduleToBind;
	}
	
	public GuiBind (Module moduleToBind, GuiScreen previousScreen) {
		m = moduleToBind;
		g = previousScreen;
	}
	
	
	
	public void initGui() {
		Minecraft.getMinecraft().entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
		tim.resetTime();
		Client.addChat(m.name + " is currently bound to " + Keyboard.getKeyName(m.getKey()));
//		Client.addChat("kaboo1m");
	}
	
	public void onGuiClosed() {
		Minecraft.getMinecraft().entityRenderer.loadEntityShader(null);
        super.onGuiClosed();
	}
	
	public void keyTyped(char typedChar, int keyCode) {
//		Client.addChat("" + typedChar + " | " + keyCode);
		if(keyCode == Keyboard.KEY_ESCAPE) {
			m.keyCode = Keyboard.KEY_NONE;
			NotificationManager.AssignNotification(new Notification(m.name + " was unbound", 2000l, -1));
			mc.displayGuiScreen(g);
		} else {
			NotificationManager.AssignNotification(new Notification(m.name + " was bound to " + typedChar, 2000l, -1));
			m.keyCode = keyCode;
			mc.displayGuiScreen(g);
		}
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		sr = new ScaledResolution(Minecraft.getMinecraft());
		
		long animTime = 0;
		if(tim.getTime() < 550) {
			animTime = (long) tim.getTime();
		} else {
			animTime = 550l;
		}
		
		
//		Client.addChat(animTime + "");
		double animTing = AnimationUtil.easeOutExpo(animTime/550d);
		
		GlStateManager.pushMatrix();
		
		GlStateManager.popMatrix();
		
		GlStateManager.pushMatrix();
//		Gui.drawRect(0, 0, sr.getScaledWidth(), sr.getScaledHeight(), 0x80000000);
		GlStateManager.rotate(-90f, 1, 0, 1);
//		Gui.drawRect(0, 0, sr.getScaledWidth(), sr.getScaledHeight(), 0x80000000);
		GlStateManager.rotate((float) ((float) 90*animTing), 1, 0, 1);
		
		
		
//		Gui.drawRect(sr.get, mouseY, mouseY, mouseX, mouseY);
		
//		Client.bufr.getS
		
		int thing = (int) (Client.ufr.getStringWidth(m.name + " is bound to " + Keyboard.getKeyName(m.getKey()))/1.8);
		
		GuiUtil.renderRoundedQuad(new Vec3(sr.getScaledWidth()/2-thing, sr.getScaledHeight()/2-20, 0), new Vec3(sr.getScaledWidth()/2+thing, sr.getScaledHeight()/2+20, 0), 5, new Color(0xff000000, true));
		
		Client.ufr.drawCenteredString(m.name + " is bound to " + Keyboard.getKeyName(m.getKey()), sr.getScaledWidth()/2, sr.getScaledHeight()/2-10, HUD.c1);
		Client.sufr.drawCenteredString("press any key to rebind", sr.getScaledWidth()/2, sr.getScaledHeight()/2+10, -1);
		GlStateManager.popMatrix();
	}
	
}
