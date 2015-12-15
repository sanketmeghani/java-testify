package dev.sanket.util;

import static org.apache.commons.lang.StringUtils.isBlank;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

public class ConfigJSON {

	private JSONObject config;

	private ConfigJSON() {
	}
	
	public static ConfigJSON from(JSONObject config) {
		ConfigJSON configJson = new ConfigJSON();
		
		if (config == null) {
			config = new JSONObject();
		}
		
		configJson.setConfig(config);
		
		return configJson;
	}

	public static ConfigJSON from(String config) {
		ConfigJSON configJson = new ConfigJSON();
		
		JSONObject json = null;
		if (!isBlank(config) && !config.equalsIgnoreCase("null") && JSONUtils.mayBeJSON(config.trim())) {
			json = JSONObject.fromObject(config);
		} else {
			json = new JSONObject();
		}
		
		configJson.setConfig(json);
		
		return configJson;
	}

    public String getString(String key, String defaultValue) {
    	Object value = getNestedValue(key, getConfig());
    	return ((value == null) ? defaultValue : String.valueOf(value));
    }

    public String getString(String key) {
    	return getString(key, "");
    }

    public int getInteger(String key, int defaultValue) {
    	Object value = getNestedValue(key, getConfig());
    	return ((value == null) ? defaultValue : Integer.valueOf(value.toString()));
    }

    public int getInteger(String key) {
    	return getInteger(key, 0);
    }

    public double getDouble(String key, double defaultValue) {
    	Object value = getNestedValue(key, getConfig());
    	return ((value == null) ? defaultValue : Double.valueOf(value.toString()));
    }

    public double getDouble(String key) {
    	return getDouble(key, 0.0);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
    	Object value = getNestedValue(key, getConfig());
    	return ((value == null) ? defaultValue : Boolean.valueOf(value.toString()));
    }

    public boolean getBoolean(String key) {
    	return getBoolean(key, Boolean.FALSE);
    }

    public JSONObject getJson(String key) {
    	Object value = getNestedValue(key, getConfig());
    	return ((value == null) ? (new JSONObject()) : JSONObject.fromObject(value));
    }

    public JSONArray getJsonArray(String key) {
    	Object value = getNestedValue(key, getConfig());
    	return ((value == null) ? (new JSONArray()) : JSONArray.fromObject(value));
    }
    
    @SuppressWarnings("unchecked")
	public List<String> getStringList(String key) {
        return (List<String>) JSONArray.toCollection(getJsonArray(key));
    }

    public boolean contains(String key) {
    	return containsNestedKey(key, getConfig());
    }
    
    public void put(JSONObject json) {
    	for (Object key : json.keySet()) {
    		getConfig().put(key.toString(), json.get(key));
    	}
    }

    public void put(String key, JSONObject json) {
		getConfig().put(key, json);
    }

    public void put(String key, String value) {
		getConfig().put(key, value);
    }

	private Object getNestedValue(String nestedKey, JSONObject nestedJson) {
		if (!nestedKey.contains(".")) {
			return nestedJson.opt(nestedKey);
		}

		String[] nestedKeys = nestedKey.split("\\.", 2);
        
		JSONObject nestedValue = nestedJson.optJSONObject(nestedKeys[0]);
		if (nestedValue != null) {
			return getNestedValue(nestedKeys[1], nestedValue);
		} else {
			return null;
		}
	}

	private boolean containsNestedKey(String nestedKey, JSONObject nestedJson) {
		if (!nestedKey.contains(".")) {
			return nestedJson.containsKey(nestedKey);
		}

		String[] nestedKeys = nestedKey.split("\\.", 2);
        
		JSONObject propertyValue = nestedJson.optJSONObject(nestedKeys[0]);
		if (propertyValue != null) {
			return containsNestedKey(nestedKeys[1], propertyValue);
		} else {
			return Boolean.FALSE;
		}
	}

	public JSONObject getConfig() {
		return config;
	}

	public void setConfig(JSONObject config) {
		this.config = config;
	}

}