package xyz.hackage.rewritten.modules.render;

import org.lwjgl.input.Keyboard;

import xyz.hackage.rewritten.modules.Module;

public class NoHurtCam extends Module {

	public NoHurtCam() {
		super("NoHurtCam", "dont shake screen when u get hit pog", Keyboard.KEY_NONE, Category.RENDER);
	}

}
