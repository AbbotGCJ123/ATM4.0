package com.feicuiedu.atm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ATMjdbc {
	
/*	public static void main(String[] args) {
		
		Connection con = getAtmConnection();
		
		if (con == null) {
			System.out.println("null");
		}else {
			System.out.println("not null");
		}
		
	}*/
	
	/**
	 * 获取连接对象
	 * @return Connection
	 */
	private static Connection getAtmConnection() {
		
		Connection con = null;
		
		try {
			
			//String driver = "org.gjt.mm.mysql.Driver";
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			con = DriverManager.getConnection("jdbc:mysql://192.168.2.108:3306/db_atm?useUnicode=true&characterEncoding=utf-8&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Hongkong","root","mysql123");
			//con.close();
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return con;
	}
	
	/**
	 * 获得指定字符在字符串中出现的次数
	 * @param sql 指定的字符串
	 * @param c 指定的字符
	 * @return
	 */
	private static int getCount(String sql,char c) {
		
		int count = 0;
		
		for(int i = 0;i<sql.length();i++) {
			
			if (c == sql.charAt(i)) {
				
				count ++;
			}
		}
		return count;
	}
	
	/**
	 * 获得查询后的结果集   
	 * @param sql   查询的sql语句
	 * @param strings   sql语句的参数  可多个
	 * @return  查询后的结果集ResultSet
	 */
	public ResultSet getResultSet(String sql,String...strings) {
		
		ResultSet rs = null;
		
		try {
			
			//获得PreparedStatement 对象    调用executeQuery 方法  获得结果集
			PreparedStatement statement = getPreparedStatement(sql,strings);
			rs = statement.executeQuery();
			
		}catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return rs;
	}
	
	/**
	 * 获得PreparedStatement 并 将有参数的 sql赋上值
	 * @param sql
	 * @param strings
	 * @return
	 */
	public PreparedStatement getPreparedStatement(String sql,String ...strings) {

		PreparedStatement statement = null;
		int count = getCount(sql, '?');
		if (count == strings.length) {
			
			try {
				
				//从链接中获取到 prepareStatement 对象
				statement = getAtmConnection().prepareStatement(sql);
				for(int i = 1;i<=count;i++) {
					
					statement.setString(i, strings[i-1]);
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		
		return statement;
	}
	
	/**
	 * 适用于增加  删除  修改  返回受影响的行数
	 * @param sql 添加的sql语句
	 * @param strings 参数
	 * @return 受影响的行数
	 */
	public int updateshow(String sql,String...strings) {
		
		PreparedStatement statement = getPreparedStatement(sql, strings);
		int count = 0;
		
		try {
			count = statement.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return count;
	}
	
}

