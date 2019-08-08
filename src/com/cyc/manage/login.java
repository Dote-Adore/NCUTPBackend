package com.cyc.manage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.cyc.dao.impl.ManagePersonDAOImpl;

public class login extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO �Զ����ɵķ������
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO �Զ����ɵķ������
		System.out.println("���û����ڵ�¼...");
		resp.setContentType("text/html;charset=UTF-8");
		String sessionstr = req.getParameter("session");
		// �������session
		System.out.println(sessionstr);
		if(sessionstr!=null) {
			autologin(req, resp, sessionstr);
			return;
		}
		// ��ȡ�û���������
		try {
			PrintWriter out = resp.getWriter();
			String user = req.getParameter("user");
			String password = req.getParameter("password");
			ManagePersonDAOImpl MPDI = new ManagePersonDAOImpl();
			JSONObject res = new JSONObject();
			if (MPDI.login(user, password)) {
				System.out.println("�ɹ���¼");
				res.put("success", true);
				HttpSession session = req.getSession(true);
				// ����session
				System.out.print("session:"+session.getId()+"; username:"+user);
				MPDI.updateSession(user, session.getId());
				res.put("session", session.getId());
			} else {
				System.out.println("��¼ʧ��");
				res.put("success", false);
			}
			out.print(res);
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}

	}
	private void autologin(HttpServletRequest req, HttpServletResponse resp, String session) throws IOException {

		PrintWriter out = resp.getWriter();
		try {
			ManagePersonDAOImpl MPDI = new ManagePersonDAOImpl();
			JSONObject jsonObject = new JSONObject();
			String username = MPDI.getusername(session);
			if(username==null) {
				System.out.println("�Զ���¼ʧ�ܣ�δ�ҵ�session");
				jsonObject.put("success", false);
				out.print(jsonObject);
				return;
			}
			
			System.out.println("�û�: "+username+"�Զ���¼...");
			jsonObject.put("user", username);
			jsonObject.put("success", true);
			out.print(jsonObject);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
