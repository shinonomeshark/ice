package xyz.hackage.rewritten.modules;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import xyz.hackage.rewritten.events.Event;
import xyz.hackage.rewritten.modules.settings.Setting;

public class Module {

	public String name, description;
	public boolean toggled, hudHidden = false;
	public int keyCode;
	public Category cat;
	public Minecraft mc = Minecraft.getMinecraft();
	public ArrayList<Setting> settings = new ArrayList<Setting>();
	
	
	public Module(String ModName, String ModDescription, int ModKeybind, Category ModCategory) {
		this.name = ModName;
		this.description = ModDescription;
		this.keyCode = ModKeybind;
		this.cat = ModCategory;
	}
	
	public void onEvent(Event e) {
		
	}
	
	public boolean toggled() {
		return toggled;
	}
	
	public int getKey() {
		return keyCode;
	}
	
	public void toggle() {
		toggled = !toggled;
		if(toggled) {
			onEnable();
		} else {
			onDisable();
		}
	}
	
	public void onEnable()	 { }
	public void onDisable()	 { }
	
	public enum Category {
		COMBAT,
		MOVEMENT,
		PLAYER,
		RENDER;
	}
}