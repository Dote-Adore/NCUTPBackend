package com.cyc.dao;

import java.sql.SQLException;
import java.util.List;

import com.cyc.entity.Comments;

public interface CommentsDAO {
	public void insert(int publishid, int userid, String content, int resqid)throws SQLException;
	public void delete(int id)throws SQLException;
	public List<Comments> getCommentsBypublishid(int publishid)throws SQLException;
	public void deletebypublishid(int publishid)throws SQLException;
	public int getuserid(int id)throws SQLException;
}
