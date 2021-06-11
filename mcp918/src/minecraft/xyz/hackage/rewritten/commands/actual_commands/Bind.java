package xyz.hackage.rewritten.commands.actual_commands;

import net.minecraft.client.Minecraft;
import xyz.hackage.rewritten.Client;
import xyz.hackage.rewritten.commands.Cmd;
import xyz.hackage.rewritten.gui.GuiBind;
import xyz.hackage.rewritten.modules.Module;
import xyz.hackage.rewritten.util.Notification;
import xyz.hackage.rewritten.util.NotificationManager;

public class Bind extends Cmd{

	public Bind() {
		super("bind", "binds", "bind <module name>", "bind");
	}

	@Override
	public void onCmd(String[] args, String cmd) {
		if(args.length > 0) {
			String moduleName = args[0];
			String actualModName = "";
			boolean modExists = false;
			
			
			
			for(Module m : Client.mods) {
				actualModName = m.name;
				
				
				actualModName.replace(" ", "");
				
//				System.out.println(moduleName + ",");
//				System.out.println(actualModName + ",");
				actualModName = actualModName.toLowerCase();
				
				if(actualModName.startsWith(moduleName.toLowerCase())) {
//					m.toggle();
//					NotificationManager.AssignNotification(new Notification(m.toggled ? "enabled " + m.name : "disabled " + m.name, 2000l, m.toggled ? 0xff80ff80 : 0xffff8080));
//					Client.addChat("kaboom");
					Minecraft.getMinecraft().displayGuiScreen(null);
					Thread thread = new Thread() {
						public void run() {
							try {
								Thread.sleep(100);
								Minecraft.getMinecraft().displayGuiScreen(new GuiBind(m));
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					};
					thread.start();
//					Client.addChat("rico");
					
					modExists = true;
					break;
				}
			}
			
			
			
			if(!modExists) {
				NotificationManager.AssignNotification(new Notification("mod not found", 2000l, 0xffff8080));
			}
		}
	}

}
