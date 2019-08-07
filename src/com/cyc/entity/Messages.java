package com.cyc.entity;

import com.alibaba.fastjson.JSONObject;

public class Messages {
	private Integer id;
	private Integer userid;
	private Integer type;
	private String title;
	private String time;
	private String content;
	private String remark;
	private String content2;
	private String imgsrc;
	private boolean hasread;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getContent2() {
		return content2;
	}
	public void setContent2(String content2) {
		this.content2 = content2;
	}
	public String getImgsrc() {
		return imgsrc;
	}
	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}
	public boolean isHasread() {
		return hasread;
	}
	public void setHasread(boolean hasread) {
		this.hasread = hasread;
	}
	public JSONObject tojson() {
		JSONObject js = new JSONObject();
		js.put("id", id);
		js.put("userid",userid );
		js.put("type",type );
		js.put("title",title );
		js.put("time",time );
		js.put("content",content );
		js.put("remark",remark );
		js.put("content2", content2);
		js.put("imgsrc",imgsrc);
		js.put("hasRead",hasread);
		return js;
	}
}
