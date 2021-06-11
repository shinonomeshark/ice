package xyz.hackage.rewritten.modules.movement;

import org.lwjgl.input.Keyboard;

import xyz.hackage.rewritten.modules.Module;

public class NoRotateSet extends Module {

	public NoRotateSet() {
		super("NoRotate", "stops incoming packets setting rotation", Keyboard.KEY_NONE, Category.MOVEMENT);
	}

}
