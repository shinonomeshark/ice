package xyz.hackage.rewritten.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import xyz.hackage.rewritten.Client;
import xyz.hackage.rewritten.HUD;
import xyz.hackage.rewritten.modules.Module;
import xyz.hackage.rewritten.modules.Module.Category;
import xyz.hackage.rewritten.modules.render.ClickGuiModule;
import xyz.hackage.rewritten.modules.render.HudModule;
import xyz.hackage.rewritten.modules.settings.BooleanSetting;
import xyz.hackage.rewritten.modules.settings.ModeSetting;
import xyz.hackage.rewritten.modules.settings.Setting;
import xyz.hackage.rewritten.util.GuiUtil;
import xyz.hackage.rewritten.util.Notification;
import xyz.hackage.rewritten.util.NotificationManager;
import xyz.hackage.rewritten.util.RainbowUtil;

public class SkeetWindow {
	
	int x = 0, y = 0;
	boolean open = true;
	boolean dragging = false;
	int dragX = 0;
	int dragY = 0;
	String openMod = "";
	
	
	
	public SkeetWindow(int xPos, int yPos) {
		x = xPos;
		y = yPos;
	}
	
	public void mouseReleased(int mouseX, int mouseY, int state) {

	}
	
	public void mouseClick(int mouseX, int mouseY, int mouseButton) {
		
	}
	
	public void draw(int mouseX, int mouseY) {
		Gui.drawRect(x, y, x+300, y+300, 0xff101010);
		GuiUtil.drawRectOutline(x, y, x+300, y+300, 0xff202020);
		GuiUtil.drawRectOutline(x, y, x+300, y+300, 0xff202020);
		
	}
}
