package com.cyc.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.cyc.dao.TradingInfoDAO;
import com.cyc.entity.tradinginfo;
import com.cyc.utils.DButils;

public class TradingInfoDAOImpl implements TradingInfoDAO {
	private DButils DB;
	public TradingInfoDAOImpl() {
		// TODO 自动生成的构造函数存根
		DB = new DButils();
	}
	@Override
	public void CreateATrade(int publishid, int price, String introduction, String mainimgSrc, int sellerid) throws SQLException{
		
		String sql = "insert into tradinginfo(publishid, price, introduction, mainimgsrc, sellerid, dealtime) values(?,?,?,?,?,?)";
		Timestamp time = new Timestamp(System.currentTimeMillis());
		DB.update(sql, publishid, price, introduction, mainimgSrc, sellerid,time);
		// TODO 自动生成的方法存根
	}
	@Override
	public List<tradinginfo> getBysellerid(int sellerid)throws SQLException{
		String sql = "select * from tradinginfo where sellerid = ?";
		List<tradinginfo> ti = DB.getForList(tradinginfo.class, sql, sellerid);
		return ti;
	}

}
