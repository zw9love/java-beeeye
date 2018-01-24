package lpdata.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

import lpdata.util.DatabaseConnection;

public class MenuDao {

	// 获得list, 无list参数
	public JSONArray getList(String sql) {
		try {
			// Class.forName(driver);
			Connection con = DatabaseConnection.getCon();
			Statement stet = con.createStatement();
			ResultSet rs = stet.executeQuery(sql);

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			JSONArray list = new JSONArray();
			// JSONObject map = new JSONObject();
			while (rs.next()) {
				JSONObject jsonObj = new JSONObject();
				for (int i = 1; i <= columnCount; i++) {
					String columnName = metaData.getColumnLabel(i);
					Object value = rs.getObject(columnName);
					// String value = rs.getString(columnName);
					jsonObj.put(columnName, value);
				}
				list.put(jsonObj);
			}

			con.close();
			return list;

		} catch (Exception e) {
			JSONArray list = new JSONArray();
			e.printStackTrace();// TODO: handle exception
			// return "error";
			return list;
		}

	}

}
