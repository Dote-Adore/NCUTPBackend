package com.cyc.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cyc.dao.CollectDAO;
import com.cyc.entity.UserCollection;
import com.cyc.utils.DButils;

public class CollectDAOImpl implements CollectDAO {
	private DButils DB;
	public CollectDAOImpl(){
		DB = new DButils();
	}
	@Override
	public void collectAGoods(int userid, int publishid) throws SQLException {
		// TODO 自动生成的方法存根
		String sql = "INSERT INTO usercollection(userid,publishid) values(?,?)";
		DB.update(sql, userid,publishid);
	}

	@Override
	public void cancelCollect(int userid, int publishid) throws SQLException {
		// TODO 自动生成的方法存根
		String sql = "DELETE FROM usercollection where userid=? and publishid=?";
		DB.update(sql, userid,publishid);
	}

	@Override
	public boolean isCollectioned(int userid, int publishid) throws SQLException {
		String sql = "SELECT count(*) FROM usercollection where userid =? and publishid=?";
		int count = DB.getCount(sql, userid,publishid);
		if (count>0)
			return true;
		else 
			return false;
	}

	@Override
	public List<Integer> getAllCollections(int userid) throws SQLException {
		String sql = " SELECT * FROM usercollection WHERE userid = ?";
		List<UserCollection> list = DB.getForList(UserCollection.class, sql, userid);
		List<Integer> listInt = new ArrayList<Integer>();
		for(int i = 0;i<list.size();i++) {
			listInt.add(list.get(i).getPublishid());
		}
		return listInt;
	}
	@Override
	public int getCount(int userid) throws SQLException {
		String sql = "select count(*) from usercollection where userid = ?";
		int count = DB.getCount(sql, userid);
		return count;
	}
}
