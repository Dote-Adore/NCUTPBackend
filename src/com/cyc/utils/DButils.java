package com.cyc.utils;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;


public class DButils {
	public Connection getConnection()throws SQLException {
		Driver driver = new com.mysql.cj.jdbc.Driver();
		String url;
		String osname = System.getProperty("os.name").toLowerCase();
		if(osname.equals("windows 10")) {
			url = "jdbc:mysql://106.15.36.71:3306/NCUTradingPlatform";
		}
		else {
			url = "jdbc:mysql://localhost:3306/NCUTradingPlatform";
		}
		
		Properties info = new Properties();
		info.put("user","root");
		info.put("password","135790");
		Connection connection = driver.connect(url, info);
		return connection;
	}
	
	//insert update delete 都在这里面
	public void update(String sql, Object ...args) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			for(int i = 0;i<args.length;i++) {
				ps.setObject(i+1,args[i]);
			}
			ps.executeUpdate();
			ps.close();
			conn.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getCount(String sql,Object...args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			for(int i = 0;i<args.length;i++) {
				ps.setObject(i+1,args[i]);
			}
			rs  = ps.executeQuery();
			if(rs.next())
				result=rs.getInt(1);
			ps.close();
			conn.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	//返回一条记录
	public <T> T get(Class<T> clazz, String sql, Object ...args) {
		
		T entity = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			for(int i = 0;i<args.length;i++) {
				ps.setObject(i+1, args[i]);
			}
			rs = ps.executeQuery();
			if(rs.next()) {
				HashMap<String, Object> values = new HashMap<String,Object>();
				java.sql.ResultSetMetaData rsmd = rs.getMetaData();
				int columnCount = rsmd.getColumnCount();
				for(int i = 0;i<columnCount;i++) {
					String columLabel = rsmd.getColumnLabel(i+1);
					Object columValue = rs.getObject(i+1);
					values.put(columLabel, columValue);
				}
				
				entity = clazz.newInstance();
				
				for(HashMap.Entry<String,Object> entry:values.entrySet()) {
					String propertyName = entry.getKey();
					Object value = entry.getValue();
					ReflectionUtils.setFieldValue(entity, propertyName, value);	
				}	
			}
			ps.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
	
		}
		
		return entity;
	}
	//返回多条记录
	public <T> List<T> getForList(Class<T> clazz, String sql, Object ...args) {
		List<T> list = new ArrayList<T>();
		PreparedStatement ps = null;
		try {
			Connection conn = getConnection();
			ps = conn.prepareStatement(sql);
			
			for(int i = 0;i<args.length;i++) {
				ps.setObject(i+1, args[i]);
			}
			ResultSet rs = ps.executeQuery();
			
			
			HashMap<String, Object> map = null;
			List<HashMap<String, Object>> values = new ArrayList<>();
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();//获取表的信息，包括列名
			while(rs.next()) {
				map = new HashMap<String, Object>();
				for(int i =0;i<rsmd.getColumnCount();i++) {
					String columnLabel = rsmd.getColumnLabel(i+1);
					Object value = rs.getObject(i+1);
					map.put(columnLabel, value);
				}
				values.add(map);
			}
			
			T bean = null;
			
			if(values.size()>0) {
				for(HashMap<String, Object> m:values) {
					bean = clazz.newInstance();
					for(HashMap.Entry<String,Object> entry: m.entrySet()) {
						String properName = entry.getKey();
						Object value = entry.getValue();
						ReflectionUtils.setFieldValue(bean, properName, value);
					}
					list.add(bean);
				}
			}
			
		} catch (SQLException | InstantiationException | IllegalAccessException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	
	//
	<E> E getForValue(String sql, Object ...args) {
		return null;
	}
	
	public int BackIdWhenUpdate(String sql,Object ...args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int id = 0;
		
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			for(int i = 0;i<args.length;i++) {
				ps.setObject(i+1,args[i]);
			}
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if(rs.next())
				id = rs.getInt(1);
			ps.close();
			conn.close();
		}
			catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		return id;
	}

}
