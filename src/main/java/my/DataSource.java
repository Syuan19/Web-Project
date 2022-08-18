package my;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSource
{
	// C3P0连接池，相关配置在 c3p0-config.xml
	static ComboPooledDataSource pool = new ComboPooledDataSource();
		
	// 加载 JDBC 驱动
	static{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e)
		{
			System.out.println("找不到 MySQL 的 JDBC驱动!");
			e.printStackTrace();
		}
	}	

	public static String jdbcUrl = "jdbc:mysql://localhost:3306/af_school?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
	public static String user = "root";
	public static String password = "msy010518";
	
	// 获取数据库连接
	public static Connection getConnection() throws Exception
	{
		//Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
		Connection conn = pool.getConnection();
		System.out.println("连接成功!");
		return conn;
	}
	
	// 查询 student 表的内容
	public static List<Student> queryAll() throws Exception
	{
		List<Student> result = new ArrayList<>();

		// try-with-resources语法，相当于 try {} finally{ conn.close()}
		try(Connection conn = getConnection()) {			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM student");
			while (rs.next())
			{
				Student row = new Student();
				row.id = rs.getInt("id");
				row.name = rs.getString("name");
				row.sex = rs.getBoolean("sex");
				row.phone = rs.getString("phone");
				
				result.add( row );
			}			
		}	
		
		return result;
	}
	
	// 查询 student , 按姓名过滤
	public static List<Student> query(String filter) throws Exception
	{
		List<Student> result = new ArrayList<>();

		// try-with-resources语法，相当于 try {} finally{ conn.close()}
		try(Connection conn = getConnection()) {			
			Statement stmt = conn.createStatement();
			
			String sql = "SELECT * FROM student";
			String where = "";
			if( filter.length() > 0)
			{
				where = " WHERE name LIKE '%" + filter + "%' ";
				sql += where;
			}
			
			System.out.println("SQL: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next())
			{
				Student row = new Student();
				row.id = rs.getInt("id");
				row.name = rs.getString("name");
				row.sex = rs.getBoolean("sex");
				row.phone = rs.getString("phone");
				
				result.add( row );
			}			
		}	
		
		return result;
	}
	
	public static List<Student> add(Student r) throws Exception
	{
		List<Student> result = new ArrayList<>();

		// try-with-resources语法，相当于 try {} finally{ conn.close()}
		try(Connection conn = getConnection()) {			
			Statement stmt = conn.createStatement();
			
			String sqlfmt = "INSERT INTO student(`id`,`name`,`sex`, `phone`) "
					+ "VALUES ('%d', '%s', '%d', '%s') ";
			String sql = String.format(sqlfmt, r.id, r.name, r.sex?1:0, r.phone);
					
			System.out.println("SQL: "+ sql);
			
	
			stmt.execute(sql);					
		}	
		
		return result;
	}
	
	public static List<Student> remove(int id) throws Exception
	{
		List<Student> result = new ArrayList<>();

		// try-with-resources语法，相当于 try {} finally{ conn.close()}
		try(Connection conn = getConnection()) {			
			Statement stmt = conn.createStatement();
			
			String sqlfmt = "DELETE FROM student WHERE id='%d' ";
			String sql = String.format(sqlfmt, id);
					
			System.out.println("SQL: "+ sql);
			
	
			stmt.execute(sql);					
		}	
		
		return result;
	}
	
	public static void main(String[] args) throws Exception
	{
		List<Student> sss = DataSource.queryAll();
		
		System.out.println("Exit");
	}
	
}
