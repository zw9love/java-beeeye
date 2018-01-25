package lpdata.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

import lpdata.util.MyUtil;

public class TokenErrorAction {
	public void execute() throws IOException {
		System.out.println("进入了TokenErrorAction");
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsonObj = MyUtil.getJson("用户登录失效。", 606, "");
		response.getWriter().write(jsonObj.toString());
	}
}
