package xyz.hackage.rewritten;

import java.awt.Color;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import xyz.hackage.rewritten.modules.Module;
import xyz.hackage.rewritten.modules.render.HudModule;
import xyz.hackage.rewritten.modules.render.InformationBox;
import xyz.hackage.rewritten.modules.settings.ModeSetting;
import xyz.hackage.rewritten.util.AnimationUtil;
import xyz.hackage.rewritten.util.Notification;
import xyz.hackage.rewritten.util.NotificationManager;
import xyz.hackage.rewritten.util.RainbowUtil;
import xyz.hackage.rewritten.util.SystemTimerUtil;
import xyz.hackage.rewritten.util.UnicodeFontRenderer;

public class HUD extends Gui {
	
	private SystemTimerUtil tim = new SystemTimerUtil(); // hello tim how are you // i am good thanks
	public static long animTime = 0; // im retarded
	public static boolean inorout = false; // you see the thing is here is it doesnt actually tell us wether its going in or out so you have to be 100% stupid to understand how it works
	// or you can just thing that in = false vice versa your welcome
	// saved ur brain
	public static int c1 = -1;
	public static int c2 = -1;
	
	public static Entity attacking = null;
	public float oldTing = 0;
	private SystemTimerUtil timWorksForTargetHudInc = new SystemTimerUtil(); // tim 4 cant fit thru the door
	// he is used in spaghetti (see targethud code)
	public static long targetHudTime = 0;
	
	public static class ModuleComparator implements Comparator<Module> {

		@Override
		public int compare(Module o1, Module o2) {
			if(Client.sufr.getWidth(o1.name) > Client.sufr.getWidth(o2.name)) {
				return -1;
			}
			if(Client.sufr.getWidth(o1.name) < Client.sufr.getWidth(o2.name)) {
				return 1;
			}

			return 0;
		}
		
	}
	
	public static void triggerNotifUpdate() {
		animTime = 0;
		inorout = false;
		// probably a better way of doing this function but im still a brainlet so i do like this
	}
	
	public void draw() {
		
		float colorThingIdek = (System.currentTimeMillis() % 40000L) / 2000.0F;
		
		String mode = ((ModeSetting) Client.getMod(new HudModule().name).settings.get(0)).getMode();
		
//		if(mode == "chroma") {
			c1 = Color.HSBtoRGB(colorThingIdek, 0.6F, 0.8F);
			c2 = Color.HSBtoRGB(colorThingIdek, 0.6F, 0.3F);
//		} else if (mode == "pine") {
//			c1 = Color.HSBtoRGB(0.333333f, 1f, 1f);
//			System.out.println(Color.RGBtoHSB(0, 255, 0, null)[1]);
//			c2 = Color.HSBtoRGB(colorThingIdek, 0.6F, 0.3F);
//		}
			
			if(((ModeSetting) Client.getMod(new HudModule().name).settings.get(0)).getMode() == "astolfo") {
				c1 = RainbowUtil.SkyRainbow(1, 1f, 0.6f);
				c2 = RainbowUtil.SkyRainbow(1, 0.4f, 0.6f);
			}
		
		if(!Client.modOn(new HudModule().name)) {
			return;
		}
		
		
		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
		UnicodeFontRenderer sfr = Client.sufr;
		UnicodeFontRenderer fr = Client.ufr;
		UnicodeFontRenderer bfr = Client.bufr;
		
		
		
		
		
		Client.ufr.drawStringWithShadow("S" + ChatFormatting.WHITE + "entinel " + Client.VERSION, 5, 0, c1);
		float rat = (Minecraft.getMinecraft().timer.timerSpeed*20) * Minecraft.getMinecraft().timer.timerSpeed;
        float bps = (float) (Minecraft.getMinecraft().thePlayer.getDistance(Minecraft.getMinecraft().thePlayer.lastTickPosX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.lastTickPosZ) * rat);
//        Client.sufr.drawStringWithShadow(bps + "" + ChatFormatting.WHITE + "b/s", 5, 10, c1);
//        Client.sufr.drawStringWithShadow(Minecraft.getMinecraft().getDebugFPS() + "" + ChatFormatting.WHITE + "fps", 5, 18, c1);
        
		
		Collections.sort(Client.mods, new ModuleComparator());
		
		int count = 0;
		int lastX = -1;
		
		int amountOn = -1;
		
		for(Module m : Client.mods) {
			if(m.toggled) {
				if(!m.hudHidden)
					amountOn++;
			}
		}
		
		for(Module m : Client.mods) {
			if(m.toggled && !m.hudHidden) {
				int c = Color.HSBtoRGB((float)(System.currentTimeMillis() % 20000L+(count*100)) / 1000.0F+(count*50), 0.4F, 0.8F);
				int c11 = Color.HSBtoRGB((float)-(System.currentTimeMillis() % 20000L+(count*100)) / 1000.0F+(count*50), 0.4F, 0.3F);
				
				if(((ModeSetting) Client.getMod(new HudModule().name).settings.get(0)).getMode() == "astolfo") {
					c = RainbowUtil.SkyRainbow(count, 1f, 0.6f);
				}
				
				String ting = m.name + " ";
				int length = (int) sfr.getWidth(ting);
				
				
				Gui.drawRect(sr.getScaledWidth()-length-2, (4+sfr.FONT_HEIGHT)*count, sr.getScaledWidth(), (4+sfr.FONT_HEIGHT)*(count+1), 0xaa000000);
				Gui.drawRect(sr.getScaledWidth()-length-3, (4+sfr.FONT_HEIGHT)*count, sr.getScaledWidth()-length-2, (4+sfr.FONT_HEIGHT)*(count+1), c);
//				Gui.drawRect(, (4+sfr.FONT_HEIGHT)*(count+1)+1, sr.getScaledWidth(), (4+sfr.FONT_HEIGHT)*(count+1), c);
				
				sfr.drawString(m.name + " ", sr.getScaledWidth()-sfr.getWidth(ting), (4+sfr.FONT_HEIGHT)*count, c);
				
				if(!(lastX == -1)) {
					Gui.drawRect(sr.getScaledWidth()-length-2, (4+sfr.FONT_HEIGHT)*(count), lastX, (4+sfr.FONT_HEIGHT)*(count)+1, c);
				}
				
//				System.out.println(amountOn + " / " + count);
				
				if(amountOn == count) {
					Gui.drawRect(sr.getScaledWidth()-length-3, (4+sfr.FONT_HEIGHT)*(count+1)+1, sr.getScaledWidth(), (4+sfr.FONT_HEIGHT)*(count+1), c);
				}
				
				lastX = sr.getScaledWidth()-length-3;
				
				count++;
			}
		}
		
//		System.out.println(NotificationManager.currentNotification);
		
		// INFORMATION BOX
		
		if(!(Minecraft.getMinecraft().currentScreen instanceof GuiChat) && !(Minecraft.getMinecraft().currentScreen instanceof GuiChat) && Client.modOn(new InformationBox().name)) {
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			
			Gui.drawRect(2, sr.getScaledHeight()-30-(10*GuiNewChat.illili), 155, sr.getScaledHeight()-80-(10*GuiNewChat.illili), 0x80000000);
//			this.drawGradientRect(3, sr.getScaledHeight()-79-(9*GuiNewChat.illili), 154, sr.getScaledHeight()-78-(10*GuiNewChat.illili), RainbowUtil.getRainbow(0), Color.HSBtoRGB((float)(System.currentTimeMillis() % 20000L+(5*100)) / 1000.0F+(5*50), 0.4F, 0.8F));
//			System.out.println(RainbowUtil.getRainbow(0) + " / " + RainbowUtil.getRainbow(20));
			
			Client.sufr.drawString("Client" + ChatFormatting.WHITE +" Info", 4, sr.getScaledHeight()-78-(10*GuiNewChat.illili), -1);
			if(Minecraft.getMinecraft().isIntegratedServerRunning()) {
				Client.sufr.drawString("Singleplayer World", 4, sr.getScaledHeight()-70-(10*GuiNewChat.illili), -1);
				Client.sufr.drawString("0ms", 4, sr.getScaledHeight()-40-(10*GuiNewChat.illili), -1);
				
			} else {
				Client.sufr.drawString(Minecraft.getMinecraft().getCurrentServerData().serverIP, 4, sr.getScaledHeight()-70-(10*GuiNewChat.illili), -1);
				Client.sufr.drawString(Minecraft.getMinecraft().getCurrentServerData().pingToServer + "ms", 4, sr.getScaledHeight()-40-(10*GuiNewChat.illili), -1);
				
			}
			Client.sufr.drawString("No Config Loaded", 4, sr.getScaledHeight()-62-(10*GuiNewChat.illili), -1);
			Client.sufr.drawString(bps + "b/s", 4, sr.getScaledHeight()-54-(10*GuiNewChat.illili), -1);
			
			
			Client.sufr.drawString("[" + dtf.format(now) + "]", 152-Client.sufr.getWidth("[" + dtf.format(now) + "]"), sr.getScaledHeight()-78-(10*GuiNewChat.illili), -1);
			Client.sufr.drawString(Minecraft.getDebugFPS() + "fps", 152-Client.sufr.getWidth(Minecraft.getDebugFPS() + "fps"), sr.getScaledHeight()-70-(10*GuiNewChat.illili), -1);
			
			Client.sufr.drawString(Minecraft.getMinecraft().thePlayer.getName() + " / ice / 6969", 152-Client.sufr.getWidth(Minecraft.getMinecraft().thePlayer.getName() + " / ice / 6969"), sr.getScaledHeight()-40-(10*GuiNewChat.illili), -1);
			
			for(int i = 0; i < 151;) {
				if(((ModeSetting) Client.getMod(new HudModule().name).settings.get(0)).getMode() == "astolfo") {
					this.drawRect(3+i, sr.getScaledHeight()-79-(10*GuiNewChat.illili), 4+i, sr.getScaledHeight()-78-(10*GuiNewChat.illili), RainbowUtil.SkyRainbow(i/10f, 1f, 0.6f));
				} else {
					this.drawRect(3+i, sr.getScaledHeight()-79-(10*GuiNewChat.illili), 4+i, sr.getScaledHeight()-78-(10*GuiNewChat.illili), RainbowUtil.getRainbow(i/10d));
				}
					i++;
			}
		}
		
		if(NotificationManager.currentNotification != null) {
			
			Notification not = NotificationManager.currentNotification;
			SystemTimerUtil time = not.getTim();
			
			long endStart = not.length - 400;
			double animTing = (AnimationUtil.easeOutExpo(animTime/400d));
			
			GlStateManager.pushMatrix();
			
			if(!inorout) {
				GlStateManager.translate(-120*animTing, 0, 0);
			} else {
				GlStateManager.translate(-120, 55*animTing, 0);
			}
			
			Gui.drawRect((int) (sr.getScaledWidth()), sr.getScaledHeight()-55, (int) (sr.getScaledWidth()+115), sr.getScaledHeight()-5, 0x90000000);
			Gui.drawRect((int) (sr.getScaledWidth()), sr.getScaledHeight()-6, (int) (sr.getScaledWidth()+115), sr.getScaledHeight()-5, not.color);
			
			Gui.drawRect((int) (sr.getScaledWidth()), sr.getScaledHeight()-55, (int) (sr.getScaledWidth()+115), sr.getScaledHeight()-40, -1);
			

			Gui.drawRect((int) (sr.getScaledWidth()), sr.getScaledHeight()-55, (int) (sr.getScaledWidth()+115), sr.getScaledHeight()-54, c1);
			Gui.drawRect((int) (sr.getScaledWidth()), sr.getScaledHeight()-41, (int) (sr.getScaledWidth()+115), sr.getScaledHeight()-40, 0xff000000);
				
			sfr.drawString("Sentinel", (float) (sr.getScaledWidth()+4), sr.getScaledHeight()-54, 0);
			sfr.drawString(not.text, (float) (sr.getScaledWidth()+4), sr.getScaledHeight()-40, -1);
			
			
			
			GlStateManager.popMatrix();
			
			if(time.getTime() < 1000) {
				animTime = (long) time.getTime();
			} else {
				animTime = 1000l;
			}
			
			if(time.getTime() > endStart) {
				inorout = true;
				animTime = (long) time.getTime() - endStart;
			}
			
			if(time.getTime() >= not.length) {
				NotificationManager.currentNotification = null;
			}
		}
		
		
		
		if(attacking != null) {
			EntityLivingBase elb = (EntityLivingBase) attacking;
			 
			Gui.drawRect((sr.getScaledWidth()/2)-75, (sr.getScaledHeight()/2)+20, (sr.getScaledWidth()/2)+75, (sr.getScaledHeight()/2)+70, 0x50000000);
			Gui.drawRect((sr.getScaledWidth()/2)-75, (sr.getScaledHeight()/2)+20, (sr.getScaledWidth()/2)-73, (sr.getScaledHeight()/2)+70, c1);
			Gui.drawRect((sr.getScaledWidth()/2)-70, (sr.getScaledHeight()/2)+55, (sr.getScaledWidth()/2)+70, (sr.getScaledHeight()/2)+65, c2);
			Gui.drawRect((sr.getScaledWidth()/2)-70, (sr.getScaledHeight()/2)+55, (int) (((sr.getScaledWidth()/2))-70+(140*(elb.getHealth() / elb.getMaxHealth()))), (sr.getScaledHeight()/2)+65, c1);
//			for(int i = 0; i < (((sr.getScaledWidth()/2))-70+(140*(elb.getHealth() / elb.getMaxHealth()))-(sr.getScaledWidth()/2-70));) {
//				if(((ModeSetting) Client.getMod(new HudModule().name).settings.get(0)).getMode() == "astolfo") {
//					this.drawRect((int) ((((sr.getScaledWidth()/2))-70)+i), sr.getScaledHeight()-55, (int) ((((sr.getScaledWidth()/2))-70+(140*(elb.getHealth()/2f / elb.getMaxHealth())))), sr.getScaledHeight()-56, RainbowUtil.SkyRainbow(i/10f, 1f, 0.6f));
//				} else {
//					this.drawRect((int) ((((sr.getScaledWidth()/2))-70)+i), sr.getScaledHeight()-55, (int) ((((sr.getScaledWidth()/2))-70+(140*(elb.getHealth()/2f / elb.getMaxHealth())))), sr.getScaledHeight()-56, RainbowUtil.getRainbow(i/10d));
//				}
//					i++;
			
//			this.drawRect(0, 0, 5, 5, RainbowUtil.SkyRainbow(1, 1f, 0.6f));
//			this.drawRect(5, 5, 10, 10, RainbowUtil.SkyRainbow(10, 1f, 0.6f));
//			this.drawGradientRect((sr.getScaledWidth()/2)-70, (sr.getScaledHeight()/2)+55, (int) (((sr.getScaledWidth()/2))-70+(140*(elb.getHealth() / elb.getMaxHealth()))), (sr.getScaledHeight()/2)+65, c1, RainbowUtil.SkyRainbow(10, 1f, 0.6f));
			
//			}
			
			
			
			
			System.out.println(((sr.getScaledWidth()/2)+70)*(elb.getHealth() / elb.getMaxHealth()));
			
//			Client.sufr.drawString(((int)elb.getHealth()) +"", (((sr.getScaledWidth()/2))-70+(140*(elb.getHealth() / elb.getMaxHealth())))-Client.sufr.getWidth(((int)elb.getHealth()) + ""), (sr.getScaledHeight()/2)+54, 0);
			
			Client.sufr.drawStringWithShadow(attacking.getName(), (sr.getScaledWidth()/2)-70, (sr.getScaledHeight()/2)+20, c1);
			Client.sufr.drawStringWithShadow(Math.floor(Minecraft.getMinecraft().thePlayer.getDistanceToEntity(elb)*10)/10 +" blocks", (sr.getScaledWidth()/2)-70, (sr.getScaledHeight()/2)+30, c1);
			Client.sufr.drawStringWithShadow((int)(elb.getHealth()) +" hp", (sr.getScaledWidth()/2)-70, (sr.getScaledHeight()/2)+40, c1);	
		}
		
////		double animTing1 = (AnimationUtil.easeOutExpo(targetHudTime/500d));
//		
//		EntityLivingBase elb = (EntityLivingBase) attacking;
////		GuiInventory.drawEntityOnScreen((sr.getScaledWidth()/2), (sr.getScaledHeight()/2)-50, 25, 25, 25, (EntityLivingBase) attacking);
//		Client.sufr.drawStringWithShadow(attacking.getName(), (sr.getScaledWidth()/2)-(Client.sufr.getWidth(attacking.getName())/2), (sr.getScaledHeight()/2)-50, c1);
//		System.out.println(elb.getHealth()/elb.getMaxHealth());
////		if(oldTing != elb.getHealth()) {
////			Gui.drawRect((int)((sr.getScaledWidth()/2)-((Client.sufr.getWidth(attacking.getName())/2))*((elb.getHealth()/elb.getMaxHealth())*animTing1)), (sr.getScaledHeight()/2)-36, (int)((sr.getScaledWidth()/2)+((Client.sufr.getWidth(attacking.getName())/2))*((elb.getHealth()/elb.getMaxHealth())*animTing1)), (sr.getScaledHeight()/2)-35, c1);
//		Gui.drawRect((int)((sr.getScaledWidth()/2)-((Client.sufr.getWidth(attacking.getName())/2))*((elb.getHealth()/elb.getMaxHealth()))), (sr.getScaledHeight()/2)-36, (int)((sr.getScaledWidth()/2)+((Client.sufr.getWidth(attacking.getName())/2))*((elb.getHealth()/elb.getMaxHealth()))), (sr.getScaledHeight()/2)-35, c1);
////		} else {
////			timWorksForTargetHudInc.resetTime();
////			Gui.drawRect((int)((sr.getScaledWidth()/2)-((Client.sufr.getWidth(attacking.getName())/2))*((elb.getHealth()/elb.getMaxHealth()))), (sr.getScaledHeight()/2)-36, (int)((sr.getScaledWidth()/2)+((Client.sufr.getWidth(attacking.getName())/2))*((elb.getHealth()/elb.getMaxHealth()))), (sr.getScaledHeight()/2)-35, c1);
////		}
////		
////		if(timWorksForTargetHudInc.getTime() < 500) {
////			targetHudTime = (long) timWorksForTargetHudInc.getTime();
////		} else {
////			targetHudTime = 500l;
////			oldTing = elb.getHealth();
////		}
	}
}
