package xyz.hackage.rewritten.gui;

import java.awt.Color;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import xyz.hackage.rewritten.Client;
import xyz.hackage.rewritten.login.GuiAltLogin;
import xyz.hackage.rewritten.util.GuiUtil;

public class BetterMainMenu extends GuiScreen {

	public BetterMainMenu () {		}
	
	public void initGui() {
//			this.buttonList.add(new GuiButton(1, this.width / 2 - 100, p_73969_1_, I18n.format("menu.singleplayer", new Object[0])));
//	      this.buttonList.add(new GuiButton(2, this.width / 2 - 100, p_73969_1_ + p_73969_2_ * 1, I18n.format("menu.multiplayer", new Object[0])));
//		this.buttonList.add(new GuiButton(1, 5, 5, "poger"));
		this.buttonList.add(new GuiButton(1, 5, 40, 140, 20, "Singleplayer"));
		this.buttonList.add(new GuiButton(2, 5, 60, 140, 20, "Multiplayer"));
		this.buttonList.add(new GuiButton(3, 5, 80, 140, 20, "Login"));
		this.buttonList.add(new GuiButton(4, 5, 100, 140, 20, "Settings"));
		
		this.buttonList.add(new GuiButton(5, 5, this.height-25, 140, 20, "Quit"));
		
		
		
		System.out.println("init");
	}
	
	public void actionPerformed(GuiButton button) {
		if(button.id == 1) {
			this.mc.displayGuiScreen(new GuiSelectWorld(new BetterMainMenu()));
		}
		if(button.id == 2) {
			this.mc.displayGuiScreen(new GuiMultiplayer(new BetterMainMenu()));
		}
		if(button.id == 3) {
			this.mc.displayGuiScreen(new GuiAltLogin(new BetterMainMenu()));
		}
		if(button.id == 4) {
			this.mc.displayGuiScreen(new GuiOptions(new BetterMainMenu(), mc.gameSettings));
		}
		if(button.id == 5) {
			this.mc.shutdown();
		}
		
		
		
		
	}
	
	
	public void keyTyped(char typedChar, int keyCode) {
//		if(keyCode == Keyboard.KEY_RSHIFT) {
//			this.mc.displayGuiScreen(new ClickGui(this));
//		}
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		ScaledResolution sr = new ScaledResolution(mc);
		this.drawGradientRect(0, 0, this.width, this.height, 0xff30ff80, 0xffffffff);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		
		this.mc.getTextureManager().bindTexture(new ResourceLocation("hackage/icons/wal.jpg"));
		this.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, sr.getScaledWidth(), sr.getScaledHeight(), sr.getScaledWidth(), sr.getScaledHeight());

		this.drawRect(0, 0, 150, sr.getScaledHeight(), 0x90000000);
      
//		 GlStateManager.pushMatrix();
//         GL11.glEnable(GL11.GL_BLEND);
//         GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//         GL11.glEnable(GL11.GL_LINE_SMOOTH);
//         GL11.glDisable(GL11.GL_TEXTURE_2D);
//         GL11.glEnable(GL11.GL_CULL_FACE);
//         GL11.glDisable(GL11.GL_DEPTH_TEST);
//         GL11.glDisable(GL11.GL_LIGHTING);
//         
////         GL11.glColor3d(255, 0, 0);
//         
//         double twicePi = 2.0 * Math.PI;
//         double radius = 0.3d;
//         
//         GL11.glBegin(GL11.GL_QUADS);
//         GL11.glVertex2i(0,0);
//         GL11.glVertex2i(500,0);
//         GL11.glVertex2i(500,-5);
//         GL11.glVertex2i(0,-5);
//         
////         GL11.glVertex2i(this.xPosition, this.yPosition);
////         for (i = 0; i <= 20; i++)   {
////             GL11.glVertex2d (
////                 (this.xPosition + (radius * Math.cos(i * twicePi / 20))), (this.yPosition + (radius * Math.sin(i * twicePi / 20)))
////                 );
////         }
//         GL11.glEnd();
//         
//         GL11.glEnable(GL11.GL_DEPTH_TEST);
//         GL11.glEnable(GL11.GL_TEXTURE_2D);
//         GL11.glDisable(GL11.GL_BLEND);
//         GL11.glDisable(GL11.GL_LINE_SMOOTH);
//         GlStateManager.popMatrix();
		
		
		GlStateManager.color(255, 255, 255);
		this.mc.getTextureManager().bindTexture(new ResourceLocation("hackage/icons/logo.png"));
		this.drawModalRectWithCustomSizedTexture(130, 5, 0, 0, 120, 120, 120, 120);
		Client.sbufr.drawString("Sentinel", 46, 7, -1);
//		Client.ufr.drawString(Client.VERSION, this.width - Client.ufr.getStringWidth(Client.VERSION) - 4, this.height - 14, 0xff000000);
		
//		this.drawRect(150, 0, sr.getScaledWidth(), sr.getScaledHeight(), -1);
//		this.drawRect(150+50, 50, sr.getScaledWidth()-50, sr.getScaledHeight()-50, -1);
//		this.drawRect(((sr.getScaledWidth()+150)/2)-100, (sr.getScaledHeight()/2)-80, ((sr.getScaledWidth()+150)/2)+100, (sr.getScaledHeight()/2)+80, -1);
//		GuiUtil.drawRoundedRect(((sr.getScaledWidth()+150)/2)-100, (sr.getScaledHeight()/2)-80, ((sr.getScaledWidth()+150)/2)+100, (sr.getScaledHeight()/2)+80, -1);
		GuiUtil.renderRoundedQuad(new Vec3(((sr.getScaledWidth()+150)/2)-100, (sr.getScaledHeight()/2)-80, 0), new Vec3(((sr.getScaledWidth()+150)/2)+100, (sr.getScaledHeight()/2)+80, 0), 5, new Color(0x80000000, true));
		GlStateManager.color(255, 255, 255);
		this.drawHorizontalLine((sr.getScaledWidth()+150)/2-105, (sr.getScaledWidth()+150)/2+104, (sr.getScaledHeight()/2)-62, 0xffffffff);
		Client.ufr.drawCenteredString(Client.VERSION, (sr.getScaledWidth()+150)/2, (sr.getScaledHeight()/2)-80, -1);
		Client.sufr.drawCenteredString("Merged Pine and SentinalPVP to create Sentinel", (sr.getScaledWidth()+150)/2, (sr.getScaledHeight()/2)-60, -1);

		Client.sufr.drawString("Created by ice & BRUDDA_OSAS", sr.getScaledWidth()-Client.sufr.getWidth("Created by ice & BRUDDA_OSAS")-2, sr.getScaledHeight()-10, -1);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
}
