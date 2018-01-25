package lpdata.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

import lpdata.util.MyUtil;

public class MethodErrorAction {
	public void execute() throws IOException {
		System.out.println("进入了MethodErrorAction");
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsonObj = MyUtil.getJson("请使用post请求。", 606, "");
		response.getWriter().write(jsonObj.toString());
	}
}
