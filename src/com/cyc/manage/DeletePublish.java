package com.cyc.manage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.cyc.dao.impl.CommentsDAOImpl;
import com.cyc.dao.impl.MessagesDAOImpl;
import com.cyc.dao.impl.PublishDetailDAOImpl;
import com.cyc.dao.impl.PublishImgDAOImpl;
import com.cyc.dao.impl.ViolationHandleDAOImpl;
import com.cyc.utils.TimeUtil;

public class DeletePublish extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO �Զ����ɵķ������
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
			//����һ���������
			ViolationHandleDAOImpl VHDI = new ViolationHandleDAOImpl();
			VHDI.create(publishid, violatingcontent, userid, processingstaff, remark,time,publishcontent, imgsrc);
			//ɾ��Υ��publish
			PublishDetailDAOImpl PDDI = new PublishDetailDAOImpl();
			PDDI.delete(publishid);
			
			// ɾ����Ӧ������
			CommentsDAOImpl CDI = new CommentsDAOImpl();
			CDI.deletebypublishid(publishid);
			
			//ɾ��img��¼
			PublishImgDAOImpl PIDI = new PublishImgDAOImpl();
			//List<PublishImg> publishImgs =  PIDI.selectAll(publishid);
			PIDI.deleteAll(publishid);
			// ���û�������Ϣ
			// ��ȡ��ǰʱ�� ���� yyyy-mm-dd hh��mm��ss ��ʽ
			String timetString = TimeUtil.getFormatTime(time);
			MessagesDAOImpl MDI = new MessagesDAOImpl();
			MDI.create(userid, 0, "�������֪ͨ", timetString, violatingcontent, remark, publishcontent, imgsrc, false);
			jsonObject.put("success", true);
			out.print(jsonObject);
			return;
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		jsonObject.put("success", false);
		out.print(jsonObject);
	}
	
	public void seedMessage() {
		
	}
}