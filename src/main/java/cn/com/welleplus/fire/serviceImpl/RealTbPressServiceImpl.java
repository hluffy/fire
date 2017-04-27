package cn.com.welleplus.fire.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.welleplus.fire.dao.RealTbPressDao;
import cn.com.welleplus.fire.entity.RealTbPress;
import cn.com.welleplus.fire.exception.PageException;
import cn.com.welleplus.fire.exception.StationException;
import cn.com.welleplus.fire.service.RealTbPressService;

@Service("realTbPressService")
public class RealTbPressServiceImpl implements RealTbPressService {

	@Resource
	private RealTbPressDao realTbPressDao;


	public List<RealTbPress> findTbPressByPage(String pageIndex, String pageSize) {
		String regex = "[0-9]+";
		if(pageIndex==null||pageIndex.trim().isEmpty()||pageSize==null||pageSize.trim().isEmpty()){
			throw new PageException("错误,请从新操作");
		}
		if(!pageIndex.matches(regex)||!pageSize.matches(regex)){
			throw new PageException("错误,请从新操作");
		}
		int index = Integer.parseInt(pageIndex);
		int size = Integer.parseInt(pageSize);
		List<RealTbPress> datas = new ArrayList<RealTbPress>();
		int start = (index-1)*size;
		int end = index*size;
		System.out.println(start+"  "+end);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		datas = realTbPressDao.find(map);
		System.out.println(datas);
		return datas;
	}

	public Map<String, Integer> findCount() {
		int count = realTbPressDao.findCount();
		System.out.println("count:"+count);
		int stationCount = realTbPressDao.findStationCount();
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("count", count);
		map.put("stationCount", stationCount);
		return map;
	}

	public List<RealTbPress> findTbPressBystation(String pageIndex, String pageSize, String station) throws StationException {
		String regex = "[0-9]+";
		if(pageIndex==null||pageIndex.trim().isEmpty()||pageSize==null||pageSize.trim().isEmpty()){
			throw new PageException("错误,请从新操作");
		}
		if(!pageIndex.matches(regex)||!pageSize.matches(regex)){
			throw new PageException("错误,请从新操作");
		}
		if(station==null||station.trim().isEmpty()){
			throw new StationException("站号不能为空");
		}
		if(!station.matches(regex)){
			throw new StationException("请输入数字");
		}
		int index = Integer.parseInt(pageIndex);
		int size = Integer.parseInt(pageSize);
		int s = Integer.parseInt(station);
		List<RealTbPress> datas = new ArrayList<RealTbPress>();
		int start = (index-1)*size;
		int end = index*size;
		System.out.println(start+"  "+end);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		map.put("station", s);
		datas = realTbPressDao.findByStation(map);
		System.out.println(datas);
		return datas;
	}

	public Map<String, Integer> findCountByStation(String station) throws StationException {
		String regex = "[0-9]+";
		if(station==null||station.trim().isEmpty()){
			throw new StationException("站号不能为空");
		}
		if(!station.matches(regex)){
			throw new StationException("请输入数字");
		}
		int s = Integer.parseInt(station);
		int count = realTbPressDao.findCountByStation(s);
		System.out.println("count:"+count);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("count", count);
		map.put("stationCount", 1);
		return map;
	}

	public List<RealTbPress> findAll(String startTime,String endTime) throws StationException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		if(startTime!=null&&!startTime.trim().isEmpty()){
			try {
				Date date = sdf.parse(startTime);
				sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				startTime = sdf.format(date);
			} catch (ParseException e) {
				throw new StationException("开始时间格式不正确");
			}
		}
		sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		if(endTime!=null&&!endTime.trim().isEmpty()){
			try {
				Date date = sdf.parse(endTime);
				sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				endTime = sdf.format(date);
			} catch (ParseException e) {
				throw new StationException("结束时间格式不正确");
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime",endTime);
		List<RealTbPress> list = realTbPressDao.findAll(map);
		return list;
	}

	public List<RealTbPress> findAllByStation(String station,String startTime,String endTime) throws StationException {
		String regex = "[0-9]+";
		if(station==null||station.trim().isEmpty()){
			throw new StationException("站号不能为空");
		}
		if(!station.matches(regex)){
			throw new StationException("请输入数字");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		if(startTime!=null&&!startTime.trim().isEmpty()){
			try {
				Date date = sdf.parse(startTime);
				sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				startTime = sdf.format(date);
			} catch (ParseException e) {
				throw new StationException("开始时间格式不正确");
			}
		}
		sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		if(endTime!=null&&!endTime.trim().isEmpty()){
			try {
				Date date = sdf.parse(endTime);
				sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				endTime = sdf.format(date);
			} catch (ParseException e) {
				throw new StationException("结束时间格式不正确");
			}
		}
		int s = Integer.parseInt(station);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("station", s);
		map.put("startTime", startTime);
		map.put("endTime",endTime);
		List<RealTbPress> list = realTbPressDao.findAllByStation(map);
		return list;
	}
}
