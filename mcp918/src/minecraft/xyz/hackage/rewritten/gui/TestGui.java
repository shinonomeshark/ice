package xyz.hackage.rewritten.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

public class TestGui extends GuiScreen {

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		mc.fontRendererObj.drawStringWithShadow("test", 5, 50, -1);
		
		GlStateManager.color(255, 255, 255);
		GlStateManager.disableTexture2D();
		
		GL11.glPushMatrix();
		
		GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_LIGHTING);
		
		GL11.glBegin(GL11.GL_POLYGON);
		
		GL11.glVertex2d(10, 10);
		GL11.glVertex2d(50, 10);
		GL11.glVertex2d(50, 50);
		GL11.glVertex2d(10, 50);
		
		GL11.glEnd(); 
		
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		
		GL11.glPopMatrix();
	}
	
}
