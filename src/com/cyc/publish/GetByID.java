package com.cyc.publish;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.cyc.dao.impl.PublishDetailDAOImpl;
import com.cyc.dao.impl.UserInfoDAOImpl;
import com.cyc.entity.PublishDetail;
import com.cyc.entity.UserInfo;

public class GetByID extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		Integer id = Integer.parseInt(req.getParameter("id"));
		try {
			PublishDetailDAOImpl PDDI = new PublishDetailDAOImpl();
			PublishDetail pd = PDDI.getpublDetailsByID(id);
			JSONObject js = pd.toJSON();
			UserInfoDAOImpl UIDI = new UserInfoDAOImpl();
			UserInfo UI;
			UI = UIDI.getUserInfobyID(pd.getUserid());
			js.put("username", UI.getName());
			js.put("avatar", UI.getAvatar());
			out.print(js);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
