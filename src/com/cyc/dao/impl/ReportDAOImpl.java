package com.cyc.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.cyc.dao.ReportDAO;
import com.cyc.entity.Report;
import com.cyc.utils.DButils;

public class ReportDAOImpl implements ReportDAO {
	private DButils DB;
	public ReportDAOImpl() {
		DB = new DButils();
	}
	@Override
	public void create(Report report) throws SQLException {
		// TODO 自动生成的方法存根
		String sql = "insert into report(informerid,informername,publishid,reason,remark,createtime) values(?,?,?,?,?,?)";
		DB.update(sql, report.getInformerid(),report.getInformername(),report.getPublishid(),
				report.getReason(),report.getRemark(),report.getCreatetime());
		
	}
	
	@Override
	public int getCount(int publishid)throws SQLException {
		String sql = "select count(*) from report where publishid = ?";
		return DB.getCount(sql, publishid);
	}
	
	@Override
	public List<Report> getAll(int page) throws SQLException {
		String sql = "select * from report order by id desc limit " + page*15 + ",15";
		return DB.getForList(Report.class, sql);
	}

	@Override
	public List<Report> getByStaff(String manageName, int page) throws SQLException {
		String sql = "select * from report where processingstaff = ? order by id desc limit " + page*15 + ",15";
		return DB.getForList(Report.class, sql, manageName);
	}

	@Override
	public List<Report> getByUnhandled(int page) throws SQLException {
		String sql = "select * from report where hashandled = false order by id des limit " + page*15 + ",15";
		return DB.getForList(Report.class, sql);
	}
	
	@Override
	public void delete(int id)throws SQLException {
		String sql = "delete from report where id = ?";
		DB.update(sql, id);
	}
}
