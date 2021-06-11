package xyz.hackage.rewritten.commands;

import java.util.ArrayList;
import java.util.Arrays;

import xyz.hackage.rewritten.commands.actual_commands.Bind;
import xyz.hackage.rewritten.commands.actual_commands.Toggle;
import xyz.hackage.rewritten.events.listeners.EventChatSent;

public class CmdMgr {
	public ArrayList<Cmd> cmds = new ArrayList<Cmd>();
	public String px = ".";
	
	public CmdMgr() {
		setup();
	}
	
	public void setup() {
		cmds.add(new Toggle());
		cmds.add(new Bind());
	}
	
	public void handleCmd(EventChatSent event) {
		String msg = event.getMessage();
		
		if(!msg.startsWith(px))
			return;
		
		event.setCancelled(true);
		
		msg = msg.substring(px.length());
		
		if(msg.split(" ").length > 0) {
			String cmdName = msg.split(" ")[0];
			
			for(Cmd c : cmds) {
				if(c.aliases.contains(cmdName)) {
					c.onCmd(Arrays.copyOfRange(msg.split(" "), 1, msg.split(" ").length), msg);
				}
				
			}
		}
	}
}
