package com.cyc.timedtask;

import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TaskManage implements ServletContextListener {
	private Timer timer = null;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO 自动生成的方法存根
		timer = new Timer(true);
		sce.getServletContext().log("定时任务开始...");
		timer.schedule(new GetWXToken(sce.getServletContext()), new Date(), 90*1000*60);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO 自动生成的方法存根
		sce.getServletContext().log("定时任务销毁");
		timer.cancel();
	}
}
