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

public class GetPublishDetails extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		JSONArray jsonArray = new JSONArray();

		 //如果请求中有userID这个数据
		if (req.getParameter("userid") != null) {
			hasUserid(req, resp);
			return;
		}
		if (req.getParameter("categoryid") != null) {
			hasCategoryid(req, resp);
			return;
		}
		try {
			// 获得发布信息
			PublishDetailDAOImpl PDDI = new PublishDetailDAOImpl();
			int page = Integer.parseInt(req.getParameter("page"));
			List<PublishDetail> pD = PDDI.examineAll(page);
			System.out.println(pD.toString());
			for (int i = 0; i<pD.size(); i++) {
				JSONObject jsonObj = pD.get(i).toJSON();

				// 获取用户信息
				UserInfoDAOImpl UIDI = new UserInfoDAOImpl();
				UserInfo UI = UIDI.getUserInfobyID(pD.get(i).getUserid());
				jsonObj.put("username", UI.getName());
				jsonObj.put("avatar", UI.getAvatar());

				jsonArray.add(jsonObj);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		// 响应
		System.out.println(jsonArray);
		out.print(jsonArray);
		return;

	}

	// 如果请求头有
	private void hasCategoryid(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// TODO 自动生成的方法存根
		PrintWriter out = resp.getWriter();
		JSONArray jsonArray = new JSONArray();
		Integer categoryid = Integer.parseInt(req.getParameter("categoryid"));
		Integer page = Integer.parseInt(req.getParameter("page"));
		PublishDetailDAOImpl PDDI = new PublishDetailDAOImpl();

		List<PublishDetail> PD = PDDI.examineByCatagroyid(categoryid,page);
		try {
			for (int i = 0; i < PD.size(); i++) {
				JSONObject jsonObj = PD.get(i).toJSON();

				// 获取用户信息
				UserInfoDAOImpl UIDI = new UserInfoDAOImpl();
				UserInfo UI = UIDI.getUserInfobyID(PD.get(i).getUserid());
				jsonObj.put("username", UI.getName());
				jsonObj.put("avatar", UI.getAvatar());

				jsonArray.add(jsonObj);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		out.print(jsonArray);
	}

	private void hasUserid(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		JSONArray jsonArray = new JSONArray();
		int userid = Integer.parseInt(req.getParameter("userid"));
		String name = req.getParameter("name");

		switch (name) {
		case "发布中": {
			PublishDetailDAOImpl PDDI = new PublishDetailDAOImpl();
			List<PublishDetail> PD = null;
			try {
				PD = PDDI.exmineByUserID(userid);
				for (int i = PD.size() - 1; i >= 0; i--) {
					JSONObject jsonObj = PD.get(i).toJSON();

					// 获取用户信息
					UserInfoDAOImpl UIDI = new UserInfoDAOImpl();
					UserInfo UI = UIDI.getUserInfobyID(PD.get(i).getUserid());
					jsonObj.put("username", UI.getName());
					jsonObj.put("avatar", UI.getAvatar());
					jsonArray.add(jsonObj);
				}

			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}

			out.print(jsonArray);
			out.close();
			break;
		}
		case "已卖出":
		case "已购得":
		default: {
			out.print("");
			out.close();
		}
		}
	}
}
