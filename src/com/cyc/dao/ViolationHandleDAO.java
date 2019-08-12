package com.cyc.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.cyc.entity.ViolationHandle;

public interface ViolationHandleDAO {
	public int create(int publishid, String violatingcontent, int userid, String processingstaff,
			String remark, Timestamp time, String publishcontent, String migSrc) throws SQLException ;
	public List<ViolationHandle> getAll(int page)throws SQLException;
	public List<ViolationHandle> getByManageName(String name)throws SQLException;
	
}
