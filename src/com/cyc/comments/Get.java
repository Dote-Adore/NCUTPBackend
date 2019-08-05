package com.cyc.comments;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cyc.dao.impl.CommentsDAOImpl;
import com.cyc.dao.impl.UserInfoDAOImpl;
import com.cyc.entity.Comments;
import com.cyc.entity.UserInfo;

public class Get extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		try {
			
			int publishid = Integer.parseInt(req.getParameter("publishid"));
			List<Comments> commentslist = new ArrayList<Comments>();
			CommentsDAOImpl CDI = new CommentsDAOImpl();
			commentslist = CDI.getCommentsBypublishid(publishid);
			JSONArray jsonArray = new JSONArray();
			for(int i = commentslist.size()-1;i>=0;i--) {				
				//获取评论json化
				JSONObject jsonObject = commentslist.get(i).toJSON();
				
				//获取用户信息
				int userid = commentslist.get(i).getUserid();
				UserInfoDAOImpl UIDI = new UserInfoDAOImpl();
				UserInfo UI = UIDI.getUserInfobyID(userid);
				String username = UI.getName();
				String userAvatar = UI.getAvatar();
				jsonObject.put("username", username);
				jsonObject.put("useravatar",userAvatar);
				
				jsonArray.add(jsonObject);
			}
			
			out.print(jsonArray);
			
		} catch (Exception e) {
		}

	}
}
