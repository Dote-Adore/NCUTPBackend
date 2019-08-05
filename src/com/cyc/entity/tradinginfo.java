package com.cyc.entity;

import java.sql.Timestamp;

import com.alibaba.fastjson.JSONObject;

public class tradinginfo {
	private Integer id;
	private Integer publishid;
	private Integer buyerid;
	private String buyercomment;
	private String sellercomment;
	private Timestamp dealtime;
	private String mainimgsrc;
	private String introduction;
	private Integer sellerid;
	private Integer price;
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
	public Integer getBuyerid() {
		return buyerid;
	}
	public void setBuyerid(Integer buyerid) {
		this.buyerid = buyerid;
	}
	public String getBuyercomment() {
		return buyercomment;
	}
	public void setBuyercomment(String buyercomment) {
		this.buyercomment = buyercomment;
	}
	public String getSellercomment() {
		return sellercomment;
	}
	public void setSellercomment(String sellercomment) {
		this.sellercomment = sellercomment;
	}
	public Timestamp getDealtime() {
		return dealtime;
	}
	public void setDealtime(Timestamp dealtime) {
		this.dealtime = dealtime;
	}
	public String getMainimgsrc() {
		return mainimgsrc;
	}
	public void setMainimgsrc(String mainimgsrc) {
		this.mainimgsrc = mainimgsrc;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public Integer getSellerid() {
		return sellerid;
	}
	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public JSONObject toJSON() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", id);
		jsonObject.put("publishid", publishid);
		jsonObject.put("mainimgsrc", mainimgsrc);
		jsonObject.put("introduction", introduction);
		jsonObject.put("sellerid", sellerid);
		jsonObject.put("price", price);
		jsonObject.put("dealtime", dealtime);
		return jsonObject;
	}
}
