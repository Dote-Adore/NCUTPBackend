package com.cyc.dao.impl;

import java.sql.SQLException;

import com.cyc.dao.PublichDataDAO;
import com.cyc.utils.DButils;

public class PublicDataDAOImpl implements PublichDataDAO {
	private DButils DB;
	public PublicDataDAOImpl() {
		DB = new DButils();
	}
	@Override
	public void UpdateToken(String token) throws SQLException {
		// TODO �Զ����ɵķ������
		String sql = "update publicdata set value=? where name='access_token'";
		DB.update(sql, token);
	}

	@Override
	public String getToken() throws SQLException {
		// TODO �Զ����ɵķ������
		return null;
	}

}
