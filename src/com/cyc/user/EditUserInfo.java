package com.cyc.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyc.dao.impl.UserInfoDAOImpl;

public class EditUserInfo extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("edit user info");
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		int userid = 0;
		userid = Integer.parseInt(req.getParameter("userid"));
		if (userid != 0) {
			String method = req.getParameter("method");
			try {
				switch(method) {
				case "changeName":{
					updateName(req, resp, userid);
					break;
				}
				case "changeAvatar":{
					updateAvatar(req, resp, userid);
					break;
				}
				case "changeElse":{
					int area = Integer.parseInt(req.getParameter("area"));
					String schoolid = req.getParameter("schoolid");
					String phonenum = req.getParameter("phonenum");
					String enrollmentyear = req.getParameter("enrollmentyear");
					UserInfoDAOImpl UIDI = new UserInfoDAOImpl();
					UIDI.editUserInfo(userid, area, schoolid, phonenum, enrollmentyear);
				}
				}
				

				out.print("success");
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			out.print("fail!");
		}
	}

	private void updateAvatar(HttpServletRequest req, HttpServletResponse resp, int userid) throws SQLException {
		System.out.println("change avatar!");
		UserInfoDAOImpl UIDI = new UserInfoDAOImpl();
		String avatar = req.getParameter("avatar");
		UIDI.editUserInfo(userid, "avatar", avatar);
	}

	private void updateName(HttpServletRequest req, HttpServletResponse resp, int userid) throws SQLException {
		System.out.println("change name!");
		UserInfoDAOImpl UIDI = new UserInfoDAOImpl();
		String name = req.getParameter("name");
		UIDI.editUserInfo(userid, "name", name);
	}

}
