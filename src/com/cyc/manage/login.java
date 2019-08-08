package com.cyc.manage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.cyc.dao.impl.ManagePersonDAOImpl;

public class login extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		System.out.println("有用户正在登录...");
		resp.setContentType("text/html;charset=UTF-8");
		String sessionstr = req.getParameter("session");
		// 如果存在session
		System.out.println(sessionstr);
		if(sessionstr!=null) {
			autologin(req, resp, sessionstr);
			return;
		}
		// 获取用户名和密码
		try {
			PrintWriter out = resp.getWriter();
			String user = req.getParameter("user");
			String password = req.getParameter("password");
			ManagePersonDAOImpl MPDI = new ManagePersonDAOImpl();
			JSONObject res = new JSONObject();
			if (MPDI.login(user, password)) {
				System.out.println("成功登录");
				res.put("success", true);
				HttpSession session = req.getSession(true);
				// 更新session
				System.out.print("session:"+session.getId()+"; username:"+user);
				MPDI.updateSession(user, session.getId());
				res.put("session", session.getId());
			} else {
				System.out.println("登录失败");
				res.put("success", false);
			}
			out.print(res);
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}

	}
	private void autologin(HttpServletRequest req, HttpServletResponse resp, String session) throws IOException {

		PrintWriter out = resp.getWriter();
		try {
			ManagePersonDAOImpl MPDI = new ManagePersonDAOImpl();
			JSONObject jsonObject = new JSONObject();
			String username = MPDI.getusername(session);
			if(username==null) {
				System.out.println("自动登录失败，未找到session");
				jsonObject.put("success", false);
				out.print(jsonObject);
				return;
			}
			
			System.out.println("用户: "+username+"自动登录...");
			jsonObject.put("user", username);
			jsonObject.put("success", true);
			out.print(jsonObject);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
