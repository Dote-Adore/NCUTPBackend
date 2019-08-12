package com.cyc.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.cyc.dao.PublishDetailDAO;
import com.cyc.entity.PublishDetail;
import com.cyc.utils.DButils;

public class PublishDetailDAOImpl implements PublishDetailDAO {
	
	private DButils DB;
	public PublishDetailDAOImpl() {
		DB = new DButils();
		// TODO 自动生成的构造函数存根
	}
	
	@Override
	public int createAPublishinfo(PublishDetail PD) throws SQLException {
		// TODO 自动生成的方法存根
		String sql = "insert into publishdetail(userid,commontags,price,introduction,publishtime,categoryid) values(?,?,?,?,?,?)";
		int id = DB.BackIdWhenUpdate(sql, PD.getUserid(),PD.getCommontags(),PD.getPrice(),
				PD.getIntroduction(),PD.getPublishtime(),PD.getCategoryid());
		return id;
	}

	@Override
	public void setMainImg(int publishid, String imgSrc) throws SQLException {
		String sql = "update publishdetail set mainimgsrc=? where id = ?";
		DB.update(sql, imgSrc,publishid);

	}
	
	@Override
	public void changeDetails(PublishDetail PD)throws SQLException{
		String sql = "update publishdetail SET commontags= ?, price= ?, introduction= ? , publishtime= ? , categoryid= ? where id = ?";
		DB.update(sql,PD.getCommontags(),PD.getPrice(),
				PD.getIntroduction(),PD.getPublishtime(),PD.getCategoryid(),PD.getId());
	}

	@Override
	public void delete(int publishID) throws SQLException {
		String sql = "delete from publishdetail where id = ?";
		DB.update(sql, publishID);

	}

	@Override
	public List<PublishDetail> examineAll(int page) throws SQLException{
		// TODO 自动生成的方法存根
		int start = page*15;
		String sql = "select * from publishdetail order by id desc limit "+start+",15";
		return DB.getForList(PublishDetail.class, sql);
	}
	
	public List<PublishDetail> exmineByUserID(int userid)throws SQLException{
		String sql = "select * from publishdetail where userid = ?";
		return DB.getForList(PublishDetail.class, sql, userid);
		
	}
	
	
	@Override
	public List<PublishDetail> examineBySearch(String Searchstr,int page)throws SQLException {
		//吧空格全部去掉
		Searchstr = Searchstr.replaceAll(" ", "");
		String sql = "select * from publishdetail where introduction like '%"+Searchstr+"%' order by id desc limit "+page*15+",15";
		List<PublishDetail> pdList = DB.getForList(PublishDetail.class, sql);
		return pdList;
	}
	
	@Override
	public PublishDetail getpublDetailsByID(int publishID) {
		// TODO 自动生成的方法存根
		String sql = "select * from publishdetail where id = ?";
		PublishDetail detail = DB.get(PublishDetail.class, sql, publishID);
		return detail;
	}

	@Override
	public int getNumOfPublish(int userid) {
		String sql = "select count(*) from publishdetail where userid = ?";
		int i = DB.getCount(sql, userid);
		return i;
	}
	@Override
	public List<PublishDetail> examineByCatagroyid(int id, int page) {
		int start = page*15;
		String sql = "select * from publishdetail where categoryid = ? order by id desc limit "+start+",15";
		
		return DB.getForList(PublishDetail.class, sql, id);
	}
}
