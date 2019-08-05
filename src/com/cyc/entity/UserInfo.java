package com.cyc.entity;

import java.sql.Timestamp;

import com.alibaba.fastjson.JSONObject;

public class UserInfo {
	private Integer id = 0;
	private String schoolid ="";
	private String openid="";
	private Integer enrollmentyear=0;
	private String phonenumber="";
	private Integer area=0;
	private String avatar="";
	private String name="";
	private Timestamp registrationtime=new Timestamp(0);
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserInfo() {
		// TODO 自动生成的构造函数存根
	}
	public String getSchoolid() {
		return schoolid;
	}
	public void setSchoolid(String schoolid) {
		this.schoolid = schoolid;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public Integer getEnrollmentyear() {
		return enrollmentyear;
	}
	public void setEnrollmentyear(Integer enrollmentyear) {
		this.enrollmentyear = enrollmentyear;
	}
	public String getPhonebnumber() {
		return phonenumber;
	}
	public void setPhonebnumber(String phonebnumber) {
		this.phonenumber = phonebnumber;
	}
	public Integer getArea() {
		return area;
	}
	public void setArea(Integer area) {
		this.area = area;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getRegistrationtime() {
		return registrationtime;
	}
	public void setRegistrationtime(Timestamp registrationtime) {
		this.registrationtime = registrationtime;
	}
	
	
	public JSONObject toJson() {
		JSONObject jsonstr=  new JSONObject();
		jsonstr.put("id",id);
		jsonstr.put("schoolid",schoolid);
		jsonstr.put("enrollmentyear",enrollmentyear);
		jsonstr.put("phonenumber", phonenumber);
		jsonstr.put("area", area);
		jsonstr.put("name", name);
		jsonstr.put("registrationtime", registrationtime);
		jsonstr.put("avatar", avatar);
		return jsonstr;
	}
	
}
