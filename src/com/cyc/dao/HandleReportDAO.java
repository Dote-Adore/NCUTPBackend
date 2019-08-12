package com.cyc.dao;

import java.sql.SQLException;
import java.util.List;

import com.cyc.entity.HandleReport;

public interface HandleReportDAO {
	public void create(HandleReport handleReport)throws SQLException;
	public List<HandleReport> getAll(int page)throws SQLException;
}
