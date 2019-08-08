package com.cyc.dao.impl;

import java.sql.SQLException;
import java.util.List;




import com.cyc.dao.PublishImgDAO;
import com.cyc.entity.PublishImg;
import com.cyc.utils.DButils;


public class PublishImgDAOImpl implements PublishImgDAO {
	private DButils DB;
	public PublishImgDAOImpl() {
		DB = new DButils();
	}
	@Override
	public void insert(int publishid, String imgSrc, int imgIndex) throws SQLException {
		String sql = "INSERT INTO publishimg(`publishid`,`src`,`index`) values(?,?,?)";
		DB.update(sql, publishid,imgSrc,imgIndex);

	}

	@Override
	public void deleteAll(int publishid) throws SQLException {
		// TODO 自动生成的方法存根
		String sql = "delete from publishimg where publishid = ?";
		DB.update(sql, publishid);

	}

	@Override
	public List<PublishImg> selectAll(int publishid) throws SQLException {
		String sql ="select * from publishimg where `publishid` = ?";
		List<PublishImg> pi = DB.getForList(PublishImg.class, sql, publishid);
		return pi;
	}

	@Override
	public void deleteOne(int publishid, int imgIndex) throws SQLException {
		// TODO 自动生成的方法存根

	}

}
