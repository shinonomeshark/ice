package xyz.hackage.rewritten.modules.player;

import org.lwjgl.input.Keyboard;

import xyz.hackage.rewritten.modules.Module;

public class AutoLongJump extends Module {

	public AutoLongJump() {
		super("AutoLongJump", "automatically longjumps after being hit by an arrow", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	

}
