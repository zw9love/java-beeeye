package lpdata.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.json.JSONArray;
import org.json.JSONObject;

import lpdata.util.DatabaseConnection;

public class LoginDao {

	public JSONArray checkLogin(String sql, String[] data) {

		try {
			// Class.forName(driver);
			Connection con = DatabaseConnection.getCon();
			// Statement stet = con.createStatement();
			// System.out.println(sql);
			PreparedStatement preState = con.prepareStatement(sql);
			int index = 1;
			for (String item : data) {
				preState.setString(index, item);
				index++;
			}

			ResultSet rs = preState.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			JSONArray list = new JSONArray();
			// JSONObject map = new JSONObject();
			while (rs.next()) {
				JSONObject jsonObj = new JSONObject();
				for (int i = 1; i <= columnCount; i++) {
					String columnName = metaData.getColumnLabel(i);
					Object value = rs.getObject(columnName);
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
