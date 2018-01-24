package lpdata.service;

import org.json.JSONArray;
import org.json.JSONObject;

import lpdata.dao.MenuDao;
import lpdata.util.MyUtil;

public class MenuService {
	public MenuDao dao = new MenuDao();
	private String tableName = "common_menu";

	public String getList() {
		String select = " select * from  " + tableName;
		String sql = select;
		JSONArray list = (JSONArray) dao.getList(sql);
		JSONObject jsonObj = new JSONObject();
		jsonObj = MyUtil.getJson("成功", 200, list);
		return jsonObj.toString();
	}
}
