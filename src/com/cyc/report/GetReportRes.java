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
import com.alibaba.fastjson.JSONObject;
import com.cyc.dao.impl.HandleReportDAOImpl;
import com.cyc.entity.HandleReport;

public class GetReportRes extends HttpServlet {
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
		String adminName = req.getParameter("adminName");
		int page = Integer.parseInt(req.getParameter("page"));
		
		try {
			HandleReportDAOImpl HRDI = new HandleReportDAOImpl();
			
			List<HandleReport> handleReportList = HRDI.getByManageName(adminName, page);
			JSONArray jsArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			for(int i = 0; i<handleReportList.size(); i++) {
				jsonObject = handleReportList.get(i).toJSON();
				if(jsonObject.getBooleanValue("reportsuccess"))
					jsonObject.put("handleresult", "举报成功，删除相关商品");
				else
					jsonObject.put("handleresult", "举报失败");
				jsArray.add(jsonObject);
			}
			out.print(jsArray);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
