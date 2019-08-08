package com.cyc.message;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.cyc.dao.impl.MessagesDAOImpl;

public class GetUnreadNum extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		Integer userid= Integer.parseInt(req.getParameter("userid"));
		System.out.println("user: "+userid+" comes to get unread num...");
		try {
			MessagesDAOImpl MDI = new MessagesDAOImpl();
			int unreadnum = MDI.getUnreadNum(userid);
			JSONObject json = new JSONObject();
			json.put("success", true);
			json.put("unreadnum", unreadnum);
			out.print(json);
			out.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			JSONObject json = new JSONObject();
			json.put("success", false);
			out.print(json);
			out.close();
		}
	}
}
