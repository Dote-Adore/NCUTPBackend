package com.cyc.entity;

import java.sql.Timestamp;

public class HandleReport {
	private Integer id;
	private boolean reportsuccess;
	private int informeruserid;
	private String informerusername;
	private String reason;
	private String remarkbyinformer;
	private String processingstaff;
	private String remarkbystaff;
	private String reporttime;
	private Timestamp handletime;
	private Integer violationhandleid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public boolean isReportsuccess() {
		return reportsuccess;
	}
	public void setReportsuccess(boolean reportsuccess) {
		this.reportsuccess = reportsuccess;
	}
	public int getInformeruserid() {
		return informeruserid;
	}
	public void setInformeruserid(int informeruserid) {
		this.informeruserid = informeruserid;
	}
	public String getInformerusername() {
		return informerusername;
	}
	public void setInformerusername(String informerusername) {
		this.informerusername = informerusername;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getRemarkbyinformer() {
		return remarkbyinformer;
	}
	public void setRemarkbyinformer(String remarkbyinformer) {
		this.remarkbyinformer = remarkbyinformer;
	}
	public String getProcessingstaff() {
		return processingstaff;
	}
	public void setProcessingstaff(String processingstaff) {
		this.processingstaff = processingstaff;
	}
	public String getRemarkbystaff() {
		return remarkbystaff;
	}
	public void setRemarkbystaff(String remarkbystaff) {
		this.remarkbystaff = remarkbystaff;
	}
	public String getReporttime() {
		return reporttime;
	}
	public void setReporttime(String reporttime) {
		this.reporttime = reporttime;
	}
	public Timestamp getHandletime() {
		return handletime;
	}
	public void setHandletime(Timestamp handletime) {
		this.handletime = handletime;
	}
	public Integer getViolationhandleid() {
		return violationhandleid;
	}
	public void setViolationhandleid(Integer violationhandleid) {
		this.violationhandleid = violationhandleid;
	}
	
}
