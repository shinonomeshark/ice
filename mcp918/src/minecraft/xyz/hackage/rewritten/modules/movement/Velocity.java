package xyz.hackage.rewritten.modules.movement;

import org.lwjgl.input.Keyboard;

import xyz.hackage.rewritten.modules.Module;

public class Velocity extends Module {

	public Velocity() {
		super("Velocity", "modifies kb", Keyboard.KEY_NONE, Category.MOVEMENT);
	}

}
