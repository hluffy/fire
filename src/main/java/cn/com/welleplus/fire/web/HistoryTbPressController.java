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

import cn.com.welleplus.fire.entity.HistoryTbPress;
import cn.com.welleplus.fire.service.HistoryTbPressService;
import cn.com.welleplus.fire.util.JsonResult;

@Controller
@RequestMapping("/history")
public class HistoryTbPressController extends AbstactController {

	@Resource
	private HistoryTbPressService historyTbPressService;

	@RequestMapping("get.do")
	@ResponseBody
	public JsonResult<List<HistoryTbPress>> getByPage(String pageIndex, String pageSize){
		List<HistoryTbPress> list = historyTbPressService.findTbPressByPage(pageIndex,pageSize);
		System.out.println(list);
		return new JsonResult<List<HistoryTbPress>>(list);
	}

	@RequestMapping("count.do")
	@ResponseBody
	public JsonResult<Map<String, Integer>> findCount(){
		Map<String, Integer> map = historyTbPressService.findCount();
		System.out.println(map);
		return new JsonResult<Map<String,Integer>>(map);
	}

	@RequestMapping("stationCount.do")
	@ResponseBody
	public JsonResult<Map<String, Integer>> findCountByStation(String station){
		Map<String, Integer> map = historyTbPressService.findCountByStation(station);
		System.out.println(map);
		return new JsonResult<Map<String,Integer>>(map);
	}

	@RequestMapping("getStation.do")
	@ResponseBody
	public JsonResult<List<HistoryTbPress>> getByStation(String pageIndex, String pageSize,String station){
		List<HistoryTbPress> list = historyTbPressService.findTbPressBystation(pageIndex, pageSize, station);
		System.out.println(list);
		return new JsonResult<List<HistoryTbPress>>(list);
	}

	@RequestMapping("findAll.do")
	@ResponseBody
	public void findAll(HttpServletRequest request, HttpServletResponse response){
		try {
			//System.out.println("station:"+station);
			String station = request.getParameter("station");
			String start = request.getParameter("start");
			String end = request.getParameter("end");
			response.setContentType("text/html; charset=utf-8");
			String callbackName=(String)request.getParameter("callback");
			System.out.println(callbackName);
			List<HistoryTbPress> list = historyTbPressService.findAll(start, end);
			System.out.println(list);
			//String renderStr;
			JsonResult<List<HistoryTbPress>> json = new JsonResult<List<HistoryTbPress>>(list);
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
			List<HistoryTbPress> list = historyTbPressService.findAllByStation(station, start, end);
			System.out.println(list);
			//String renderStr;
			JsonResult<List<HistoryTbPress>> json = new JsonResult<List<HistoryTbPress>>(list);
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
