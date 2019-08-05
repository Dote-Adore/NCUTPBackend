package com.cyc.trading;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyc.dao.impl.PublishDetailDAOImpl;
import com.cyc.dao.impl.TradingInfoDAOImpl;
import com.cyc.dao.impl.UserInfoDetailsDAOImpl;

public class SellGoods extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		int publishid = Integer.parseInt(req.getParameter("publishid"));
		int sellerid = Integer.parseInt(req.getParameter("sellerid"));
		String introduction = req.getParameter("introduction");
		int price = Integer.parseInt(req.getParameter("price"));
		String mainimgSrc = req.getParameter("mainimgsrc");
		try {
			//���һ��������Ϣ
			TradingInfoDAOImpl TIDI = new TradingInfoDAOImpl();
			TIDI.CreateATrade(publishid, price, introduction, mainimgSrc, sellerid);
			//ɾ�����ڷ���������
			PublishDetailDAOImpl PDDI = new PublishDetailDAOImpl();
			PDDI.delete(publishid);
			//����userinfodetails
			UserInfoDetailsDAOImpl UIDDI = new UserInfoDetailsDAOImpl();
			UIDDI.updateWhenTrade(sellerid, price);
			out.print("success");
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
}
