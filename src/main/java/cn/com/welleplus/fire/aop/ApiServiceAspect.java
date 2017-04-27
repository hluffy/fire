package cn.com.welleplus.fire.aop;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component 
@Aspect
public class ApiServiceAspect {

	//���д洢��־���ݣ�
	private BlockingQueue<String> queue = new LinkedBlockingDeque<String>(500);

	//�洢��־��·��
	private String filename;
	@Value("#{config.filename}")
	public void setFilename(String filename) {
		this.filename = filename;
		file = new File(filename);
	}

	private File file;
	private Thread writer;

	//�����������־д���ļ�
	public ApiServiceAspect() {
		writer = new Thread(){
			@Override
			public void run(){
				while(true){
					try{
						//������û�������ӳ�0.5��
						if(queue.isEmpty()){
							Thread.sleep(500);
							continue;
						}
						//���ֶ�����������
						PrintWriter out = new PrintWriter(new FileOutputStream(file,true));
						while(! queue.isEmpty()){
							String str = queue.poll();
							out.println(str);
						}
						out.close();
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};
		writer.start();
	}
	

	/** 
	 * ���� ������service�����Σ����Σ��׳����쳣��ִ�г����ʱ��
	 */ 
	private final String POINT_CUT = "execution(* cn.com.welleplus.fire.service*.*Service.*(..))";

	@Pointcut(POINT_CUT)
	private void pointcut(){}

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Before(value = POINT_CUT)
	public void before(JoinPoint joinPoint) { //��¼���õĺ��������
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		StringBuilder log = new StringBuilder();
		Date time = new Date();
		String t = sdf.format(time);
		log.append(t+"  before: ")
		.append(className)
		.append("@")
		.append(methodName)
		.append(" , params: ");
		Object[] args = joinPoint.getArgs();
		for (Object arg : args) {
			log.append(arg + ", ");
		}
		queue.offer(log.toString());
	}

	@AfterReturning(value = "pointcut()", returning = "returnObj")
	public void afterReturn(Object returnObj) { //��¼����ֵ
		Date time = new Date();
		String t = sdf.format(time);
		queue.offer(t+"  afterReturning: "+returnObj);
	}

	@AfterThrowing(value = POINT_CUT, throwing = "e")
	public void afterThrowing(Throwable e) { //��¼�׳����쳣
		Date time = new Date();
		String t = sdf.format(time);
		queue.offer(t+"  afterThrowing: "+e.getMessage()+", "+e);
	}

	@Around(value = "pointcut()")
	public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable { //��¼�׳����Զ����쳣�ͺ���ִ��ʱ��
		Long begin = System.currentTimeMillis();
		Date time = new Date();
		String t = sdf.format(time);
		StringBuilder log = new StringBuilder(t+"  around: ");
		Object result = null;
		try {
			result = proceedingJoinPoint.proceed();
		} catch (Exception e) {
			queue.offer(log + e.getMessage()+", "+e);
			throw e;
		}
		Long end = System.currentTimeMillis();
		log.append(" ִ��ʱ��: ")
		.append(end-begin)
		.append("ms");
		queue.offer(log.toString());
		return result;
	}
}
