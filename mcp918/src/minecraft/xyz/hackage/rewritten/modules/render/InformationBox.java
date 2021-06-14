package xyz.hackage.rewritten.modules.render;

import org.lwjgl.input.Keyboard;

import xyz.hackage.rewritten.modules.Module;

public class InformationBox extends Module {

	public InformationBox() {
		super("Info", "displays various client information", Keyboard.KEY_NONE, Category.RENDER);
	}
	
}
