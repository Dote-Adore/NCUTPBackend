package com.cyc.entity;

import com.alibaba.fastjson.JSONObject;

public class UserInfoDetails {
	private Integer id;
	private Integer havepublishednum;
	private Integer dealdonenum;
	private Integer collectionnum;
	private Integer sellingtotalprice;
	private Integer purchasednum;
	public Integer getPurchasednum() {
		return purchasednum;
	}
	public void setPurchasednum(Integer purchasednum) {
		this.purchasednum = purchasednum;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getHavepublishednum() {
		return havepublishednum;
	}
	public void setHavepublishednum(Integer havepublishednum) {
		this.havepublishednum = havepublishednum;
	}
	public Integer getDealdonenum() {
		return dealdonenum;
	}
	public void setDealdonenum(Integer dealdonenum) {
		this.dealdonenum = dealdonenum;
	}
	public Integer getCollectionnum() {
		return collectionnum;
	}
	public void setCollectionnum(Integer collectionnum) {
		this.collectionnum = collectionnum;
	}
	public Integer getSellingtotalprice() {
		return sellingtotalprice;
	}
	public void setSellingtotalprice(Integer sellingtotalprice) {
		this.sellingtotalprice = sellingtotalprice;
	}
	public JSONObject toJson() {
		JSONObject jsonstr = new JSONObject();
		jsonstr.put("collectionNum",collectionnum);
		jsonstr.put("dealDoneNum", dealdonenum);
		jsonstr.put("havePublishedNum",havepublishednum);
		jsonstr.put("sellingTotalPrice", sellingtotalprice);
		jsonstr.put("purchasedNum", purchasednum);
		return jsonstr;
	}
}
