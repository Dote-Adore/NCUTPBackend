package com.cyc.dao;

import java.sql.SQLException;
import java.util.List;

import com.cyc.entity.PublishImg;

public interface PublishImgDAO {
	public void insert(int publishid, String imgSrc, int imgIndex)throws SQLException;
	public void deleteAll(int publishid)throws SQLException;
	public List<PublishImg> selectAll(int publishid) throws SQLException;
	public void deleteOne(int publishid, int imgIndex)throws SQLException;
}
