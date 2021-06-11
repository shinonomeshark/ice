package xyz.hackage.rewritten.commands.actual_commands;

import xyz.hackage.rewritten.Client;
import xyz.hackage.rewritten.commands.Cmd;
import xyz.hackage.rewritten.modules.Module;
import xyz.hackage.rewritten.util.Notification;
import xyz.hackage.rewritten.util.NotificationManager;

public class Toggle extends Cmd{

	public Toggle() {
		super("toggle", "toggles a module on or off", "toggle <module name>", "t");
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
				
				System.out.println(moduleName + ",");
				System.out.println(actualModName + ",");
				
				actualModName = actualModName.toLowerCase();
				
				if(actualModName.startsWith(moduleName.toLowerCase())) {
					m.toggle();
					NotificationManager.AssignNotification(new Notification(m.toggled ? "enabled " + m.name : "disabled " + m.name, 2000l, m.toggled ? 0xff80ff80 : 0xffff8080));
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
