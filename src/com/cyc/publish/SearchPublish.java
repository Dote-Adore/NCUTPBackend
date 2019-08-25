package com.cyc.publish;

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
import com.cyc.dao.impl.PublishDetailDAOImpl;
import com.cyc.dao.impl.UserInfoDAOImpl;
import com.cyc.entity.UserInfo;
import com.cyc.entity.PublishDetail;

public class SearchPublish extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		String content = req.getParameter("content");
		int page = Integer.parseInt(req.getParameter("page"));
		try {
			PublishDetailDAOImpl PDDI = new PublishDetailDAOImpl();
			List<PublishDetail> pdList = PDDI.examineBySearch(content,page);
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i<pdList.size(); i++) {
				JSONObject jsonObj = pdList.get(i).toJSON();

				// 获取用户信息
				UserInfoDAOImpl UIDI = new UserInfoDAOImpl();
				UserInfo UI = UIDI.getUserInfobyID(pdList.get(i).getUserid());
				jsonObj.put("username", UI.getName());
				jsonObj.put("avatar", UI.getAvatar());

				jsonArray.add(jsonObj);
			}
			out.print(jsonArray);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}
}
