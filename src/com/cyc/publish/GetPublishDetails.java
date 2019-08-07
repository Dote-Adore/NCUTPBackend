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
		// TODO �Զ����ɵķ������
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		JSONArray jsonArray = new JSONArray();

		 //�����������userID�������
		if (req.getParameter("userid") != null) {
			hasUserid(req, resp);
			return;
		}
		if (req.getParameter("categoryid") != null) {
			hasCategoryid(req, resp);
			return;
		}
		try {
			// ��÷�����Ϣ
			PublishDetailDAOImpl PDDI = new PublishDetailDAOImpl();
			int page = Integer.parseInt(req.getParameter("page"));
			List<PublishDetail> pD = PDDI.examineAll(page);
			System.out.println(pD.toString());
			for (int i = 0; i<pD.size(); i++) {
				JSONObject jsonObj = pD.get(i).toJSON();

				// ��ȡ�û���Ϣ
				UserInfoDAOImpl UIDI = new UserInfoDAOImpl();
				UserInfo UI = UIDI.getUserInfobyID(pD.get(i).getUserid());
				jsonObj.put("username", UI.getName());
				jsonObj.put("avatar", UI.getAvatar());

				jsonArray.add(jsonObj);
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		// ��Ӧ
		System.out.println(jsonArray);
		out.print(jsonArray);
		return;

	}

	// �������ͷ��
	private void hasCategoryid(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// TODO �Զ����ɵķ������
		PrintWriter out = resp.getWriter();
		JSONArray jsonArray = new JSONArray();
		Integer categoryid = Integer.parseInt(req.getParameter("categoryid"));
		Integer page = Integer.parseInt(req.getParameter("page"));
		PublishDetailDAOImpl PDDI = new PublishDetailDAOImpl();

		List<PublishDetail> PD = PDDI.examineByCatagroyid(categoryid,page);
		try {
			for (int i = 0; i < PD.size(); i++) {
				JSONObject jsonObj = PD.get(i).toJSON();

				// ��ȡ�û���Ϣ
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
		case "������": {
			PublishDetailDAOImpl PDDI = new PublishDetailDAOImpl();
			List<PublishDetail> PD = null;
			try {
				PD = PDDI.exmineByUserID(userid);
				for (int i = PD.size() - 1; i >= 0; i--) {
					JSONObject jsonObj = PD.get(i).toJSON();

					// ��ȡ�û���Ϣ
					UserInfoDAOImpl UIDI = new UserInfoDAOImpl();
					UserInfo UI = UIDI.getUserInfobyID(PD.get(i).getUserid());
					jsonObj.put("username", UI.getName());
					jsonObj.put("avatar", UI.getAvatar());
					jsonArray.add(jsonObj);
				}

			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}

			out.print(jsonArray);
			out.close();
			break;
		}
		case "������":
		case "�ѹ���":
		default: {
			out.print("");
			out.close();
		}
		}
	}
}
