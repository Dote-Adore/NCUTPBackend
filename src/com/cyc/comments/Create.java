package com.cyc.comments;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyc.dao.impl.CommentsDAOImpl;

public class Create extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		try {
			int publishid = Integer.parseInt(req.getParameter("publishid"));
			int userid = Integer.parseInt(req.getParameter("userid"));
			String content = req.getParameter("content");
			CommentsDAOImpl CDI = new CommentsDAOImpl();
			CDI.insert(publishid, userid, content);
			out.print("successInsert");
		} catch (Exception e) {
			// TODO: handle exception
			out.print("failInsert");
		}
	}
}
