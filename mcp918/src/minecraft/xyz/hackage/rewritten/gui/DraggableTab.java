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
import xyz.hackage.rewritten.util.Notification;
import xyz.hackage.rewritten.util.NotificationManager;
import xyz.hackage.rewritten.util.RainbowUtil;

public class DraggableTab {
	
	Category cat;
	String t = "error 69420";
	int x = 0, y = 0;
	boolean open = true;
	boolean dragging = false;
	int dragX = 0;
	int dragY = 0;
	String openMod = "";
	
	int color1 = 0xff80ff80;
	int color2 = 0xcc000000;
	int txtColor = -1;
	
	
	
	public DraggableTab(Category category, String title, int xPos, int yPos) {
		cat = category;
		t = title;
		x = xPos;
		y = yPos;
	}
	
	public void mouseReleased(int mouseX, int mouseY, int state) {
//		System.out.println(state);
		if(state == 0) {
			if(mouseX > x && mouseX < x+70) {
				if(mouseY > y && mouseY < y+10) {
					dragging = false;
					dragX = 0;
					dragY = 0;
				}
			}
		}
	}
	
	public void mouseClick(int mouseX, int mouseY, int mouseButton) {
		int offset = 1;
		
		System.out.println(mouseButton);
		
		if(mouseButton == 1) {
			if(mouseX > x && mouseX < x+70) {
				if(mouseY > y && mouseY < y+10) {
					open = !open;
				}
			}
		} else if(mouseButton == 0) {
			if(mouseX > x && mouseX < x+70) {
				if(mouseY > y && mouseY < y+10) {
					dragging = true;
					dragX = x-mouseX;
					dragY = y-mouseY;
				}
			}
		}
		
		for(Module m : Client.mods) {
			if(m.cat == cat) {
//				Gui.drawRect(x, y+(20*offset), x+100, y+(20*offset)+20, color2);
//				Client.sufr.drawString(m.name, x+4, y+(20*offset)+3, m.toggled ? color1 : txtColor);
				
				if(mouseX > x && mouseX < x+70) {
					if(mouseY > y+(10*offset) && mouseY < y+(10*offset)+10) {
						if(mouseButton == 0) {
							m.toggle();
						} else if(mouseButton == 1) {
							if(!(m.settings.size() == 0)) {
								if(m.name == openMod) {
									openMod = "";
								} else { 
									openMod = m.name;
								}
							} else {
//								NotificationManager.AssignNotification(new Notification("mod not configurable", 2000l, 0xffff8080));
								Client.addChat("module not configurable");
							}
						}
					}
				}
				
				if(m.name == openMod) {
//					Gui.drawRect(x+100, sY+(20*settOff), x+200, sY+(20*settOff)+20, color2);
					int sY = y+(10*offset);
					int settOff = 0;
					for(Setting s : m.settings) {
						if(mouseX > x+70 && mouseX < x+140) {
							if(mouseY > sY+(10*settOff) && mouseY < sY+(20*settOff)+10) {
								if(s instanceof BooleanSetting) {
									((BooleanSetting) s).toggle();
								}
								if(s instanceof ModeSetting) {
									((ModeSetting) s).cycleFwd();
								}
							}
						}
						
						settOff++;
					}
				}
				
				offset++;
			}
		}
	}
	
	
	
	public void draw(int mouseX, int mouseY) {
		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
		String mode = ((ModeSetting) Client.getMod(new ClickGuiModule().name).settings.get(0)).getMode();
		
		int TitleColor = 0xff000000;
		int txtColorOn = -1;
		int backingColor = 0x80000000;
		
		if(mode == "ranee") {
			color1 = 0xff000000;
			color2 = 0xcc000000;
			txtColor = 0xff009e00;
			txtColorOn = 0xff000000;
			TitleColor = -1;
			backingColor = -1;
		} else if(mode == "sync") {
			color1 = HUD.c1;
			color2 = 0xcc000000;
			txtColor = -1;
			if(((ModeSetting) Client.getMod(new HudModule().name).settings.get(0)).getMode() == "astolfo") {
//				txtColorOn = RainbowUtil.SkyRainbow(0.1f, 1f, 0.6f);
			} else {
				txtColorOn = HUD.c1;
			}
			TitleColor = 0xff000000;
			backingColor = 0xcc000000;
		}
		
		if(dragging) {
			x = mouseX+dragX;
			y = mouseY+dragY;
		}
		
//		System.out.println(openMod);
		
		Gui.drawRect(x, y, x+70, y+10, color1);
		Client.sufr.drawString(t, x+4, y, TitleColor);
		
		String descToDraw = ""; // this is here so it overlaps everything
		
		int offset = 1;
		if(open) {
			for(Module m : Client.mods) {
				if(m.cat == cat) {
					Gui.drawRect(x, y+(10*offset), x+70, y+(10*offset)+10, backingColor);
					if(mode == "sync" && ((ModeSetting) Client.getMod(new HudModule().name).settings.get(0)).getMode() == "astolfo") {
						txtColorOn = RainbowUtil.SkyRainbow(offset, 1f, 0.6f);
					}
					Client.sufr.drawString(m.name, x+4, y+(10*offset), m.toggled ? txtColorOn : txtColor);
					
					int sY = y+(10*offset);
					
					
					
					if(m.name == openMod) {
						Client.sufr.drawString("<", x+60, y+(10*offset), txtColorOn);
						
						int settOff = 0;
						for(Setting s : m.settings) {
							Gui.drawRect(x+70, sY+(10*settOff), x+140, sY+(10*settOff)+10, backingColor);
							if(s instanceof BooleanSetting) {
								Client.sufr.drawString(s.getName(), x+70+4, sY+(10*settOff), ((BooleanSetting)s).getValue() ? 0xff80ff80 : 0xffff8080);
							}
							if(s instanceof ModeSetting) {
								Client.sufr.drawString(s.getName() + ": " +((ModeSetting) s).getMode(), x+70+4, sY+(10*settOff), txtColorOn);
							}
							
							settOff++;
						}
						Gui.drawRect(x+70, sY, x+71, sY+(10*settOff), txtColorOn);
					} else {
						if(m.settings.size() != 0) {
							Client.sufr.drawString(">", x+60, y+(10*offset), txtColorOn);
						}
					}
					
					if(mouseX > x && mouseX < x+100) {
						if(mouseY > y+(10*offset) && mouseY < y+(10*offset)+20) {
							descToDraw = m.description;
						}
					}
					
					offset++;
				}
			}
			if(descToDraw != "") {
				GlStateManager.translate(0f, 0f, 0f);
				Client.sufr.drawCenteredStringWithShadow(descToDraw, sr.getScaledWidth()/2, 2, color1);
				
			}
		}
	}
}
