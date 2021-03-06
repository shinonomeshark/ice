package xyz.hackage.rewritten.modules.render;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import xyz.hackage.rewritten.events.Event;
import xyz.hackage.rewritten.events.listeners.EventUpdate;
import xyz.hackage.rewritten.gui.ClickGui;
import xyz.hackage.rewritten.modules.Module;
import xyz.hackage.rewritten.modules.settings.ModeSetting;
import xyz.hackage.rewritten.util.Notification;
import xyz.hackage.rewritten.util.NotificationManager;

public class ClickGuiModule extends Module {

	public ModeSetting theme = new ModeSetting("color", "sync", "ranee", "sync", "skeet");
	
	public ClickGuiModule() {
		super("ClickGui", "hi this is where u are right now", Keyboard.KEY_RSHIFT, Category.RENDER);
		this.settings.add(theme);
	}
	
	public void onEnable() {
		mc.displayGuiScreen(new ClickGui());
		this.toggle();	
		NotificationManager.AssignNotification(new Notification("loaded clickgui", 2000l, 0xff80ff80));
	}
	
	public net.minecraft.client.settings.KeyBinding[] keyBindsToDoStuffWith = new net.minecraft.client.settings.KeyBinding[] {
            (KeyBinding) Minecraft.getMinecraft().gameSettings.keyBindForward,
            (KeyBinding) Minecraft.getMinecraft().gameSettings.keyBindBack,
            (KeyBinding) Minecraft.getMinecraft().gameSettings.keyBindRight,
            (KeyBinding) Minecraft.getMinecraft().gameSettings.keyBindLeft,
            (KeyBinding) Minecraft.getMinecraft().gameSettings.keyBindJump,
            (KeyBinding) Minecraft.getMinecraft().gameSettings.keyBindSprint
    };
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			for(net.minecraft.client.settings.KeyBinding k : keyBindsToDoStuffWith) {
				k.pressed = GameSettings.isKeyDown(k);
			}
		}
	}
	
}
