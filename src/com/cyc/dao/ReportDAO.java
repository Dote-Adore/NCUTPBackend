package com.cyc.dao;

import java.sql.SQLException;
import java.util.List;

import com.cyc.entity.Report;

public interface ReportDAO {
	public void create(Report report) throws SQLException;
	public int getCount(int publishid)throws SQLException;
	public List<Report> getAll(int page) throws SQLException;
	public List<Report> getByStaff(String manageName, int page) throws SQLException;
	public List<Report> getByUnhandled(int page)throws SQLException;
	public void delete(int id)throws SQLException;
	
}
