package com.cyc.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.cyc.dao.MessagesDAO;
import com.cyc.entity.Messages;
import com.cyc.utils.DButils;

public class MessagesDAOImpl implements MessagesDAO {
	private DButils DB;
	public MessagesDAOImpl() {
		DB = new DButils();
	}
	@Override
	public void create(int userid, int type, String title, String time, String content, String remark, String content2,
			String imgsrc, boolean hasread) throws SQLException {
		String sql = "insert into messages(userid, type, title, time, content, remark, content2, imgsrc, hasread) values(?,?,?,?,?,?,?,?,?)";
		DB.update(sql, userid,type,title,time,content,remark,content2,imgsrc,hasread);
	}

	@Override
	public Integer getUnreadNum(int userid) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public List<Messages> getlist(int userid) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public void setRead(Integer... messageid) {
		// TODO 自动生成的方法存根

	}

}
