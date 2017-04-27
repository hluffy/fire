package cn.com.welleplus.fire.aop;

import java.util.TimerTask;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.com.welleplus.dbo.service.TbPressService;

public class Time extends TimerTask {

	private static boolean isRunning = false; //�ж��Ƿ�ִ�����
	private static ServletContextEvent event = null;
	private WebApplicationContext springContext; //��ȡbean
	public Time(ServletContextEvent event) {
		this.event = event;
	}

	@Override
	public void run() {
		springContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		TbPressService tbPressService = (TbPressService) springContext.getBean("tbPressService");
		//		if(tbPressService==null){
		//			
		//		}
		if(!isRunning) {
			isRunning  =  true;
			System.out.println("��ʼ����");
			try {
				tbPressService.SaveAndDelete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			isRunning  =  false;
			System.out.println("�������");
		}else {
			System.out.println("�ϴ�����δ���");
		}
	}


}
