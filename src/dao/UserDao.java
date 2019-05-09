package dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import bean.User;
import utils.JDBCUtils;

/*
 * 	此类负责用户相关的操作
 * 		查询用户
 * 		插入用户
 *      更新用户
 *      删除用户
 */
public class UserDao {
	
	//	根据用户名和密码查询用户信息
	public User findUser(String username,String password) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = JDBCUtils.getConnection();
		String sql = "select * from user where username = ? and password = ?";
		Object[] params = {username,password};
		/*
		 * BeanListHandler:将结果集封装为list集合
		 * BeanHandler：将结果集中的第一条记录封装为对象
		 */
		try {
			User user = (User)queryRunner.query(connection, sql,new BeanHandler(User.class),params);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtils.release(null, null, connection);
		}
		return null;
	}
}

