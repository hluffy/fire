package cn.com.welleplus.fire.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.welleplus.fire.entity.Admin;
import cn.com.welleplus.fire.service.AdminService;
import cn.com.welleplus.fire.util.JsonResult;

@Controller
@RequestMapping("/admin")
public class AdminController extends AbstactController {

	@Resource
	private AdminService adminService;
	
	@RequestMapping("login.do")
	@ResponseBody
	public JsonResult<Admin> getCfgUrlEntitys(String username, String password,HttpServletResponse response){
		Admin user = adminService.login(username, password);
		Cookie cookie = new Cookie("token", user.getToken());
		cookie.setPath("/");
		response.addCookie(cookie);
		return new JsonResult<Admin>(user);
	}
	
	@RequestMapping("findAll.do")
	@ResponseBody
	public JsonResult<List<Admin>> findAll(){
		List<Admin> list = adminService.findAll();
		return new JsonResult<List<Admin>>(list);
	}
	
	@RequestMapping("save.do")
	@ResponseBody
	public JsonResult<Boolean> saveAdmin(String username,String password){
		boolean b = adminService.SaveAdmin(username, password);
		return new JsonResult<Boolean>(b);
	}
}
