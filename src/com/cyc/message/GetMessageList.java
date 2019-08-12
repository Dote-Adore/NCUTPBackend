package com.cyc.message;

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
import com.cyc.dao.impl.MessagesDAOImpl;
import com.cyc.entity.Messages;

public class GetMessageList extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		Integer userid = Integer.parseInt(req.getParameter("userid"));
		System.out.println("userid: "+userid+"comes to get his/her messages...");
		JSONArray jsonArray = new JSONArray();
		try {
			MessagesDAOImpl MDI = new MessagesDAOImpl();
			List<Messages> messageList = MDI.getlist(userid);
			for(int i = messageList.size()-1; i >=0 ; i--) {
				JSONObject js = messageList.get(i).tojson();
				//如果是评论消息
				if(js.getInteger("type")==2||js.getInteger("type")==1) {
					JSONObject remarkJson = JSONObject.parseObject(js.getString("remark"));
					js.remove("remark");
					js.put("remark", remarkJson);
				}
				jsonArray.add(js);
			}
			out.print(jsonArray);
			// 将所有标记为已读
			MDI.setRead(userid);
			return;
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
}
