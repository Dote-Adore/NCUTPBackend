package com.cyc.report;

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
import com.cyc.dao.impl.HandleReportDAOImpl;
import com.cyc.dao.impl.MessagesDAOImpl;
import com.cyc.dao.impl.PublishDetailDAOImpl;
import com.cyc.dao.impl.ReportDAOImpl;
import com.cyc.dao.impl.ViolationHandleDAOImpl;
import com.cyc.entity.HandleReport;
import com.cyc.utils.TimeUtil;

public class Handle extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PrintWriter out;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=UTF-8");
		out = resp.getWriter();
		JSONObject report = JSONObject.parseObject(req.getParameter("report"));
		JSONObject publishDetails = JSONObject.parseObject(req.getParameter("publishdetails"));
		boolean successReport = Boolean.parseBoolean(req.getParameter("successreport"));
		String staffremark = req.getParameter("remark");
		String staffname = req.getParameter("staff");
		Timestamp currenttime = new Timestamp(System.currentTimeMillis());
		JSONObject res = new JSONObject();
		
		try {
			String reporterRemark = null;
			int vhid = 0;
			MessagesDAOImpl MDI = new MessagesDAOImpl();
			String title = new String();
			JSONObject content = new JSONObject();
			//����ٱ�ʧ��
			if(!successReport) {
				System.out.println("�ٱ����ɹ�");
				//�ٱ�������ɹ�����
				title = "�ٱ����ɹ�֪ͨ";
				content.put("reportedname", publishDetails.getString("username"));
				content.put("result", "���޷��϶��ٱ��������Υ��");
				content.put("method", "�ݲ�����");
				content.put("reporttime", report.getString("createtime"));
				reporterRemark = "�����ṩ����Ϣ�������޷�ȷ���ٱ�����Υ�棬��������ʵȷʵΥ�棬���ǽ����ദ����л����֧�֡�";
			}
			//����ٱ��ɹ�
			else {
				//�ٱ��ɹ�����
				System.out.println("�ٱ��ɹ�");
				title = "�ٱ��ɹ�֪ͨ";
				content.put("reportedname", publishDetails.getString("username"));
				content.put("result", "��ȷ����Υ����Ϊ");
				content.put("method", "ɾ�������Ʒ");
				content.put("reporttime", report.getString("createtime"));
				reporterRemark = "������ͨ��������Ϣ��Υ�����ݽ��д�����л��Ϊ�������������г���������!";
				
				//ɾ��publish
				int publishid = publishDetails.getInteger("id");
				PublishDetailDAOImpl PDDI = new PublishDetailDAOImpl();
				PDDI.delete(publishid);
				//ɾ��publish��comment
				CommentsDAOImpl CDI = new CommentsDAOImpl();
				CDI.deletebypublishid(publishid);
				//����һ��������Ϣ
				ViolationHandleDAOImpl VHDI = new ViolationHandleDAOImpl();
				vhid = VHDI.create(publishid, report.getString("reason"), publishDetails.getInteger("userid"), staffname, staffremark, currenttime, 
						publishDetails.getString("introduction"), publishDetails.getString("mainimgsrc"));
				
				//�����ٱ��˷���һ����Ϣ
				MDI.create(publishDetails.getInteger("userid"), 0, "�������֪ͨ", TimeUtil.getFormatTime(currenttime),report.getString("reason"), 
						staffremark,publishDetails.getString("introduction"), publishDetails.getString("mainimgsrc"), false);
			}
			MDI.create(report.getInteger("informerid"), 1, title, TimeUtil.getFormatTime(currenttime),
					reporterRemark,content.toJSONString(), publishDetails.getString("introduction"), publishDetails.getString("mainimgsrc"), false);
			
			System.out.println("��������һ�������¼");
			// ����һ�������¼
			HandleReport hr = new HandleReport();
			hr.setReportsuccess(successReport);
			hr.setInformeruserid(report.getInteger("informerid"));
			hr.setInformerusername(report.getString("informername"));
			hr.setReason(report.getString("reason"));
			hr.setRemarkbyinformer(report.getString("remark"));
			hr.setRemarkbystaff(staffremark);
			hr.setProcessingstaff(staffname);
			hr.setReporttime(report.getString("createtime"));
			hr.setHandletime(currenttime);
			hr.setViolationhandleid(vhid);
			HandleReportDAOImpl HRDI = new HandleReportDAOImpl();
			HRDI.create(hr);
			System.out.println("���ɳɹ�");
			//ɾ������report
			ReportDAOImpl RDI = new ReportDAOImpl();
			RDI.delete(report.getInteger("id"));
			res.put("success", true);
			System.out.println("����");
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			res.put("success", false);
		} finally {
			out.print(res);
		}
	}
}
