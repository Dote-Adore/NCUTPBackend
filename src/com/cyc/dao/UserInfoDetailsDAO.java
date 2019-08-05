package com.cyc.dao;

import java.sql.SQLException;

import com.cyc.entity.UserInfoDetails;

public interface UserInfoDetailsDAO {
	public void insert(UserInfoDetails UID) throws SQLException;
	public UserInfoDetails getInfoDetailsByid(Integer id) throws SQLException;
	public void update(int userid,String item,int num) throws SQLException;
	public void updateWhenTrade(int userid, int price) throws SQLException;
}
