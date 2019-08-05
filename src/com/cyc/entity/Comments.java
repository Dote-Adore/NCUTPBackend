package com.cyc.entity;

import java.sql.Timestamp;
import com.cyc.utils.TimeDifference;
import com.alibaba.fastjson.JSONObject;

public class Comments {
	private Integer id;
	private Integer publishid;
	private Integer userid;
	private String content;
	private Timestamp time;
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
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	
	public JSONObject toJSON() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", id);
		jsonObject.put("publishid",publishid );
		jsonObject.put("userid",userid );
		jsonObject.put("content",content );
		jsonObject.put("time",TimeDifference.Timediff(time));
		return jsonObject;
	}
}
