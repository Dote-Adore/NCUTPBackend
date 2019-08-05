package com.cyc.publishimg;

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
import com.cyc.dao.impl.PublishImgDAOImpl;
import com.cyc.entity.PublishImg;

public class GetGoodsImgsUrl extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		int publishid = Integer.parseInt(req.getParameter("id"));
		PublishImgDAOImpl PIDI = new PublishImgDAOImpl();
		try {
			List<PublishImg> publishImglist = PIDI.selectAll(publishid);
			JSONArray urls = new JSONArray();
			JSONArray indexs = new JSONArray();
			for(int i = 0;i<publishImglist.size();i++) {
				urls.add(publishImglist.get(i).getSrc());
				indexs.add(publishImglist.get(i).getIndex());
			}
			JSONObject jso = new JSONObject();
			jso.put("src", urls);
			jso.put("index", indexs);
			out.print(jso);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		out.close();
		return;
	}
}
