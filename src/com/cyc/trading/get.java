package com.cyc.trading;

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
import com.cyc.dao.impl.TradingInfoDAOImpl;
import com.cyc.entity.tradinginfo;

public class get extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		int sellerid = Integer.parseInt(req.getParameter("userid"));
		try {
			TradingInfoDAOImpl TIDI = new TradingInfoDAOImpl();
			List<tradinginfo> tiList = TIDI.getBysellerid(sellerid);
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject;
			for(int i = 0;i<tiList.size();i++) {
				jsonArray.add(tiList.get(i).toJSON());
			}
			out.print(jsonArray);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
