package com.cyc.dao;


import java.sql.SQLException;

import com.cyc.entity.UserInfo;

public interface UserInfoDAO {
	public void insert(UserInfo userinfo) throws Exception;
	public void update(UserInfo userinfo) throws Exception;
	public void delete(UserInfo userinfo) throws Exception;
	public boolean queryByOpenId(String openid) throws SQLException;
	public UserInfo getUserInfobyID(int id)throws SQLException;
	public void editUserInfo(int userid, int areaid, String schoolid, String phonenum, String enrollmentyear)throws SQLException;
	public void editUserInfo(int userid, String item, String value)throws SQLException;
}
