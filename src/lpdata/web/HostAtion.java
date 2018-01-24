package lpdata.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import lpdata.domain.Host;
import lpdata.service.HostService;

public class HostAtion extends ActionSupport implements ModelDriven<Host> {
	private String data;
	// 准备host 成员变量
	private Host host = new Host();
	public HostService service = new HostService();
	private String host_ids = "";

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	// public void get() throws IOException {
	public void get() throws IOException {
		// System.out.println(host.getName());
		// System.out.println(host.getHost_ids());
		// return SUCCESS;

		// 测试uuid
		// String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		// System.out.println(uuid);

		// String[] retArray = new String[24];
		// for (int i = 0; i < 24; i++) {
		// retArray[i] = UUID.randomUUID().toString().replaceAll("-", "");
		// }
		// System.out.println(retArray.length);

		// String uuid = UUID.randomUUID().toString().replaceAll("-",
		// "").substring(0, 24);
		// System.out.println(uuid);

		// 测试md5加密
		// MD5Util md5Util = new MD5Util();
		// String passwd = md5Util.encrypt("123456");
		// System.out.println(passwd);
		// System.out.println(md5Util.decrypt(passwd));

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

		// String str =
		// "{eror=34,list=[{id=99,bondcd=6},{id=98,bondcd=7}],over=345}";
		String str = sb.toString();
		Map<String, Object> rightLlist = new Gson().fromJson(str, new TypeToken<Map<String, Object>>() {
		}.getType());
		for (Entry e : rightLlist.entrySet()) {
			String value = e.getValue().toString();
			try {
				List<Map<String, String>> list = new Gson().fromJson(value, new TypeToken<List<Map<String, String>>>() {
				}.getType());
				e.setValue(list);
			} catch (JsonSyntaxException e1) {

			}
		}
		// System.out.println("---------------");
		Map<String, Object> page = (Map<String, Object>) rightLlist.get("page");
		Map<String, Object> size = (Map<String, Object>) rightLlist.get("size");
		String host_ids = (String) rightLlist.get("host_ids");
		// System.out.println(host_ids);
		// System.out.println(host_ids.isEmpty());
		if (host_ids == null) {
			if (page != null) {
				String data = service.getPageData(page);
				response.getWriter().write(data);
			} else if (size != null) {
				String data = service.getSizeData(size);
				response.getWriter().write(data);
			}
		} else {
			String data = service.fetchSingleData(host_ids);
			response.getWriter().write(data);
		}
	}

	public void delete() throws IOException, JSONException {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		String host_ids = request.getQueryString();
		System.out.println(host_ids);
		String data = service.deleteData(host_ids);
		response.getWriter().write(data);
	}

	public void add() throws IOException, JSONException {
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

		// String str =
		// "{eror=34,list=[{id=99,bondcd=6},{id=98,bondcd=7}],over=345}";
		String str = sb.toString();
		Map<String, Object> list = new Gson().fromJson(str, new TypeToken<Map<String, Object>>() {
		}.getType());
		// System.out.println(list);
		String data = service.addData(list);
		response.getWriter().write(data);

	}

	public void put() throws IOException, JSONException {
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

		// String str =
		// "{eror=34,list=[{id=99,bondcd=6},{id=98,bondcd=7}],over=345}";
		String str = sb.toString();
		Map<String, Object> list = new Gson().fromJson(str, new TypeToken<Map<String, Object>>() {
		}.getType());
		// System.out.println(list);
		String data = service.updateData(list);
		response.getWriter().write(data);

	}

	@Override
	public Host getModel() {
		// TODO Auto-generated method stub
		return host;
	}
}
