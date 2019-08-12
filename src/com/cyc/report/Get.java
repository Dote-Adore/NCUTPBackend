package com.cyc.report;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.cyc.dao.impl.ReportDAOImpl;
import com.cyc.entity.Report;

public class Get extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PrintWriter out;
	private ReportDAOImpl RDI;
	private Integer page;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=UTF-8");
		out = resp.getWriter();
		page = Integer.parseInt(req.getParameter("page"));
		RDI = new ReportDAOImpl();
		String method = req.getParameter("method");
		switch(method) {
		case "getall": getall(req, resp);break;
		}
	}
	private void getall(HttpServletRequest req, HttpServletResponse resp) {
		try {
			List<Report> reportlList = RDI.getAll(page);
			JSONArray jsonArray = new JSONArray();
			for(int i = 0; i<reportlList.size(); i++) {
				jsonArray.add(reportlList.get(i).toJSON());
			}
			out.print(jsonArray);
			out.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
