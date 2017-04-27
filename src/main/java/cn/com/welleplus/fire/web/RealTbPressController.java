package cn.com.welleplus.fire.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.welleplus.fire.entity.RealTbPress;
import cn.com.welleplus.fire.service.RealTbPressService;
import cn.com.welleplus.fire.util.JsonResult;

@Controller
@RequestMapping("/real")
public class RealTbPressController extends AbstactController {

	@Resource
	private RealTbPressService realTbPressService;

	@RequestMapping("get.do")
	@ResponseBody
	public JsonResult<List<RealTbPress>> getByPage(String pageIndex, String pageSize){
		List<RealTbPress> list = realTbPressService.findTbPressByPage(pageIndex,pageSize);
		System.out.println(list);
		return new JsonResult<List<RealTbPress>>(list);
	}

	@RequestMapping("count.do")
	@ResponseBody
	public JsonResult<Map<String, Integer>> findCount(){
		Map<String, Integer> map = realTbPressService.findCount();
		System.out.println(map);
		return new JsonResult<Map<String,Integer>>(map);
	}

	@RequestMapping("stationCount.do")
	@ResponseBody
	public JsonResult<Map<String, Integer>> findCountByStation(String station){
		Map<String, Integer> map = realTbPressService.findCountByStation(station);
		System.out.println(map);
		return new JsonResult<Map<String,Integer>>(map);
	}

	@RequestMapping("getStation.do")
	@ResponseBody
	public JsonResult<List<RealTbPress>> getByStation(String pageIndex, String pageSize,String station){
		List<RealTbPress> list = realTbPressService.findTbPressBystation(pageIndex, pageSize, station);
		System.out.println(list);
		return new JsonResult<List<RealTbPress>>(list);
	}

	@RequestMapping("findAll.do")
	@ResponseBody
	public void findAll(HttpServletRequest request, HttpServletResponse response){
		try {
			String start = request.getParameter("start");
			String end = request.getParameter("end");
			response.setContentType("text/html; charset=utf-8");
			String callbackName=(String)request.getParameter("callback");
			System.out.println(callbackName);
			List<RealTbPress> list = realTbPressService.findAll(start, end);
			System.out.println(list);
			//String renderStr;
			JsonResult<List<RealTbPress>> json = new JsonResult<List<RealTbPress>>(list);
			System.out.println(json.toString());
			//renderStr=callbackName+"("+json.toString()+")";
			String renderStr=callbackName+"("+json.toString()+")";

			response.setContentType("text/plain;charset=UTF-8");  
			response.getWriter().write(renderStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("find.do")
	@ResponseBody
	public void findAllByStation(HttpServletRequest request, HttpServletResponse response){
		try {
			//System.out.println("station:"+station);
			String station = request.getParameter("station");
			String start = request.getParameter("start");
			String end = request.getParameter("end");
			response.setContentType("text/html; charset=utf-8");
			String callbackName=(String)request.getParameter("callback");
			System.out.println(callbackName);
			List<RealTbPress> list = realTbPressService.findAllByStation(station,start, end);
			System.out.println(list);
			//String renderStr;
			JsonResult<List<RealTbPress>> json = new JsonResult<List<RealTbPress>>(list);
			System.out.println(json.toString());
			//renderStr=callbackName+"("+json.toString()+")";
			String renderStr=callbackName+"("+json.toString()+")";

			response.setContentType("text/plain;charset=UTF-8");  
			response.getWriter().write(renderStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
