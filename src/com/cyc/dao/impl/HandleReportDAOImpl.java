package com.cyc.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.cyc.dao.HandleReportDAO;
import com.cyc.entity.HandleReport;
import com.cyc.utils.DButils;

public class HandleReportDAOImpl implements HandleReportDAO {
	private DButils DB;
	public HandleReportDAOImpl() {
		DB = new DButils();
	}
	@Override
	public void create(HandleReport hr) throws SQLException {
		// TODO �Զ����ɵķ������
		String sql = "insert into handlereport(reportsuccess, informeruserid, informerusername, reason, remarkbyinformer, "
				+ "processingstaff, remarkbystaff, reporttime, handletime,violationhandleid) values(?,?,?,?,?,?,?,?,?,?)";
		DB.update(sql, hr.isReportsuccess(),hr.getInformeruserid(),hr.getInformerusername(),hr.getReason(),hr.getRemarkbyinformer(),
				hr.getProcessingstaff(),hr.getRemarkbystaff(),hr.getReporttime(),hr.getHandletime(),hr.getViolationhandleid());
	}

	@Override
	public List<HandleReport> getAll(int page) throws SQLException {
		// TODO �Զ����ɵķ������
		return null;
	}

}
