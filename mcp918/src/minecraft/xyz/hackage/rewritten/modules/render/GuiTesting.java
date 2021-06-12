package xyz.hackage.rewritten.modules.render;

import org.lwjgl.input.Keyboard;

import xyz.hackage.rewritten.events.Event;
import xyz.hackage.rewritten.gui.TestGui;
import xyz.hackage.rewritten.modules.Module;

public class GuiTesting extends Module {

	public GuiTesting() {
		super("GuiTest", "testing of graphics and stuff", Keyboard.KEY_NONE, Category.RENDER);
	}
	
	public void onEnable() {
		mc.displayGuiScreen(new TestGui());
		this.toggle();
	}
	
	public void onDisable() {}
	
	public void onEvent(Event e) {}
	

}
