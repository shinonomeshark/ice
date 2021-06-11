package xyz.hackage.rewritten;

import java.util.concurrent.CopyOnWriteArrayList;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import xyz.hackage.rewritten.commands.CmdMgr;
import xyz.hackage.rewritten.events.EventManager;
import xyz.hackage.rewritten.events.listeners.EventTeleport;
import xyz.hackage.rewritten.modules.Module;
import xyz.hackage.rewritten.modules.combat.HitboxAura;
import xyz.hackage.rewritten.modules.combat.RotationAura;
import xyz.hackage.rewritten.modules.movement.Longjump;
import xyz.hackage.rewritten.modules.movement.NoRotateSet;
import xyz.hackage.rewritten.modules.movement.PearlFly;
import xyz.hackage.rewritten.modules.movement.Scaffold;
import xyz.hackage.rewritten.modules.movement.Speed;
import xyz.hackage.rewritten.modules.movement.Fly;
import xyz.hackage.rewritten.modules.movement.InvMove;
import xyz.hackage.rewritten.modules.movement.KeepSprint;
import xyz.hackage.rewritten.modules.movement.Sprint;
import xyz.hackage.rewritten.modules.movement.Velocity;
import xyz.hackage.rewritten.modules.player.ChestSteal;
import xyz.hackage.rewritten.modules.player.Experimental;
import xyz.hackage.rewritten.modules.player.NoFall;
import xyz.hackage.rewritten.modules.player.Phase;
import xyz.hackage.rewritten.modules.render.ClickGuiModule;
import xyz.hackage.rewritten.modules.render.Esp;
import xyz.hackage.rewritten.modules.render.HudModule;
import xyz.hackage.rewritten.modules.render.NoHurtCam;
import xyz.hackage.rewritten.util.Notification;
import xyz.hackage.rewritten.util.NotificationManager;
import xyz.hackage.rewritten.util.UnicodeFontRenderer;

public class Client {
	private static Minecraft mc = Minecraft.getMinecraft();
	public static String VERSION = "v4";
	public static UnicodeFontRenderer bufr;
	public static UnicodeFontRenderer bpufr;
	public static UnicodeFontRenderer ufr;
	public static UnicodeFontRenderer sufr;
	public static UnicodeFontRenderer sbufr;
	
	public static HUD hud = new HUD();
	
	public static CmdMgr cmdmgr = new CmdMgr();
	
	public static CopyOnWriteArrayList<Module> mods = new CopyOnWriteArrayList<Module>();
	public static boolean moddedFlying = false;
	public static boolean blink = false;
	
	
	public static void lagDetect() {
//		NotificationManager.AssignNotification(new Notification("tp / lagback detected", 2000l, 0xffff8080));
		EventManager.callEvent(new EventTeleport());
	}
	
	public static void init() {
		System.out.println("hi welcome to hackage rewritten this was painful to make");
		System.out.println("it was fun tho ngl");
		System.out.println("basically i was bored one weekend and wanted to mod menu on blocksmc and they should pour a gallon of water on the server");
		
		bufr = new UnicodeFontRenderer("SF-Pro-Display-Black", 0, 48, 0f, 1f);
		bpufr = new UnicodeFontRenderer("Pacifico-Regular", 0, 128, 0f, 1f);
		ufr = new UnicodeFontRenderer("SF-Pro-Display-Regular", 0, 10, 0f, 1f);
		sufr = new UnicodeFontRenderer("SF-Pro-Display-Regular", 0, 8, 0f, 1f);
		sbufr = new UnicodeFontRenderer("Pacifico-Regular", 0, 32, 0f, 1f);
		
		
		
		mc.gameSettings.showInventoryAchievementHint = false;
        /*
		 ^
         This removes the annoying 'Press E to open your inventory'.
         The reason for this is because some servers leave it on and
         it messes up the ArrayList in the HUD.
        */
		
		mods.add(new Longjump());
		mods.add(new HitboxAura());
		mods.add(new Sprint());
		mods.add(new KeepSprint());
		mods.add(new Esp());
		mods.add(new ClickGuiModule());
		mods.add(new ChestSteal());
		mods.add(new Scaffold());
		mods.add(new PearlFly());
		mods.add(new NoHurtCam());
		mods.add(new InvMove());
		mods.add(new NoRotateSet());
		mods.add(new HudModule());
		mods.add(new NoFall());
		mods.add(new Fly());
		mods.add(new Velocity());
		mods.add(new RotationAura());
		mods.add(new Experimental());
		mods.add(new Phase());
		mods.add(new Speed());
	}
	
	public static void addChat(String message) {
		mc.thePlayer.addChatMessage(new ChatComponentText(ChatFormatting.GREEN + "pine // " + ChatFormatting.RESET + message));
	}
	
	public static boolean modOn(String modName) {
		for(Module m : mods) {
			if(m.name == modName && m.toggled) {
				return true;
			}
		}
		return false;
	}
	
	public static Module getMod(String modName) {
		for(Module m : mods) {
			if(m.name == modName) {
				return m;
			}
		}
		return null;
	}
	
	
	
	public static void keyPress(int key) {
		for(Module m : mods) {
			if(m.getKey() == key) {
				m.toggle();
				if(m.toggled) {
					NotificationManager.AssignNotification(new Notification("enabled " + m.name, 1000l, 0xff80ff80));
				} else {
					NotificationManager.AssignNotification(new Notification("disabled " + m.name, 1000l, 0xffff8080));
				}
				
			}
		}
	}
}
