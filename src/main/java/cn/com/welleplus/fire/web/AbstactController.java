package cn.com.welleplus.fire.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.welleplus.fire.entity.RealTbPress;
import cn.com.welleplus.fire.exception.AdminNameException;
import cn.com.welleplus.fire.exception.PageException;
import cn.com.welleplus.fire.exception.PasswordException;
import cn.com.welleplus.fire.exception.StationException;
import cn.com.welleplus.fire.util.JsonResult;
/**
 * 同一处理异常和自定义异常
 * @author Lu
 *
 */
public class AbstactController {


	@SuppressWarnings("rawtypes")
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public JsonResult expHandle(Exception e){
		e.printStackTrace();
		return new JsonResult<Integer>(0,e);
	}

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(StationException.class)
	@ResponseBody
	public void station(StationException e,HttpServletRequest request, HttpServletResponse response){
		try {
		response.setContentType("text/html; charset=utf-8");
		String callbackName=(String)request.getParameter("callback");
		System.out.println(callbackName);
		List<RealTbPress> list = new ArrayList<RealTbPress>();
		JsonResult<List<RealTbPress>> json = new JsonResult<List<RealTbPress>>(2,list,e);
		System.out.println(json.toString());
		//renderStr=callbackName+"("+json.toString()+")";
		String renderStr=callbackName+"("+json.toString()+")";

		response.setContentType("text/plain;charset=UTF-8");  
		
			response.getWriter().write(renderStr);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//return new JsonResult<List<RealTbPress>>(2,list,e);
	}

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(PageException.class)
	@ResponseBody
	public JsonResult page(PageException e){
		return new JsonResult<Integer>(1,0,e);
	}

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(AdminNameException.class)
	@ResponseBody
	public JsonResult userName(AdminNameException e){
		return new JsonResult<Integer>(2,0,e);
	}

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(PasswordException.class)
	@ResponseBody
	public JsonResult password(PasswordException e){
		return new JsonResult<Integer>(3,0,e);
	}


}
