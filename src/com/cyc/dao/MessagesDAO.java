package com.cyc.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.cyc.entity.Messages;

public interface MessagesDAO {
	public void create(int userid, int type, String title, String time, String content, String remark,
			String content2,String imgsrc, boolean hasread) throws SQLException;
	public Integer getUnreadNum(int userid);
	public List<Messages> getlist(int userid);
	public void setRead(Integer ...messageid);
	
}
