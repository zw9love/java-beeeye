package lpdata.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

import lpdata.util.DatabaseConnection;

public class HostDao {
	private String tableName = "beeeye_host";

	// 获得list, 有list参数
	public JSONArray getList(String sql, String[] data) {
		try {
			// Class.forName(driver);
			Connection con = DatabaseConnection.getCon();
			// Statement stet = con.createStatement();
			System.out.println(sql);
			PreparedStatement preState = con.prepareStatement(sql);
			int index = 1;
			for (String item : data) {
				preState.setString(index, item);
				index++;
			}

			ResultSet rs = preState.executeQuery();
			// ResultSet rs = stet.executeQuery(sql);

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

	// 获得列表总数
	public int getCount(String sql) {
		try {
			// Class.forName(driver);
			Connection con = DatabaseConnection.getCon();
			Statement stet = con.createStatement();

			// 我的数据库Person中的表student，改成你自己的表

			ResultSet rs = stet.executeQuery(sql);

			ResultSetMetaData metaData = rs.getMetaData();
			int totalRow = 0;

			while (rs.next()) {
				totalRow = Integer.parseInt(rs.getString("sum"));
			}

			con.close();
			return totalRow;

		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception

			return 0;
		}
	}

	// 删除
	public int deleteData(String sql, String ids) {
		Connection con = DatabaseConnection.getCon();
		// Statement stet = con.createStatement();
		// System.out.println(sql);
		PreparedStatement preState;
		try {
			preState = con.prepareStatement(sql);
			preState.setString(1, ids);
			int rs = preState.executeUpdate();
			// System.out.println(rs);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	// 增加
	public int addData(String sql, Object[] data) {
		Connection con = DatabaseConnection.getCon();
		// Statement stet = con.createStatement();
		// System.out.println(sql);
		PreparedStatement preState;
		try {
			preState = con.prepareStatement(sql);
			int index = 1;
			for (Object item : data) {
				preState.setObject(index, item);
				index++;
			}
			int rs = preState.executeUpdate();
			// System.out.println(rs);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	// 修改
	public int updateData(String sql, Object[] data) {
		Connection con = DatabaseConnection.getCon();
		// Statement stet = con.createStatement();
		// System.out.println(sql);
		PreparedStatement preState;
		try {
			preState = con.prepareStatement(sql);
			int index = 1;
			for (Object item : data) {
				preState.setObject(index, item);
				index++;
			}
			int rs = preState.executeUpdate();
			// System.out.println(rs);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

}
