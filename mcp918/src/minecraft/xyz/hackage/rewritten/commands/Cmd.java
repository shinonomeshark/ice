package xyz.hackage.rewritten.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Cmd {
	public String name, desc, syntax;
	public List<String> aliases = new ArrayList<String>();
	
	public Cmd(String name, String desc, String syntax, String... aliases) {
		this.name = name;
		this.desc = desc;
		this.syntax = syntax;
		this.aliases = Arrays.asList(aliases);
	}

	public abstract void onCmd(String[] args, String cmd);
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getSyntax() {
		return syntax;
	}

	public void setSyntax(String syntax) {
		this.syntax = syntax;
	}

	public List<String> getAliases() {
		return aliases;
	}

	public void setAliases(ArrayList<String> aliases) {
		this.aliases = aliases;
	}
}
