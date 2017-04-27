package cn.com.welleplus.dbo.web;

import java.util.Timer;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

	private Timer  timer  =  null;

	public void contextDestroyed(ServletContextEvent event) {
		timer.cancel();
		System.out.println("定时器销毁");
	}

	public void contextInitialized(ServletContextEvent event) {
		timer = new java.util.Timer(true);
		System.out.println("定时器已启动");
		timer.schedule(new Time(event), 0, 60*1000); //定时执行Time()
		System.out.println("已经添加任务调度表");
	}

}
