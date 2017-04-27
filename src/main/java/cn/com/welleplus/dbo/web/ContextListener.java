package cn.com.welleplus.dbo.web;

import java.util.Timer;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

	private Timer  timer  =  null;

	public void contextDestroyed(ServletContextEvent event) {
		timer.cancel();
		System.out.println("��ʱ������");
	}

	public void contextInitialized(ServletContextEvent event) {
		timer = new java.util.Timer(true);
		System.out.println("��ʱ��������");
		timer.schedule(new Time(event), 0, 60*1000); //��ʱִ��Time()
		System.out.println("�Ѿ����������ȱ�");
	}

}
