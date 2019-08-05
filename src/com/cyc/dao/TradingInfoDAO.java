package com.cyc.dao;

import java.sql.SQLException;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.cyc.entity.tradinginfo;



public interface TradingInfoDAO {
	public void CreateATrade(int publishid, int price, String introduction,String mainimgSrc, int sellerid) throws SQLException;
	public List<tradinginfo> getBysellerid(int sellerid)throws SQLException;
}
