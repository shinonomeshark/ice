package net.minecraft.client.renderer;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;

public class ChestRenderer {
   public void renderChestBrightness(Block p_178175_1_, float color) {
	  GL11.glDisable(GL11.GL_DEPTH_TEST);
      GlStateManager.color(color, color, color, 1.0F);
      GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
      TileEntityItemStackRenderer.instance.renderByItem(new ItemStack(p_178175_1_));
      GL11.glEnable(GL11.GL_DEPTH_TEST);
   }
}
