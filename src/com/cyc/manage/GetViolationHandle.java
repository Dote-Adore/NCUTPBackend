package com.cyc.manage;

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
import com.cyc.dao.impl.ViolationHandleDAOImpl;
import com.cyc.entity.ViolationHandle;

public class GetViolationHandle extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=UTF-8");
		String adminName = req.getParameter("adminName");
		PrintWriter out = resp.getWriter();
		int page = Integer.parseInt(req.getParameter("page"));
		try {
			ViolationHandleDAOImpl VHDI = new ViolationHandleDAOImpl();
			
			List<ViolationHandle> violationHandleList = VHDI.getByManageName(adminName, page);
			JSONArray jsArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			for(int i = 0; i<violationHandleList.size(); i++) {
				jsonObject = violationHandleList.get(i).toJSON();
				jsArray.add(jsonObject);
			}
			out.print(jsArray);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
