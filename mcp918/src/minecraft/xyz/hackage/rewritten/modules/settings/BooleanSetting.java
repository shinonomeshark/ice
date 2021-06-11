package xyz.hackage.rewritten.modules.settings;

public class BooleanSetting extends Setting {

	boolean value;
	
	public BooleanSetting(String settingName, boolean defaultValue) {
		name = settingName;
		value = defaultValue;
	}
	
	public void toggle() {
		value = !value;
	}
	
	public boolean getValue() {
		return value;
	}
	
	public void setValue(boolean val) {
		value = val;
	}
	
}
