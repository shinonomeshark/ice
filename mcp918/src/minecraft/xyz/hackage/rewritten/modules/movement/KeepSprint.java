package xyz.hackage.rewritten.modules.movement;

import org.lwjgl.input.Keyboard;

import xyz.hackage.rewritten.modules.Module;

public class KeepSprint extends Module {

	public KeepSprint() {
		super("KeepSprint", "stops motion modification when attacking enemies", Keyboard.KEY_L, Category.MOVEMENT);
		this.toggled = true;
	}

}
