package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import bean.Emp;
import utils.JDBCUtils;
public class EmpDao {
	/*
	 *   查询客户
	 *   删除客户
	 *   更新客户
	 *   插入客户
	 */
	public List<Emp> getEmps() {
		
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = JDBCUtils.getConnection();
		String sql = "select * from emp";
		/*
		 * BeanListHandler:将结果集封装为list集合
		 * BeanHandler：将结果集中的第一条记录封装为对象
		 */
		try {
			List<Emp> list = (List<Emp>)queryRunner.query(connection, sql,new BeanListHandler(Emp.class));
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtils.release(null, null, connection);
		}
		return null;
	}
	public List<Emp> getEmps(int start,int number) {
		
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = JDBCUtils.getConnection();
//		                                     start number
		String sql = "select * from emp limit ?,?";
		Object[] pramas = {start,number};
		/*
		 * BeanListHandler:将结果集封装为list集合
		 * BeanHandler：将结果集中的第一条记录封装为对象
		 */
		try {
			List<Emp> list = (List<Emp>)queryRunner.query(connection, sql,new BeanListHandler(Emp.class),pramas);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtils.release(null, null, connection);
		}
		return null;
	}
	
//	查询数据的总条数
	public int getCount() {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = JDBCUtils.getConnection();
//		                                     start number
		String sql = "select count(empno) from emp";
		/*
		 * BeanListHandler:将结果集封装为list集合
		 * BeanHandler：将结果集中的第一条记录封装为对象
		 * ScalarHandler: 处理单个值，比如记录的个数
		 */
		try { 
			long count = (long)queryRunner.query(connection, sql,new ScalarHandler());
			return (int) count;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtils.release(null, null, connection);
		}
		return 0;
	}
	public void add(Emp emp) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = JDBCUtils.getConnection();
//		                                     start number
		String sql = "insert into emp (ename,job,hiredate,sal) values(?,?,?,?)";
		Object[] params = {emp.getEname(),emp.getJob(),emp.getHireDate(),emp.getSal()};
		/*
		 * BeanListHandler:将结果集封装为list集合
		 * BeanHandler：将结果集中的第一条记录封装为对象
		 * ScalarHandler: 处理单个值，比如记录的个数
		 */
		try { 
			queryRunner.update(connection, sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtils.release(null, null, connection);
		}
		
	}
	public void update(Emp emp) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = JDBCUtils.getConnection();
//		                                     start number
		String sql = "update emp set ename = ?,job = ?,hiredate = ?,sal = ? where empno = ?";
		Object[] params = {emp.getEname(),emp.getJob(),emp.getHireDate(),emp.getSal(),emp.getEmpno()};
		/*
		 * BeanListHandler:将结果集封装为list集合
		 * BeanHandler：将结果集中的第一条记录封装为对象
		 * ScalarHandler: 处理单个值，比如记录的个数
		 */
		try { 
			queryRunner.update(connection, sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtils.release(null, null, connection);
		}
		
	}
	public void delete(int empno) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = JDBCUtils.getConnection();
//		                                     start number
		String sql = "delete from emp where empno = ?";
		Object[] params = {empno};
		/*
		 * BeanListHandler:将结果集封装为list集合
		 * BeanHandler：将结果集中的第一条记录封装为对象
		 * ScalarHandler: 处理单个值，比如记录的个数
		 */
		try { 
			queryRunner.update(connection, sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtils.release(null, null, connection);
		}
	}
	
	
	public List<Emp> search(String findname){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = JDBCUtils.getConnection();
		String sql = "select * from emp where ename = ?";
		/*
		 * BeanListHandler:将结果集封装为list集合
		 * BeanHandler：将结果集中的第一条记录封装为对象
		 */
		Object[] pramas = {findname};
		/*
		 * BeanListHandler:将结果集封装为list集合
		 * BeanHandler：将结果集中的第一条记录封装为对象
		 */
		try {
			List<Emp> list = (List<Emp>)queryRunner.query(connection, sql,new BeanListHandler(Emp.class),pramas);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtils.release(null, null, connection);
		}
		return null;
		
	}
	
	
	public List<Emp> bumenfind(){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = JDBCUtils.getConnection();
		String sql = "select distinct(job) from emp ";
		/*
		 * BeanListHandler:将结果集封装为list集合
		 * BeanHandler：将结果集中的第一条记录封装为对象
		 */
		//Object[] pramas = {findname};
		/*
		 * BeanListHandler:将结果集封装为list集合
		 * BeanHandler：将结果集中的第一条记录封装为对象
		 */
		try {
			List<Emp> list = (List<Emp>)queryRunner.query(connection, sql,new BeanListHandler(Emp.class));
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtils.release(null, null, connection);
		}
		return null;
		
	}
	
	
	public List<Emp> bumenvalue(String bumenvalue){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = JDBCUtils.getConnection();
		String sql = "select * from emp where job=?";
		/*
		 * BeanListHandler:将结果集封装为list集合
		 * BeanHandler：将结果集中的第一条记录封装为对象
		 */
		Object[] pramas = {bumenvalue};
		/*
		 * BeanListHandler:将结果集封装为list集合
		 * BeanHandler：将结果集中的第一条记录封装为对象
		 */
		try {
			List<Emp> list = (List<Emp>)queryRunner.query(connection, sql,new BeanListHandler(Emp.class),pramas);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtils.release(null, null, connection);
		}
		return null;
		
	}
}
