package com.cyc.dao;

import java.sql.SQLException;
import java.util.List;

public interface CollectDAO {
	public void collectAGoods(int userid, int publishid)throws SQLException;
	public void cancelCollect(int userid, int publishid)throws SQLException;
	public boolean isCollectioned(int userid, int publishid)throws SQLException;
	public List<Integer> getAllCollections(int userid)throws SQLException;
	public int getCount(int userid)throws SQLException;
}
