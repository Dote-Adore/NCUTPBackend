package com.cyc.dao;

import java.sql.SQLException;
import java.util.List;

import com.cyc.entity.PublishDetail;

public interface PublishDetailDAO {
	public int createAPublishinfo(PublishDetail PD)throws SQLException;
	public void setMainImg(int publishid, String imgSrc)throws SQLException;
	public void changeDetails(PublishDetail PD)throws SQLException;
	public void delete(int publishID)throws SQLException;
	public List<PublishDetail> examineAll(int page)throws SQLException;
	public List<PublishDetail> examineBySearch(String Searchstr,int page)throws SQLException;
	public List<PublishDetail> examineByCatagroyid(int categoryid,int page);
	public PublishDetail getpublDetailsByID(int publishID);
	public List<PublishDetail> exmineByUserID(int userid)throws SQLException;
	public int getNumOfPublish(int userid);
}
