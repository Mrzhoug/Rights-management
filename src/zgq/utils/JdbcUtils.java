package zgq.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {

	private JdbcUtils(){}
	
	private static DataSource ds ;
	
	// 初始化数据库连接池
	static {
		try {
			ComboPooledDataSource cpds = new ComboPooledDataSource();	// 使用 默认的配置
			ds = cpds;
		} catch(Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	//获得数据库连接池
	public static DataSource getDataSource() {
		return ds;
	}
	
	// 获得连接  找 dbcp 要连接
	public static Connection getConnection() throws SQLException {
		
		return ds.getConnection();
	}

	// 释放资源
	public static void release(ResultSet rs, Statement stmt, Connection conn) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rs = null;
		}
		
		
		if(stmt!=null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stmt = null;
		}
		
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = null;
		}
	}

}
