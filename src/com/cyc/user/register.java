package com.cyc.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.cyc.dao.impl.UserInfoDAOImpl;
import com.cyc.dao.impl.UserInfoDetailsDAOImpl;
import com.cyc.entity.UserInfo;
import com.cyc.entity.UserInfoDetails;

public class Register extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		System.out.println("register...");
	    resp.setContentType("text/html;charset=UTF-8");
	    PrintWriter out = resp.getWriter();
        String nickname = req.getParameter("nickName");
        String avatar = req.getParameter("avatar");
        String openid = req.getParameter("openid");
        System.out.println("nickname: "+nickname+"; avatar: "+avatar+"; openid: "+openid);
        if(nickname!=null&&avatar!=null&&openid!=null) {
        	UserInfo ui = new UserInfo();
        	ui.setAvatar(avatar);
        	ui.setOpenid(openid);
        	ui.setName(nickname);
        	ui.setRegistrationtime(new Timestamp(System.currentTimeMillis()));
        	UserInfoDAOImpl uidImpl = new UserInfoDAOImpl();
        	try {
				uidImpl.insert(ui);//插入一条新用户数据
				int ID = uidImpl.getId(ui);
	        	out.print("{\"register\":true, \"id\":"+ID+"}");
	        	UserInfoDetailsDAOImpl UIDDI = new UserInfoDetailsDAOImpl();
	        	UserInfoDetails UID = new UserInfoDetails();
	        	instanceUID(UID, ID);
	        	UIDDI.insert(UID);
	        	System.out.println("success register");
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
        }   
	}
	
	public void instanceUID(UserInfoDetails UID,Integer ID) {
		UID.setId(ID);
		UID.setCollectionnum(0);
		UID.setDealdonenum(0);
		UID.setHavepublishednum(0);
		UID.setSellingtotalprice(0);
		UID.setPurchasednum(0);
		
	}
}
