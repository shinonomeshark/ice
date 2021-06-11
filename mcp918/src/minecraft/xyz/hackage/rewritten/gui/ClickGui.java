package xyz.hackage.rewritten.gui;

import java.awt.Color;
import java.io.IOException;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import xyz.hackage.rewritten.Client;
import xyz.hackage.rewritten.HUD;
import xyz.hackage.rewritten.modules.Module.Category;
import xyz.hackage.rewritten.util.AnimationUtil;
import xyz.hackage.rewritten.util.Notification;
import xyz.hackage.rewritten.util.NotificationManager;
import xyz.hackage.rewritten.util.SystemTimerUtil;

public class ClickGui extends GuiScreen {

	private ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
	private SystemTimerUtil tim = new SystemTimerUtil(); //tim 3 funnie mee mee
	// how are you tim
	private boolean skidma = false;
	private boolean closeFlag = false;
	private GuiScreen closeTo = null;
	
	private DraggableTab move = new DraggableTab(Category.MOVEMENT, "move", 5, 30);
	private DraggableTab combat = new DraggableTab(Category.COMBAT, "combat", 110, 30);
	private DraggableTab render = new DraggableTab(Category.RENDER, "render", 215, 30);
	private DraggableTab player = new DraggableTab(Category.PLAYER, "player", 320, 30);
	
	public ClickGui() {
		
	}
	public ClickGui(GuiScreen previousScreen) {
		closeTo = previousScreen;
	}
	
	private void closeScreen() {
		this.mc.displayGuiScreen((GuiScreen)closeTo);
        if(this.mc.currentScreen == null) {
           this.mc.setIngameFocus();
        }
        if(Client.modOn("clickgui")) {
        	Client.getMod("clickgui").toggle();
        	NotificationManager.AssignNotification(new Notification("disabled clickgui", 2000l, 0xffff8080));
        }
	}
	
	private void closeScreenWithAnim() {
		skidma = true;
		closeFlag = true;
		tim.resetTime();
	}
	
	public void initGui() {
		tim.resetTime();
		skidma = false;
		closeFlag = false;
//		Minecraft.getMinecraft().entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
	}
	
	public void onGuiClosed() {
//		Minecraft.getMinecraft().entityRenderer.loadEntityShader(null);
        super.onGuiClosed();
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		sr = new ScaledResolution(mc);
		
		long animTime = 0;
		if(tim.getTime() < 550) {
			animTime = (long) tim.getTime();
		} else {
			animTime = 550l;
			if(closeFlag) {
				closeScreen();
			}
		}

		
//		Client.sbufr.drawStringWithShadow("pine " + Client.VERSION, 5, 0, HUD.c1);
		
		
		double animTing = AnimationUtil.easeOutExpo(animTime/550d);
		
//		Color a = new Color(0, 0, 0, (int) (210*animTing));
		
		if(skidma) {
			GlStateManager.translate(0, (sr.getScaledHeight())*animTing, 0);
		}
		
		
		
		
		if(!skidma) {
			GlStateManager.translate(sr.getScaledWidth()/2, sr.getScaledHeight()/2, 0);
			GlStateManager.scale(1*animTing, 1*animTing, 0);
			GlStateManager.translate(-(sr.getScaledWidth()/2*animTing), -(sr.getScaledHeight()/2*animTing), 0);
		}
		
//		Gui.drawRect(0, 0, sr.getScaledWidth(), sr.getScaledHeight(), 0xaa000000);
//		Client.sbufr.drawStringWithShadow("clickgui", 80, 0, HUD.c1);
		
		move.draw(mouseX, mouseY);
		combat.draw(mouseX, mouseY);
		render.draw(mouseX, mouseY);
		player.draw(mouseX, mouseY);
	}
	
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		move.mouseClick(mouseX, mouseY, mouseButton);
		combat.mouseClick(mouseX, mouseY, mouseButton);
		render.mouseClick(mouseX, mouseY, mouseButton);
		player.mouseClick(mouseX, mouseY, mouseButton);
	}
	
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		move.mouseReleased(mouseX, mouseY, state);
		combat.mouseReleased(mouseX, mouseY, state);
		render.mouseReleased(mouseX, mouseY, state);
		player.mouseReleased(mouseX, mouseY, state);
		
	}
	
	protected void keyTyped(char typedChar, int keyCode) {
		if(keyCode == Keyboard.KEY_RSHIFT || keyCode == Keyboard.KEY_ESCAPE) {
			if(!closeFlag)
				closeScreen();
//				closeScreenWithAnim();
		}
	}
}
