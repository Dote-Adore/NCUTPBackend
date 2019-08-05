package com.cyc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.cyc.dao.FollowDAO;
import com.cyc.utils.DButils;

public class FollowDAOImpl implements FollowDAO {
	private DButils DB = null;
	public FollowDAOImpl() {
		DB = new DButils(); 
		
	}
	@Override
	public int getFollowNum(int userid) throws SQLException {
		// TODO 自动生成的方法存根
		String sql = "select count(*) from follow where userid = ?";
		Connection conn = DB.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, userid);
		ResultSet rs = ps.executeQuery();
		int num = 0;
		if(rs.next()) {
			num = rs.getInt(1);
		}
		return num;
	}

	@Override
	public int getFollowerNum(int userid) throws Exception {
		String sql = "select count(*) from follow where followid = ?";
		Connection conn = DB.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, userid);
		ResultSet rs = ps.executeQuery();
		int num = 0;
		if(rs.next()) {
			num = rs.getInt(1);
		}
		return num;
	}

	@Override
	public List<Integer> getFollows(int userid) throws Exception {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public List<Integer> getFollowers(int userid) throws Exception {
		// TODO 自动生成的方法存根
		return null;
	}

}
