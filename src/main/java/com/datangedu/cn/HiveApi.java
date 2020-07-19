package com.datangedu.cn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.hadoop.conf.Configuration;
//import org.apache.sqoop.Sqoop;
//import org.apache.sqoop.tool.SqoopTool;
//import org.apache.sqoop.util.OptionsFileUtil;
//import org.junit.*;

public class HiveApi {
	// 驱动名称，固定写法
	private static String driverName = "org.apache.hive.jdbc.HiveDriver";
	// 连接路径
	private static String url = "jdbc:hive2://namenode1:10000/default";
	// 用户名、密码
	private static String user = "";
	private static String password = "";
	// 连接、声明、返回值
	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;

	// 加载驱动、创建连接
//	@Before
	public void init() {
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	// 释放资源
//	@After
	public void destory() throws Exception {
		if (rs != null) {
			rs.close();
		}
		if (stmt != null) {
			stmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}
	

	// 创建数据库
//	@Test
	public void createDatabase() throws Exception {
		String sql = "create database myhive1";
		System.out.println("Running:" + sql);
		stmt.execute(sql);
		System.out.println("Running:" + sql + ";success!");
	}

//	 查询数据库
//	@Test
	public void showDatabases() throws Exception {
		String sql = "show databases";
		System.out.println("Running:" + sql);
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			System.out.println(rs.getString(1));
		}
	}

	// 创建表
//	@Test
	public void createTable() throws Exception {
		String sql = "create table stu(code string,name string,gender string,school string,profession string,phone string) row format delimited fields terminated by '\t' stored as textfile";
		System.out.println("Running:" + sql);
		stmt.execute(sql);
		System.out.println("Running:" + sql + ";success!");
	}
	
	// 查询所有表
//		@Test
		public void showTables() throws Exception {
			String sql = "show tables";
			System.out.println("Running:" + sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
		}

	// 查询列
//	@Test
	public void loadColumns() throws Exception {
		String sql = "desc formatted stu";
		System.out.println("Running:" + sql);
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			System.out.println(rs.getString(1));
		}
	}

	// 加载数据
//	@Test
	public void loadData() throws Exception {
		String filePath = "/home/zhaochaoqun/hive/data/short-student-utf8.txt";
		String sql = "load data local inpath '"+filePath+"' overwrite into table stu";
		System.out.println("Running:" + sql);
		stmt.execute(sql);
		System.out.println("Running:" + sql + ";success!");
	}

	// 查询数据
//		@Test
		public void selectData() throws Exception {
			String sql = "select * from stu";
			System.out.println("Running:" + sql);
			rs = stmt.executeQuery(sql);
			System.out.println("学号"+"\t"+"姓名"+"\t"+"性别"+"\t"+"学校"+"\t"+"专业"+"\t"+"电话");
			while (rs.next()) {
				System.out.println(rs.getString("code")+"\t\t"+rs.getString("name")+"\t\t"+rs.getString("gender")+"\t\t"+rs.getString("school")+"\t\t"+rs.getString("profession")+"\t\t"+rs.getString("phone"));
			}
		}
	
		// 统计查询
//		@Test
		public void countData() throws Exception {
			String sql = "select count(*) from stu";
			System.out.println("Running:" + sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
		}
		
//		 删除数据库
//		@Test
//		public void DropDatabase() throws Exception {
//			String sql = "drop database if exists myhive";
//			System.out.println("Running:" + sql);
//			stmt.execute(sql);
//			System.out.println("Running:" + sql + ";success!");
//		}
//		
//			// 导入sql数据到hdfs
//				@Test
//				public void importtable() throws Exception {
//					String[] agrs = new String[] {
//		    				"--connect","jdbc:mysql://192.168.1.235/test?serverTimezone=GMT%2B8",
//		    				"--username","root",
//		    				"--password","123456","--fields-terminated-by","\\t",
//		    				"--table","emp2","-m","1"
//		    		};
//		    		String[] expandArguments = OptionsFileUtil.expandArguments(agrs);
//		    		SqoopTool tool = SqoopTool.getTool("import");
//		    		Configuration conf = new Configuration();
//		    		conf.set("fs.default.name", "hdfs://192.168.1.130:9000");
//		    		Configuration loadPlugins = SqoopTool.loadPlugins(conf);
//		    		Sqoop sqoop = 
//		    		new Sqoop((com.cloudera.sqoop.tool.SqoopTool)tool,loadPlugins);
//		    		 Sqoop.runSqoop(sqoop, expandArguments);
//				}
//				
//				// hdfs导入数据到hive，要修改权限
//				@Test
//				public void hdfsimporttohive() throws Exception {
//					String sql1 = "create table emp2(id string,name string,degree string,salary string,dept string) row format delimited fields terminated by '\t' stored as textfile";
//					stmt.execute(sql1);
//	    		 String filePath = "/user/ZCQ/emp2/part-m-00000";
//	    			String sql3 = "load data inpath '"+filePath+"' overwrite into table emp7";
//	    			System.out.println("Running:" + sql3);
//	    			stmt.execute(sql3);
//	    			System.out.println("Running:" + sql3 + ";success!");
//				}
		
				
				
}
