package util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
	private static String user;
	private static String password;
	private static String url;
	private static String driverName;
	//单例的模式
	private static ThreadLocal<Connection> t1= new ThreadLocal<Connection>();
	
	static{
		Properties p = new Properties();
		try {
			System.out.println(DBUtil.class.getClassLoader().getResource("").getPath());
			/*
			/D:/Workspaces_for_IDEA/myNETCTOSS/target/
			 */
			InputStream is= DBUtil.class.getClassLoader().getResourceAsStream("db.properties");
			p.load(is);
			is.close();
			user = p.getProperty("user").trim();
			password = p.getProperty("password").trim();
			url = p.getProperty("url").trim();
			driverName = p.getProperty("driver").trim();
			Class.forName(driverName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("读取数据库匹配失败");
		}
	}
	
	public static Connection getConnection(){
		Connection conn = t1.get();
		if(conn == null){
			try {
				conn = DriverManager.getConnection(url,user,password);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("获取连接失败");
			}
		}
		return conn;
	}

	public static void closeConnection() {
		Connection conn = t1.get();
		if(conn != null){
			try {
				conn.close();
				t1.set(null);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("关闭连接失败！");
			}
		}
	}
	public static void main(String[] args) {
		Connection conn = DBUtil.getConnection();
		System.out.println(conn);
		DBUtil.closeConnection();
	}
}
