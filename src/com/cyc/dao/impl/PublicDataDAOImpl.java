package com.cyc.dao.impl;

import java.sql.SQLException;

import com.cyc.dao.PublicDataDAO;
import com.cyc.utils.DButils;

public class PublicDataDAOImpl implements PublicDataDAO {
	private DButils DB;
	public PublicDataDAOImpl() {
		DB = new DButils();
	}
	@Override
	public void UpdateToken(String token) throws SQLException {
		// TODO 自动生成的方法存根
		String sql = "update publicdata set value=? where name='access_token'";
		DB.update(sql, token);
	}

	@Override
	public String getToken() throws SQLException {
		// TODO 自动生成的方法存根
		return null;
	}

}
