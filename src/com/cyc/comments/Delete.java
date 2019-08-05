package com.cyc.comments;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyc.dao.impl.CommentsDAOImpl;

public class Delete extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		try {
			CommentsDAOImpl CDI = new CommentsDAOImpl();
				int id = Integer.parseInt(req.getParameter("id"));
				CDI.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			out.print(false);
		}
		out.print(true);
	}
}
