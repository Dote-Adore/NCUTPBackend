package com.cyc.report;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.cyc.dao.impl.ReportDAOImpl;
import com.cyc.entity.Report;

public class Create extends HttpServlet {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setCharacterEncoding("utf-8");
	resp.setCharacterEncoding("utf-8");
	resp.setContentType("text/html;charset=UTF-8");
	PrintWriter out = resp.getWriter();
	JSONObject js = new JSONObject();
	try {
		Report report = new Report();
		ReportDAOImpl RDI = new ReportDAOImpl();
		//获取数据
		report.setInformerid(Integer.parseInt(req.getParameter("informerid")));
		report.setInformername(req.getParameter("informername"));
		report.setPublishid(Integer.parseInt(req.getParameter("publishid")));
		report.setReason(req.getParameter("reason"));
		report.setRemark(req.getParameter("remark"));
		// 如果已经被人举报了
		if(RDI.getCount(Integer.parseInt(req.getParameter("publishid")))>0) {
			js.put("success", false);
			js.put("hasbeenreported",true);
			out.print(js);
			return;
		}
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		report.setCreatetime(currentTimestamp);
		RDI.create(report);
		js.put("success", true);
		out.print(js);
	} catch (SQLException e) {
		// TODO: handle exception
		js.put("success", false);
		e.printStackTrace();
	}
	out.close();

}
}
