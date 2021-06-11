package xyz.hackage.rewritten.modules.settings;

public class ModeSetting extends Setting {

	String mode;
	String[] values;
	int index = 0;
	
	public ModeSetting(String settingName, String defaultValue, String... possibleValues) {
		name = settingName;
		mode = defaultValue;
		values = possibleValues;
		for (int i=0;i<values.length;i++) {
		    if (values[i].equals(mode)) {
		        index = i;
		        break;
		    }
		}
	}
	
	public void cycleFwd() {
		if((values.length-1) == index)
			index = 0;
		else {
			index++;
		}
	}
	
	public String getMode() {
		return values[index];
	}
	
}
