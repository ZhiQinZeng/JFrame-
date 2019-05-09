package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Statement;

public class JDBCUtils {
	private static Properties properties;
	//注册驱动
	static{
		//静态代码块当类被加载的时候会执行，只要JDBCUtils类被第一次用到，JDBCUtils类就会被加载到内存，
		//静态代码块就会执行，驱动会被注册
		try {
			//通过类加载器读取src目录中的 properties文件
			ClassLoader cl = JDBCUtils.class.getClassLoader();
			InputStream is = cl.getResourceAsStream("db.properties");
			
			//Properties有一个对应的类，可以用于读取文件信息
			properties = new Properties();
			//通过输入流加载properties文件
			properties.load(is);
			
			//通过key获取到properties文件中对应的值
			String driver = properties.getProperty("driver");
			//注册驱动
			Class.forName(driver);
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
	//获取连接
	public static Connection getConnection(){
		
		//读取配置文件中的账号，密码等信息，用于连接数据库
		String url = properties.getProperty("url");
		String user = properties.getProperty("username");
		String password = properties.getProperty("password");
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	//释放资源
	public static void release(ResultSet resultSet,Statement statement,Connection connection){
		//资源在finnaly中释放
		if(resultSet != null){
			try {
				resultSet.close(); 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	}
}
