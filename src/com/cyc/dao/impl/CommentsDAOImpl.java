package com.cyc.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.cyc.dao.CommentsDAO;
import com.cyc.entity.Comments;
import com.cyc.utils.DButils;

public class CommentsDAOImpl implements CommentsDAO {

	private DButils DB;
	public CommentsDAOImpl() {
		DB = new DButils();
	}
	@Override
	public void insert(int publishid, int userid, String content, int resqid) throws SQLException {
		String sql = "insert into comments(publishid,userid,content,time,respid) values(?,?,?,?,?)";
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		DB.update(sql, publishid,userid,content,ts,resqid);
	}

	@Override
	public void delete(int id) throws SQLException {
		// TODO 自动生成的方法存根
		String sql = "delete from comments where id = ?";
		DB.update(sql, id);
	}

	@Override
	public List<Comments> getCommentsBypublishid(int publishid) throws SQLException {
		// TODO 自动生成的方法存根
		String sql = "select * from comments where publishid = ?";
		return DB.getForList(Comments.class, sql, publishid);
	}

	@Override
	public void deletebypublishid(int publishid) throws SQLException {
		// TODO 自动生成的方法存根
		String sql = "delete from comments where publishid = ?";
		DB.update(sql, publishid);
		
	}
	public int getuserid(int id)throws SQLException{
		String sql = "select * from comments where id = ?";
		Comments comments = DB.get(Comments.class, sql, id);
		return comments.getUserid();
	}

}
