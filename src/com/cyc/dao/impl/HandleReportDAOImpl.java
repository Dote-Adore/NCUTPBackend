package com.cyc.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.cyc.dao.HandleReportDAO;
import com.cyc.entity.HandleReport;
import com.cyc.utils.DButils;
import com.mysql.cj.xdevapi.DbDoc;

public class HandleReportDAOImpl implements HandleReportDAO {
	private DButils DB;
	public HandleReportDAOImpl() {
		DB = new DButils();
	}
	@Override
	public void create(HandleReport hr) throws SQLException {
		// TODO 自动生成的方法存根
		String sql = "insert into handlereport(reportsuccess, informeruserid, informerusername, reason, remarkbyinformer, "
				+ "processingstaff, remarkbystaff, reporttime, handletime,violationhandleid) values(?,?,?,?,?,?,?,?,?,?)";
		DB.update(sql, hr.isReportsuccess(),hr.getInformeruserid(),hr.getInformerusername(),hr.getReason(),hr.getRemarkbyinformer(),
				hr.getProcessingstaff(),hr.getRemarkbystaff(),hr.getReporttime(),hr.getHandletime(),hr.getViolationhandleid());
	}

	@Override
	public List<HandleReport> getAll(int page) throws SQLException {
		String sql = "select * from handlereport order by id desc limit"+page*20 +",20";
		List<HandleReport> handleReports = DB.getForList(HandleReport.class, sql);
		return handleReports;
	}
	
	@Override
	public List<HandleReport> getByManageName(String name, int page) throws SQLException {
		String sql = "select * from handlereport where processingstaff = ? order by id desc limit "+page*20 +",20";
		List<HandleReport> handleReports = DB.getForList(HandleReport.class, sql, name);
		return handleReports;
	}
	
}
