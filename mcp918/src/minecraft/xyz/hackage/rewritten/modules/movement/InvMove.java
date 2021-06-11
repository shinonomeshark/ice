package xyz.hackage.rewritten.modules.movement;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import xyz.hackage.rewritten.events.Event;
import xyz.hackage.rewritten.events.listeners.EventUpdate;
import xyz.hackage.rewritten.modules.Module;

public class InvMove extends Module {

	public net.minecraft.client.settings.KeyBinding[] keyBindsToDoStuffWith = new net.minecraft.client.settings.KeyBinding[] {
            (KeyBinding) Minecraft.getMinecraft().gameSettings.keyBindForward,
            (KeyBinding) Minecraft.getMinecraft().gameSettings.keyBindBack,
            (KeyBinding) Minecraft.getMinecraft().gameSettings.keyBindRight,
            (KeyBinding) Minecraft.getMinecraft().gameSettings.keyBindLeft,
            (KeyBinding) Minecraft.getMinecraft().gameSettings.keyBindJump,
            (KeyBinding) Minecraft.getMinecraft().gameSettings.keyBindSprint
    };
	
	public InvMove() {
		super("InvMove", "allows you to move in inventories", Keyboard.KEY_NONE, Category.MOVEMENT);
	}
	
	public void onEnable() {
		
	}
	
	public void onDisable() {
		
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			for(net.minecraft.client.settings.KeyBinding k : keyBindsToDoStuffWith) {
				if(!(mc.currentScreen instanceof GuiChat)) {
					k.pressed = GameSettings.isKeyDown(k);
				}
			}
		}
	}

}
