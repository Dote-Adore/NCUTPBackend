package com.cyc.entity;

import java.sql.Timestamp;
import com.cyc.utils.TimeUtil;
import com.alibaba.fastjson.JSONObject;


public class PublishDetail {
	private Integer id;
	private Integer userid;
	private String commontags;
	private Integer price;
	private String introduction;
	private Timestamp publishtime;
	private Integer categoryid;
	private String mainimgsrc;
	public String getMainimgsrc() {
		return mainimgsrc;
	}
	public void setMainimgsrc(String mainimgsrc) {
		this.mainimgsrc = mainimgsrc;
	}
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
	public String getCommontags() {
		return commontags;
	}
	public void setCommontags(String commontags) {
		this.commontags = commontags;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public Timestamp getPublishtime() {
		return publishtime;
	}
	public void setPublishtime(Timestamp publishtime) {
		this.publishtime = publishtime;
	}
	public Integer getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}
	public String getMainingsrc() {
		return mainimgsrc;
	}
	public void setMainingsrc(String mainingsrc) {
		this.mainimgsrc = mainingsrc;
	}
	
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("userid", userid);
		json.put("commontags", commontags);
		json.put("price", price);
		json.put("introduction", introduction);
		json.put("publishtime", TimeUtil.Timediff(publishtime));
		json.put("exacttime", publishtime);
		json.put("categoryid", categoryid);
		json.put("mainimgsrc", mainimgsrc);
		return json;
	}
}
