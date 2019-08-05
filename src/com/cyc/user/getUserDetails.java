package com.cyc.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.cyc.dao.impl.FollowDAOImpl;
import com.cyc.dao.impl.UserInfoDAOImpl;
import com.cyc.dao.impl.UserInfoDetailsDAOImpl;
import com.cyc.entity.UserInfo;
import com.cyc.entity.UserInfoDetails;

public class getUserDetails extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		int id = Integer.parseInt(req.getParameter("id"));
		System.out.println("user:"+id+" comes to get its details");
		UserInfoDetailsDAOImpl UIDDI = new UserInfoDetailsDAOImpl();
		FollowDAOImpl FDI = new FollowDAOImpl();
		UserInfoDetails UID =null;
		try {
			UID = UIDDI.getInfoDetailsByid(id);//获取用户的收藏，信息
			int follownum = FDI.getFollowNum(id);
			int followernum = FDI.getFollowerNum(id);
			JSONObject jsonstr = new JSONObject();
			jsonstr.put("collectionNum", UID.getCollectionnum());
			jsonstr.put("dealDoneNum", UID.getDealdonenum());
			jsonstr.put("havePublishedNum", UID.getHavepublishednum());
			jsonstr.put("sellingTotalPrice", UID.getSellingtotalprice());
			jsonstr.put("purchasedNum", UID.getPurchasednum());
			jsonstr.put("followNum", follownum);
			jsonstr.put("followerNum", followernum);
			//获取用户的普通信息
			UserInfoDAOImpl UIDI = new UserInfoDAOImpl();
			UserInfo UI = UIDI.getUserInfobyID(id);
			jsonstr.put("userinfo", UI.toJson());
			
			out.print(jsonstr);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
}
