package lpdata.util;

import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

public class MyUtil {
	public static JSONObject getJson(String message, int status, Object data) {
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put("data", data);
			jsonObj.put("status", status);
			jsonObj.put("msg", message);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObj;
	}

	public static String getRandomString() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 24);
		return uuid;
	}
}
