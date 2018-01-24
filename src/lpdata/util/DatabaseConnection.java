package lpdata.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	private static Connection conn = null;

	public static Connection getCon() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // 加载数据库连接驱动
			String user = "root";
			String psw = "159357";
			String url = "jdbc:mysql://localhost:3306/beeeyehced?characterEncoding=UTF-8";
			conn = DriverManager.getConnection(url, user, psw); // 获取连接
		} catch (Exception e) {
			System.out.println("连接数据库失败");
			e.printStackTrace();
		}
		return conn;
	}
}
