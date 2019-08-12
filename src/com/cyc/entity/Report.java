package com.cyc.entity;

import java.sql.Timestamp;

import com.alibaba.fastjson.JSONObject;
import com.cyc.utils.TimeUtil;

public class Report {
	private Integer id;
	private Integer informerid;
	private String informername;
	private Integer publishid;
	private String reason;
	private String remark;
	private Timestamp createtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getInformerid() {
		return informerid;
	}

	public void setInformerid(Integer informerid) {
		this.informerid = informerid;
	}

	public String getInformername() {
		return informername;
	}

	public void setInformername(String informername) {
		this.informername = informername;
	}

	public Integer getPublishid() {
		return publishid;
	}

	public void setPublishid(Integer publishid) {
		this.publishid = publishid;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public JSONObject toJSON() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", id);
		jsonObject.put("informerid", informerid);
		jsonObject.put("informername", informername);
		jsonObject.put("publishid", publishid);
		jsonObject.put("reason", reason);
		jsonObject.put("remark", remark);
		jsonObject.put("createtime", TimeUtil.getFormatTime(createtime));
		return jsonObject;
	}
	
}
