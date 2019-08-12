package com.cyc.comments;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.cyc.dao.impl.CommentsDAOImpl;
import com.cyc.dao.impl.MessagesDAOImpl;
import com.cyc.utils.TimeUtil;

public class Create extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		try {
			// �½�һ�����ۼ�¼
			Integer publishid = Integer.parseInt(req.getParameter("publishid"));
			Integer userid = Integer.parseInt(req.getParameter("userid"));
			Integer respuserid = Integer.parseInt(req.getParameter("respuserid"));
			String content = req.getParameter("content");
			Integer respid = Integer.parseInt(req.getParameter("respid"));
			Integer publishuserid = Integer.parseInt(req.getParameter("publishuserid"));
			String imgsrc = req.getParameter("mainimgsrc");
			String publishcontent =req.getParameter("publishcontent");
			String username =req.getParameter("username");
			CommentsDAOImpl CDI = new CommentsDAOImpl();
			CDI.insert(publishid, userid, content, respid);
			out.print("successInsert");
			JSONObject remark = new JSONObject();
			remark.put("username", username);
			remark.put("publishid",publishid);
			String remarkString = remark.toJSONString();
			MessagesDAOImpl MDI = new MessagesDAOImpl();
			String currentTime = TimeUtil.getFormatTime();
			//����Ϣ��������(�������Ϣ���˲��������Ʒ�����Ҳ��Ǹ���Ʒ���ظ�)
			if(userid!=publishuserid&&respuserid!=publishuserid) {
				MDI.create(publishuserid, 2, "������Ϣ", currentTime, content, remarkString, publishcontent, imgsrc, false);
				System.out.println("����һ��������Ϣ����Ʒ�����ߣ�"+publishuserid);
			}
			//����Ϣ�����ظ���
			if(respuserid!=0) {
				MDI.create(respuserid, 2, "�ظ���Ϣ", currentTime, content, remarkString, publishcontent, imgsrc, false);
				System.out.println("����һ���ظ���Ϣ����"+respuserid);
			}
			//����Ϣ�������۵�¥��
			int louzhuid = CDI.getuserid(respid);
			if(userid!=louzhuid&&respid!=0&&louzhuid!=respuserid) {
				MDI.create(louzhuid, 2, "������Ϣ", currentTime, content, remarkString, publishcontent, imgsrc, false);
				System.out.println("����һ���ظ���Ϣ��¥����"+louzhuid);
			}
		} catch (Exception e) {
			// TODO: handle exception
			out.print("failInsert");
		}
	}
}
