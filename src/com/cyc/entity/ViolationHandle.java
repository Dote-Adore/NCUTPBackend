package com.cyc.entity;

import java.sql.Timestamp;

import com.alibaba.fastjson.JSONObject;
import com.cyc.utils.TimeUtil;

public class ViolationHandle {
	private Integer id;
	private Integer publishid;
	private String violatingcontent;
	private Integer userid;
	private String processingstaff;
	private String remark;
	private Timestamp time;
	private String publishcontent;
	private String imgsrc;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPublishid() {
		return publishid;
	}
	public void setPublishid(Integer publishid) {
		this.publishid = publishid;
	}
	public String getViolatingcontent() {
		return violatingcontent;
	}
	public void setViolatingcontent(String violatingcontent) {
		this.violatingcontent = violatingcontent;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getProcessingstaff() {
		return processingstaff;
	}
	public void setProcessingstaff(String processingstaff) {
		this.processingstaff = processingstaff;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getPublishcontent() {
		return publishcontent;
	}
	public void setPublishcontent(String publishcontent) {
		this.publishcontent = publishcontent;
	}
	public String getImgsrc() {
		return imgsrc;
	}
	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}
	
	
	public JSONObject toJSON() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id",id );
		jsonObject.put("publishid",publishid );
		jsonObject.put("violatingcontent",violatingcontent );
		jsonObject.put("userid",userid );
		jsonObject.put("processingstaff",processingstaff );
		jsonObject.put("remark",remark );
		jsonObject.put("time", TimeUtil.getFormatTime(time));
		jsonObject.put("publishcontent",publishcontent );
		jsonObject.put("imgsrc",imgsrc );
		return jsonObject;
	}
}
