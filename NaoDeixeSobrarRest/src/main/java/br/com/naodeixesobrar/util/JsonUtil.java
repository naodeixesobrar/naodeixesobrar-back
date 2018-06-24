package br.com.naodeixesobrar.util;

import org.json.JSONObject;

public class JsonUtil {

	public static String getJsonMessageReturn(String message) {
    	JSONObject jsonObj = new JSONObject();
		jsonObj.put("mensagem", message);
		return jsonObj.toString();
	}
	
}
