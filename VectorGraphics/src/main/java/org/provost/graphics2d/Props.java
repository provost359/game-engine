package org.provost.graphics2d;

import java.util.HashMap;
import java.util.Map;

public enum Props {
	Width("width", "320"),
	Height("height", "240"),
	;
	
	private String name = null;
	private String defaultValue = null;

	private Props(String name, String defaultValue) {
		this.name = name;
		this.defaultValue = defaultValue;
	}

	public String getName() {
		return this.name;
	}

	public String getDefaultValue() {
		return this.defaultValue;
	}

	public static Map<String, String> getDefaults() {
		Map<String, String> ret = new HashMap<String, String>();
		for(Props prop : Props.values()) {
			ret.put(prop.getName(), prop.getDefaultValue());
		}
		return ret;
	}
}

