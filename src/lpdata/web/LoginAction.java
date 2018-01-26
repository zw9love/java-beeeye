package lpdata.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opensymphony.xwork2.ActionSupport;

import lpdata.service.LoginService;
import lpdata.util.MyUtil;

public class LoginAction extends ActionSupport {

	public LoginService service = new LoginService();

	public String execute() throws Exception {
		System.out.println("进来了LoginAction");
		return SUCCESS;
	}

	public void doLogin() throws IOException, JSONException {
		// System.out.println("进来了menu的get方法");
		StringBuilder sb = new StringBuilder();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		try (BufferedReader reader = request.getReader()) {
			char[] buff = new char[1024];
			int len;
			while ((len = reader.read(buff)) != -1) {
				sb.append(buff, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		String str = sb.toString();
		Map<String, String> list = new Gson().fromJson(str, new TypeToken<Map<String, Object>>() {
		}.getType());

		JSONObject data = service.checkLogin(request, list);
		if ((int) data.get("status") == 200) {
			response.getWriter().write(data.get("data").toString());
			response.setHeader("token", data.get("msg").toString());
		} else {
			response.getWriter().write(data.get("data").toString());
		}
		// System.out.println(data);
		// response.getWriter().write(data);

	}

	public void loged() throws IOException, JSONException {
		StringBuilder sb = new StringBuilder();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		try (BufferedReader reader = request.getReader()) {
			char[] buff = new char[1024];
			int len;
			while ((len = reader.read(buff)) != -1) {
				sb.append(buff, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String str = sb.toString();
		Map<String, String> list = new Gson().fromJson(str, new TypeToken<Map<String, Object>>() {
		}.getType());

		String token = list.get("token");
		HttpSession session = request.getSession();
		JSONObject role = (JSONObject) session.getAttribute(token);
		JSONObject roleObj = new JSONObject();
		roleObj.put("role", role);
		JSONObject json = new JSONObject();
		if (role == null)
			json = MyUtil.getJson("失败", 606, "");
		else
			json = MyUtil.getJson("成功", 200, roleObj);
		response.getWriter().write(json.toString());
	}
}
