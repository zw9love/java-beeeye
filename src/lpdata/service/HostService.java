package lpdata.service;

import java.util.Map;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import lpdata.dao.HostDao;
import lpdata.util.MD5Util;
import lpdata.util.MyUtil;

public class HostService {
	public HostDao dao = new HostDao();
	private String tableName = "beeeye_host";

	public String fetchSingleData(String ids) {

		String select = " select * from  " + tableName;
		String count = "SELECT count(*) as sum FROM " + tableName;
		String where = " where host_ids = ? ";
		String sql = select + where;
		// JSONArray array = new JSONArray();
		JSONObject jsonObj = new JSONObject();

		String[] data = { ids };
		JSONArray list = (JSONArray) dao.getList(sql, data);
		if (list.length() > 0)
			jsonObj = MyUtil.getJson("成功", 200, "");
		else
			jsonObj = MyUtil.getJson("hostIds不存在。", 606, "");

		// array.put(jsonObj);

		return jsonObj.toString();
	}

	public String getPageData(Map<String, Object> page) {

		int pageNumber = (int) Double.parseDouble(page.get("pageNumber").toString());
		int pageSize = (int) Double.parseDouble(page.get("pageSize").toString());
		int pageStart = (pageNumber - 1) * pageSize;
		String limit = " limit " + pageStart + " , " + pageSize;
		// System.out.println(pageNumber);
		// System.out.println(pageSize);
		String select = " select * from  " + tableName;
		String count = "SELECT count(*) as sum FROM " + tableName;
		String where = " where 1 = 1 ";
		String sql = select + where + limit;
		// JSONArray array = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		JSONObject resObj = new JSONObject();
		String[] data = { tableName };
		JSONArray list = (JSONArray) dao.getList(sql);
		int totalRow = dao.getCount(count);
		int totalPage = 1;
		// System.out.println(sql);
		if (totalRow % pageSize == 0) {
			totalPage = totalRow / pageSize;
		} else {
			totalPage = (int) Math.floor(totalRow / pageSize) + 1;
		}

		try {
			resObj.put("list", list);
			resObj.put("pageNumber", pageNumber);
			resObj.put("pageSize", pageSize);
			resObj.put("totalPage", totalPage);
			resObj.put("totalRow", totalRow);
			resObj.put("firstPage", pageNumber == 1 ? true : false);
			resObj.put("lastPage", pageNumber == totalPage ? true : false);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		jsonObj = MyUtil.getJson("成功", 200, resObj);

		// array.put(jsonObj);

		return jsonObj.toString();
	}

	public String getSizeData(Map<String, Object> size) {
		int pageStart;
		// System.out.println(size.get("beforeId").toString().isEmpty());
		if (size.get("beforeId").toString().isEmpty()) {
			System.out.println("beforeId为空");
			pageStart = 0;
		} else {
			System.out.println("beforeId不为空");
			pageStart = (int) Double.parseDouble(size.get("beforeId").toString());
		}
		int pageSize = (int) Double.parseDouble(size.get("size").toString());
		// int pageStart = (pageNumber - 1) * pageSize;
		String limit = " limit " + pageStart + " , " + pageSize;
		// System.out.println(pageNumber);
		// System.out.println(pageSize);
		String select = "select * from " + tableName;
		String count = "SELECT count(*) as sum FROM " + tableName;
		String where = " where 1 = 1 ";
		String sql = select + where + limit;
		System.out.println(sql);
		// JSONArray array = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		JSONObject sizeObj = new JSONObject();
		JSONObject resObj = new JSONObject();

		JSONArray list = (JSONArray) dao.getList(sql);
		int totalRow = dao.getCount(count);

		try {
			sizeObj.put("beforeId", pageStart + pageSize);
			sizeObj.put("offset", "");
			sizeObj.put("size", pageSize);
			resObj.put("list", list);
			resObj.put("totalRow", totalRow);
			resObj.put("size", sizeObj);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		jsonObj = MyUtil.getJson("成功", 200, resObj);

		// array.put(jsonObj);
		return jsonObj.toString();
	}

	public String deleteData(String ids) {
		String delete = "DELETE FROM " + tableName;
		String where = " where host_ids = ? ";
		String sql = delete + where;
		JSONObject jsonObj = new JSONObject();
		int effectRows = dao.deleteData(sql, ids);
		if (effectRows > 0)
			jsonObj = MyUtil.getJson("成功", 200, "");
		else
			jsonObj = MyUtil.getJson("失败", 606, "");

		return jsonObj.toString();
	}

	public String addData(Map<String, Object> list) {
		String sql = "INSERT INTO " + tableName
				+ " (host_ids, name, ip, port, os_type, os_version, os_arch, login_name, login_pwd, record_hash, status) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		JSONObject jsonObj = new JSONObject();
		String uuid = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 24);
		String login_pwd = MD5Util.encrypt(list.get("login_pwd").toString());
		Object[] data = { uuid, list.get("name"), list.get("ip"), list.get("port"), list.get("os_type"),
				list.get("os_version"), list.get("os_arch"), list.get("login_name"), login_pwd, "", 0 };

		// System.out.println(data);
		int effectRows = dao.addData(sql, data);
		if (effectRows > 0)
			jsonObj = MyUtil.getJson("成功", 200, "");
		else
			jsonObj = MyUtil.getJson("失败", 606, "");

		return jsonObj.toString();
	}

	public String updateData(Map<String, Object> list) throws JSONException {
		String sql = "UPDATE " + tableName
				+ "  SET name = ?, ip = ?, port = ?, login_name = ?, login_pwd = ? where host_ids = ? ";
		String login_pwd = MD5Util.encrypt(list.get("login_pwd").toString());
		JSONObject jsonObj = new JSONObject();
		Object[] data = { list.get("name"), list.get("ip"), list.get("port"), list.get("login_name"), login_pwd,
				list.get("host_ids") };

		// System.out.println(data);
		int effectRows = dao.updateData(sql, data);
		if (effectRows > 0)
			jsonObj = MyUtil.getJson("成功", 200, "");
		else
			jsonObj = MyUtil.getJson("失败", 606, "");

		return jsonObj.toString();
	}
}
