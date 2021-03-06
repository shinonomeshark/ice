package net.minecraft.client.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import xyz.hackage.rewritten.Client;

public class GuiButton extends Gui {
   protected static final ResourceLocation buttonTextures = new ResourceLocation("textures/gui/widgets.png");
   protected int width;
   protected int height;
   public int xPosition;
   public int yPosition;
   public String displayString;
   public int id;
   public boolean enabled;
   public boolean visible;
   protected boolean hovered;

   public GuiButton(int buttonId, int x, int y, String buttonText) {
      this(buttonId, x, y, 200, 20, buttonText);
   }

   public GuiButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
      this.width = 200;
      this.height = 20;
      this.enabled = true;
      this.visible = true;
      this.id = buttonId;
      this.xPosition = x;
      this.yPosition = y;
      this.width = widthIn;
      this.height = heightIn;
      this.displayString = buttonText;
   }

   protected int getHoverState(boolean mouseOver) {
      int i = 1;
      if(!this.enabled) {
         i = 0;
      } else if(mouseOver) {
         i = 2;
      }

      return i;
   }

   public void drawButton(Minecraft mc, int mouseX, int mouseY) {
      if(this.visible) {
         FontRenderer fontrenderer = mc.fontRendererObj;
         mc.getTextureManager().bindTexture(buttonTextures);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
         int i = this.getHoverState(this.hovered);
//         GlStateManager.enableBlend();
//         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
//         GlStateManager.blendFunc(770, 771);
////         this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 + i * 20, this.width / 2, this.height);
////         this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
         this.drawGradientRect(this.xPosition, this.yPosition, this.width+this.xPosition, this.height+this.yPosition, 0x90000000, 0x90000000);
//         
//         GlStateManager.enableBlend();
//         GlStateManager.disableTexture2D();
//         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
////         GlStateManager.color(f, f1, f2, f3);
//         GL11.glBegin(GL11.GL_TRIANGLE_FAN);
//         
//         GL11.glVertex2i(this.xPosition, this.yPosition);
//	     for (i = 0; i <= 20; i++)   {
//	         GL11.glVertex2d (
//	             (this.xPosition + (radius * Math.cos(i * twicePi / 20))), (this.yPosition + (radius * Math.sin(i * twicePi / 20)))
//	             );
//	     }
//         
//         GL11.glEnd();
//         GlStateManager.enableTexture2D();
//         GlStateManager.disableBlend();
        
         
         
         this.mouseDragged(mc, mouseX, mouseY);
         int j = 0xffffffff;
         if(!this.enabled) {
            j = 0xff909090;
         } else if(this.hovered) {
            j = 0xff9090ff;
         }

         Client.sufr.drawCenteredString(this.displayString, this.xPosition + this.width / 2, this.yPosition  + (this.height - 10) / 2, j);
      }
   }

   protected void mouseDragged(Minecraft mc, int mouseX, int mouseY) {
   }

   public void mouseReleased(int mouseX, int mouseY) {
   }

   public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
      return this.enabled && this.visible && mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
   }

   public boolean isMouseOver() {
      return this.hovered;
   }

   public void drawButtonForegroundLayer(int mouseX, int mouseY) {
   }

   public void playPressSound(SoundHandler soundHandlerIn) {
      soundHandlerIn.playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
   }

   public int getButtonWidth() {
      return this.width;
   }

   public void setWidth(int width) {
      this.width = width;
   }
}
