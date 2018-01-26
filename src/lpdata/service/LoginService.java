package lpdata.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import lpdata.dao.LoginDao;
import lpdata.util.MD5Util;
import lpdata.util.MyUtil;

public class LoginService {
	public LoginDao dao = new LoginDao();
	private String tableName = "common_user";

	public JSONObject checkLogin(HttpServletRequest request, Map<String, String> json) throws JSONException {

		String select = " select * from  " + tableName;
		String where = " where login_name = ? and login_pwd = ? ";
		String sql = select + where;
		// JSONArray array = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		JSONObject resObj = new JSONObject();
		String login_pwd = MD5Util.encrypt(json.get("login_pwd").toString());
		// login_pwd => G2LB1ELM7D5FC71D1M6E2EBMACE0L2F5
		String[] data = { json.get("login_name"), login_pwd };
		JSONArray list = (JSONArray) dao.checkLogin(sql, data);
		if (list.length() > 0) {
			String token = MyUtil.getRandomString();
			HttpSession session = request.getSession();
			session.setAttribute(token, list.get(0));
			session.setAttribute(((JSONObject) list.get(0)).get("login_name").toString(), token);
			jsonObj = MyUtil.getJson("成功", 200, "");
			resObj = MyUtil.getJson(token, 200, jsonObj);
		} else {
			jsonObj = MyUtil.getJson("账号或者密码错误。", 606, "");
			resObj = MyUtil.getJson("", 606, jsonObj);
		}

		// array.put(jsonObj);
		// System.out.println(resObj.toString());
		return resObj;
	}
}
