package lpdata.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;

public class RegeditAction {
	public void getFileList() throws IOException {
		// System.out.println("进来RegeditAction");
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		JSONArray array = new JSONArray();
		array.put("windows_10_x64");
		array.put("windows_2003_x64");
		array.put("windows_2003_x86");
		array.put("windows_2008_x64");
		array.put("windows_2008_x86");
		array.put("windows_2012_x64");
		array.put("windows_7_x64");
		array.put("windows_7_x86");
		array.put("windows_8_x64");
		array.put("windows_XP_x86");
		// System.out.println(array.toString());
		response.getWriter().write(array.toString());
	}
}
