package com.cyc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cyc.dao.UserInfoDAO;
import com.cyc.entity.UserInfo;
import com.cyc.utils.DButils;

public class UserInfoDAOImpl implements UserInfoDAO {
	
	private Connection conn = null;
	private DButils DB =  new DButils();
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private int ID = 0; 
	@Override
	/*
	 * public void insert(UserInfo userinfo) throws SQLException { String sql =
	 * "insert into userinfo(schoolid,openid,enrollmentyear,phonenumber,area,avatar,name,registrationtime)values(?,?,?,?,?,?,?,?)"
	 * ; conn = new DButils().getConnection(); ps = conn.prepareStatement(sql);
	 * ps.setString(1, userinfo.getSchoolid()); ps.setString(2,
	 * userinfo.getOpenid()); ps.setInt(3, userinfo.getEnrollmentyear());
	 * ps.setString(4, userinfo.getPhonebnumber());
	 * ps.setString(5,userinfo.getArea()); ps.setString(6,userinfo.getAvatar());
	 * ps.setString(7,userinfo.getName()); ps.setTimestamp(8,
	 * userinfo.getRegistrationtime()); ps.executeUpdate(); ps.close();
	 * System.out.println("success to register!"); conn.close(); }
	 */
	
	public void insert(UserInfo userinfo) {
		String sql = "insert into userinfo(openid,avatar,name,registrationtime)values(?,?,?,?)";
		DB.update(sql, userinfo.getOpenid(),userinfo.getAvatar(),userinfo.getName(),userinfo.getRegistrationtime());
	}
	@Override
	public void update(UserInfo userinfo) throws Exception {
		// TODO 自动生成的方法存根
		

	}
	public int getId(UserInfo ui) {
		String sql = "select id from userinfo where openid = ?";
		UserInfo userInfoRS = DB.get(UserInfo.class, sql, ui.getOpenid());
		return userInfoRS.getId();
		
	}
	@Override
	public void delete(UserInfo userinfo) throws Exception {
		// TODO 自动生成的方法存根

	}
	@Override
	public boolean queryByOpenId(String openid) throws SQLException {
		String sql = "select * from userinfo where openid = ?";
		conn = DB.getConnection();
		ps = conn.prepareStatement(sql);
		ps.setString(1, openid);
		rs = ps.executeQuery();
		if(rs.next()) {
			ID = rs.getInt("id");
			ps.close();
			conn.close();
			return true;
		}
		else {
			ps.close();
			conn.close();
			return false;
		}
	}
	public int getID() {
		return ID;
	}

	@Override
	public UserInfo getUserInfobyID(int id) throws SQLException {
		// TODO 自动生成的方法存根
		String sql = "select * from userinfo where id = ?";
		
		return DB.get(UserInfo.class, sql, id);
	}
	
	@Override
	public void editUserInfo(int userid, int areaid, String schoolid, String phonenumber, String enrollmentyear)
			throws SQLException {
		String sql = "update userinfo SET area = ?,schoolid =?,phonenumber = ?, enrollmentyear = ? where id = ?";
		DB.update(sql,areaid,schoolid,phonenumber,enrollmentyear,userid);
		
	}
	@Override
	public void editUserInfo(int userid, String item, String value) throws SQLException {
		String sql = "update userinfo SET "+item+"=? where id = ?";
		DB.update(sql, value,userid);
		
	}
}
