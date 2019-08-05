package com.cyc.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.cyc.dao.impl.UserInfoDAOImpl;

public class getOpenId extends HttpServlet {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	private String code;

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		
	    response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out = response.getWriter();
        Enumeration<String> parNames = request.getParameterNames();
        while (parNames.hasMoreElements()) {
        	String paramString = (String)parNames.nextElement();
        	code = request.getParameter(paramString);
        }
        StringBuilder result = toWxApi.getresult(code);	//这里将获得openid
        
        int startindex = result.indexOf("openid\":")+9;//获取位置
        int endindex = result.indexOf("\"",startindex);
        String openid = result.substring(startindex,endindex);
        System.out.println("openid:"+openid);
        UserInfoDAOImpl userInfo = new UserInfoDAOImpl();
        try {
			if(!userInfo.queryByOpenId(openid)) {	//如果不存在这个账号，响应不存在此账号，返回openid
				JSONObject jsonstr = new JSONObject();
				jsonstr.put("hasAccount", false);
				jsonstr.put("openid", openid);
				out.print(jsonstr);
			}
			else {
				int userid = userInfo.getID();
				JSONObject jsonstr = new JSONObject();
				jsonstr.put("hasAccount",true);
				jsonstr.put("id", userid);
				out.print(jsonstr);
				System.out.println("This account has already registered!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
