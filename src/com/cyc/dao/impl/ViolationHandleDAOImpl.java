package com.cyc.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.cyc.dao.ViolationHandleDAO;
import com.cyc.entity.ViolationHandle;
import com.cyc.utils.DButils;

public class ViolationHandleDAOImpl implements ViolationHandleDAO {
	private DButils DB;

	public ViolationHandleDAOImpl() {
		DB = new DButils();
	}

	@Override
	public int create(int publishid, String violatingcontent, int userid, String processingstaff,
			String remark, Timestamp time, String publishcontent, String migSrc) throws SQLException {
		// TODO 自动生成的方法存根
		String sql = "insert into violationhandle(publishid, violatingcontent, userid, processingstaff,remark,`time`,publishcontent, imgsrc)"
				+ "values(?,?,?,?,?,?,?,?)";
		return DB.BackIdWhenUpdate(sql, publishid,violatingcontent,userid,processingstaff,remark,time,publishcontent,migSrc);
		
	}

	@Override
	public List<ViolationHandle> getAll(int page) throws SQLException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public List<ViolationHandle> getByManageName(String name) throws SQLException {
		// TODO 自动生成的方法存根
		return null;
	}

}
