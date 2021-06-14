package xyz.hackage.rewritten.modules.render;

import org.lwjgl.input.Keyboard;

import xyz.hackage.rewritten.modules.Module;
import xyz.hackage.rewritten.modules.settings.ModeSetting;

public class HudModule extends Module {
	
	public static ModeSetting colorScheme = new ModeSetting("color", "chroma", "chroma", "pine", "astolfo");
	
	public HudModule() {
		super("HUD", "shows arraylist and branding and stuff", Keyboard.KEY_NONE, Category.RENDER);
		hudHidden = true;
		toggled = true;
		
		this.settings.add(colorScheme);
	}

}
