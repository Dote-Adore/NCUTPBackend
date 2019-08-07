package com.cyc.manage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.alibaba.fastjson.JSONObject;
import com.cyc.dao.impl.CommentsDAOImpl;
import com.cyc.dao.impl.MessagesDAOImpl;
import com.cyc.dao.impl.PublishDetailDAOImpl;
import com.cyc.dao.impl.PublishImgDAOImpl;
import com.cyc.dao.impl.ViolationHandleDAOImpl;
import com.cyc.entity.PublishImg;
import com.sun.net.httpserver.Authenticator.Success;

public class deletepublish extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		JSONObject jsonObject = new JSONObject();
		String remark = req.getParameter("remark");
		String violatingcontent = req.getParameter("violatingcontent");
		String processingstaff = req.getParameter("processingstaff");
		String publishcontent = req.getParameter("publishcontent");
		Integer userid = Integer.parseInt(req.getParameter("userid"));
		Integer publishid = Integer.parseInt(req.getParameter("publishid"));
		String imgsrc = req.getParameter("imagesrc");
		Timestamp time = new Timestamp(System.currentTimeMillis());
		System.out.println(remark+violatingcontent+processingstaff+publishcontent+imgsrc+userid+"   "+publishid);
		try {
			//插入一条处罚结果
			ViolationHandleDAOImpl VHDI = new ViolationHandleDAOImpl();
			VHDI.create(publishid, violatingcontent, userid, processingstaff, remark,time,publishcontent, imgsrc);
			//删除违规publish
			PublishDetailDAOImpl PDDI = new PublishDetailDAOImpl();
			PDDI.delete(publishid);
			
			// 删除对应的评论
			CommentsDAOImpl CDI = new CommentsDAOImpl();
			CDI.deletebypublishid(publishid);
			
			//删除img记录
			PublishImgDAOImpl PIDI = new PublishImgDAOImpl();
			List<PublishImg> publishImgs =  PIDI.selectAll(publishid);
			PIDI.deleteAll(publishid);
			// 给用户发送消息
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String timetString = sdf.format(time);
			MessagesDAOImpl MDI = new MessagesDAOImpl();
			MDI.create(userid, 0, "处罚结果通知", timetString, violatingcontent, remark, publishcontent, imgsrc, false);
			jsonObject.put("success", true);
			out.print(jsonObject);
			return;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		jsonObject.put("success", false);
		out.print(jsonObject);
	}
	
	public void seedMessage() {
		
	}
}
