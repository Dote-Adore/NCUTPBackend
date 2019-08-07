package com.cyc.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.cyc.dao.managepersonDAO;
import com.cyc.entity.ManagePerson;
import com.cyc.utils.DButils;

public class ManagePersonDAOImpl implements managepersonDAO {
	private DButils DB;
	public ManagePersonDAOImpl() {
		// TODO 自动生成的构造函数存根
		DB = new DButils();
	}
	@Override
	public boolean login(String user, String password) throws SQLException {
		String sql = "select * from manageperson where binary user = ? and password = ?";
		if(DB.getCount(sql, user, password)>0)
			return true;
		else 
			return false;
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void addPerson(String user, String password) throws SQLException {
		// TODO 自动生成的方法存根

	}
	@Override
	public void updateSession(String username, String session) throws SQLException {
		String sql = "update manageperson set session = ? where user = ?";
		DB.update(sql, session, username);
		// TODO 自动生成的方法存根
		
	}
	@Override
	public String getusername(String session) throws SQLException {
		String sql = "select * from manageperson where session = ?";
		List<ManagePerson> managePersons = DB.getForList(ManagePerson.class, sql, session);
		return managePersons.get(0).getUser();
	}
}
