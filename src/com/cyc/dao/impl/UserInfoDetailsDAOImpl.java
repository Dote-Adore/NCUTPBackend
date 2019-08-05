package com.cyc.dao.impl;

import java.sql.SQLException;

import com.cyc.dao.UserInfoDetailsDAO;
import com.cyc.entity.UserInfoDetails;
import com.cyc.utils.DButils;


public class UserInfoDetailsDAOImpl implements UserInfoDetailsDAO {

	DButils DB = new DButils();
	@Override
	public void insert(UserInfoDetails UID) throws SQLException {
		// TODO 自动生成的方法存根
		String sql = "insert into userinfodetails(id,havepublishednum,dealdonenum,collectionnum,sellingtotalprice,purchasednum) values(?,?,?,?,?,?)";
		DButils myDB = new DButils();
		myDB.update(sql, UID.getId(),UID.getHavepublishednum(),UID.getDealdonenum(),
				UID.getCollectionnum(),UID.getSellingtotalprice(),UID.getPurchasednum());
		System.out.println("success insert User:"+UID.getId()+"  details into the Userinfodetails Table!");
	}

	@Override
	public UserInfoDetails getInfoDetailsByid(Integer id) throws SQLException {
		String sql = "select * from userinfodetails where id = ?";
		UserInfoDetails UID = DB.get(UserInfoDetails.class, sql, id);
		System.out.println("success to find user:"+id+"  details infomations!");
		// TODO 自动生成的方法存根
		return UID;
	}

	@Override
	public void update(int userid,String item,int num) throws SQLException {
		// TODO 自动生成的方法存根
		String sql = "update userinfodetails set "+item+"=? where id=?";
		DB.update(sql,num,userid);
		
	}
	public void updateWhenTrade(int userid, int price) throws SQLException {
		String sql = "update userinfodetails set sellingtotalprice = sellingtotalprice + ?, havepublishednum=havepublishednum-1, dealdonenum = dealdonenum +1 where id = ?";
		DB.update(sql, price,userid);
	}
}
