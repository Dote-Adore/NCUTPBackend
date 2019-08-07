package com.cyc.timedtask;

import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TaskManage implements ServletContextListener {
	private Timer timer = null;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO �Զ����ɵķ������
		timer = new Timer(true);
		sce.getServletContext().log("��ʱ����ʼ...");
		timer.schedule(new GetWXToken(sce.getServletContext()), new Date(), 90*1000*60);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO �Զ����ɵķ������
		sce.getServletContext().log("��ʱ��������");
		timer.cancel();
	}
}
