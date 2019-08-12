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
			//如果举报失败
			if(!successReport) {
				System.out.println("举报不成功");
				//举报结果不成功参数
				title = "举报不成功通知";
				content.put("reportedname", publishDetails.getString("username"));
				content.put("result", "暂无法认定举报对象存在违规");
				content.put("method", "暂不处理");
				content.put("reporttime", report.getString("createtime"));
				reporterRemark = "根据提供的信息我们暂无法确定举报内容违规，若后续核实确实违规，我们将严肃处理！感谢您的支持。";
			}
			//如果举报成功
			else {
				//举报成功参数
				System.out.println("举报成功");
				title = "举报成功通知";
				content.put("reportedname", publishDetails.getString("username"));
				content.put("result", "已确认有违规行为");
				content.put("method", "删除相关商品");
				content.put("reporttime", report.getString("createtime"));
				reporterRemark = "我们已通过您的信息将违规内容进行处理，感谢您为净化昌大跳蚤市场做出贡献!";
				
				//删除publish
				int publishid = publishDetails.getInteger("id");
				PublishDetailDAOImpl PDDI = new PublishDetailDAOImpl();
				PDDI.delete(publishid);
				//删除publish的comment
				CommentsDAOImpl CDI = new CommentsDAOImpl();
				CDI.deletebypublishid(publishid);
				//生成一条处罚信息
				ViolationHandleDAOImpl VHDI = new ViolationHandleDAOImpl();
				vhid = VHDI.create(publishid, report.getString("reason"), publishDetails.getInteger("userid"), staffname, staffremark, currenttime, 
						publishDetails.getString("introduction"), publishDetails.getString("mainimgsrc"));
				
				//给被举报人发送一条消息
				MDI.create(publishDetails.getInteger("userid"), 0, "处罚结果通知", TimeUtil.getFormatTime(currenttime),report.getString("reason"), 
						staffremark,publishDetails.getString("introduction"), publishDetails.getString("mainimgsrc"), false);
			}
			MDI.create(report.getInteger("informerid"), 1, title, TimeUtil.getFormatTime(currenttime),
					reporterRemark,content.toJSONString(), publishDetails.getString("introduction"), publishDetails.getString("mainimgsrc"), false);
			
			System.out.println("正在生成一条处理记录");
			// 生成一条处理记录
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
			System.out.println("生成成功");
			//删除该条report
			ReportDAOImpl RDI = new ReportDAOImpl();
			RDI.delete(report.getInteger("id"));
			res.put("success", true);
			System.out.println("结束");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			res.put("success", false);
		} finally {
			out.print(res);
		}
	}
}
