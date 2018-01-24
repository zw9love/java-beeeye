package lpdata.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import lpdata.service.MenuService;

public class MenuAction extends ActionSupport {

	public MenuService service = new MenuService();

	public void get() throws IOException {
		// System.out.println("进来了menu的get方法");
		StringBuilder sb = new StringBuilder();
		HttpServletResponse response = ServletActionContext.getResponse();
		String data = service.getList();
		// System.out.println(data);
		response.getWriter().write(data);

	}

}
