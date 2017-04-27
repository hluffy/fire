package cn.com.welleplus.fire.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.com.welleplus.fire.service.AdminService;

public class ACLFilter implements Filter  {

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String path = request.getRequestURI();
		System.out.println(path);

		path = path.substring(path.indexOf('/',1));
		System.out.println(">>"+path);

		if(path.matches(".*/index\\.html$")||path.matches(".*/data.*\\.html$")||path.matches(".*/main\\.html$")){
			checkLogin(request,response,chain);
			return;
		}
		if((path.matches(".(real).*\\.do$")||path.matches(".(history).*\\.do$"))&&!path.matches(".(real/find).*\\.do$")&&!path.matches(".(history/find).*\\.do$")){
			checkDotDo(request,response,chain);
			return;
		}
		chain.doFilter(request, response);

	}

	private void checkLogin(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("checkLogin");
		//检查是否有token cookie
		//如果没有，就重定向到login.html
		String token = getCookie(request,"token");
		System.out.println("token--"+token);
		if(token==null){
			// path = /fire/login.html
			String path = request.getContextPath()+"/login.html";
			response.sendRedirect(path);
			return;
		}
		//检查token的有效性
		ServletContext sc = request.getServletContext();
		//获取Spring容器
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		//从容器中获取UserService对象
		AdminService service = ctx.getBean("adminService",AdminService.class);
		System.out.println(service);
		System.out.println(token);

		String username = getCookie(request,"username");
		System.out.println("username:"+username);
		System.out.println(service.checkToken(username,token));

		if(service.checkToken(username,token)){
			chain.doFilter(request, response);
			return;
		}
		//重定向到login.html
		String path = request.getContextPath()+"/login.html";
		response.sendRedirect(path);
	}

	public void checkDotDo(HttpServletRequest request,HttpServletResponse response,FilterChain chain) throws IOException, ServletException{
		System.out.println("checkDotDo");
		String token = getCookie(request,"token");

		if(token==null){
			// path = /note/login.html
			String json = "{\"state\":1,\"message\":\"必须登录\"}";
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().println(json);
			
			return;
		}

		//检查token的有效性
		ServletContext sc = request.getServletContext();
		//获取Spring容器
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		//从容器中获取UserService对象
		AdminService service = ctx.getBean("adminService",AdminService.class);
		System.out.println(service);
		System.out.println(token);
		String username = getCookie(request,"username");
		System.out.println("userId:"+username);
		System.out.println(service.checkToken(username,token));

		if(service.checkToken(username,token)){
			chain.doFilter(request, response);
			return;
		}

		String json = "{\"state\":1,\"message\":\"账号异地登录\"}";
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().println(json);
		
		return;
	}

	private String getCookie(HttpServletRequest request, String cookieName){
		Cookie[] cookies = request.getCookies();
		if(cookies==null){
			return null;
		}
		for(Cookie cookie : cookies){
			if(cookieName.equals((cookie).getName())){
				return cookie.getValue();
			}
		}
		return null;
	}

	public void init(FilterConfig cfg) throws ServletException {

	}

}
