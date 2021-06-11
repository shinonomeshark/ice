package xyz.hackage.rewritten.modules.player;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.Container;
import xyz.hackage.rewritten.events.Event;
import xyz.hackage.rewritten.events.listeners.EventUpdate;
import xyz.hackage.rewritten.modules.Module;

public class ChestSteal extends Module {

	int ticks = 0;
	int picked = 0;
	boolean reenablesprint = false;
	
	public ChestSteal() {
		super("ChestStealer", "speedrun time", Keyboard.KEY_Y, Category.PLAYER);
		// TODO Auto-generated constructor stub
	}

	public void onEnable() {
		ticks = 0;
		picked = 0;
	}
	
	private int nextSlot(Container container) {
		int slotAmount = container.inventorySlots.size() == 90 ? 54 : 27;

		for(int i = 0; i < slotAmount; ++i) {
			if (container.getInventory().get(i) != null) {
				return i;
			}
		}
		
		return -1;
	}
	
	private boolean empty(Container container) {
		int slotAmount = container.inventorySlots.size() == 90 ? 54 : 27;
		
		for(int i = 0; i<slotAmount; i++) {
			if(container.getSlot(i).getHasStack()) {
				return true;
			}
		}
		return false;
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate &&  e.isPre()) {
			if(mc.currentScreen instanceof GuiChest) {
				ticks++;
				
				if(!empty(mc.thePlayer.openContainer)) {
					mc.thePlayer.closeScreen();
					picked = 0;
					return;
				}
				
				int tickDelay = 2;
				if(ticks == tickDelay) {
					ticks = 0;
					int index = nextSlot(mc.thePlayer.openContainer);
					mc.playerController.windowClick(mc.thePlayer.openContainer.windowId, index, 0, 1, mc.thePlayer);
					picked++;
				}
			}
		}
	}
}
