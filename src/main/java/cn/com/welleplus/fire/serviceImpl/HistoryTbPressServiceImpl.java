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

import cn.com.welleplus.fire.dao.HistoryTbPressDao;
import cn.com.welleplus.fire.entity.HistoryTbPress;
import cn.com.welleplus.fire.exception.PageException;
import cn.com.welleplus.fire.exception.StationException;
import cn.com.welleplus.fire.service.HistoryTbPressService;

@Service("historyTbPressService")
public class HistoryTbPressServiceImpl implements HistoryTbPressService {


	@Resource
	private HistoryTbPressDao historyTbPressDao;
	
	//��ҳ��ѯ����
	public List<HistoryTbPress> findTbPressByPage(String pageIndex, String pageSize) throws PageException {
		//������������ĺ�����
		String regex = "[0-9]+";
		if(pageIndex==null||pageIndex.trim().isEmpty()||pageSize==null||pageSize.trim().isEmpty()){
			throw new PageException("����,����²���");
		}
		if(!pageIndex.matches(regex)||!pageSize.matches(regex)){
			throw new PageException("����,����²���");
		}
		int index = Integer.parseInt(pageIndex);
		int size = Integer.parseInt(pageSize);
		List<HistoryTbPress> datas = new ArrayList<HistoryTbPress>();
		//��ҳ��ѯ����ʼ����ĩβ��
		int start = (index-1)*size;
		int end = index*size;
		System.out.println(start+"  "+end);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		datas = historyTbPressDao.find(map);
		System.out.println(datas);
		return datas;
	}

	//��ѯ���ݵ�����
	public Map<String, Integer> findCount() {
		int count = historyTbPressDao.findCount();
		System.out.println("count:"+count);
		int stationCount = historyTbPressDao.findStationCount();//���ظ����ݵ�����
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("count", count);
		map.put("stationCount", stationCount);
		return map;
	}

	//��station��ҳ��ѯ
	public List<HistoryTbPress> findTbPressBystation(String pageIndex, String pageSize, String station) throws PageException,StationException {
		//������������ĺ�����
		String regex = "[0-9]+";
		if(pageIndex==null||pageIndex.trim().isEmpty()||pageSize==null||pageSize.trim().isEmpty()){
			throw new PageException("����,����²���");
		}
		if(!pageIndex.matches(regex)||!pageSize.matches(regex)){
			throw new PageException("����,����²���");
		}
		if(station==null||station.trim().isEmpty()){
			throw new StationException("վ�Ų���Ϊ��");
		}
		if(!station.matches(regex)){
			throw new StationException("����������");
		}
		int index = Integer.parseInt(pageIndex);
		int size = Integer.parseInt(pageSize);
		int s = Integer.parseInt(station);
		List<HistoryTbPress> datas = new ArrayList<HistoryTbPress>();
		int start = (index-1)*size;
		int end = index*size;
		System.out.println(start+"  "+end);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		map.put("station", s);
		datas = historyTbPressDao.findByStation(map);
		System.out.println(datas);
		return datas;
	}

	//��station��ѯ����
	public Map<String, Integer> findCountByStation(String station) throws StationException {
		//������������ĺ�����
		String regex = "[0-9]+";
		if(station==null||station.trim().isEmpty()){
			throw new StationException("վ�Ų���Ϊ��");
		}
		if(!station.matches(regex)){
			throw new StationException("����������");
		}
		int s = Integer.parseInt(station);
		int count = historyTbPressDao.findCountByStation(s);
		System.out.println("count:"+count);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("count", count);
		map.put("stationCount", 1); //stationΨһ
		return map;
	}

	//��ѯ��������
	public List<HistoryTbPress> findAll(String startTime,String endTime) throws StationException  {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		if(startTime!=null&&!startTime.trim().isEmpty()){
			try {
				Date date = sdf.parse(startTime);
				sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				startTime = sdf.format(date);
			} catch (ParseException e) {
				throw new StationException("��ʼʱ���ʽ����ȷ");
			}
		}
		sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		if(endTime!=null&&!endTime.trim().isEmpty()){
			try {
				Date date = sdf.parse(endTime);
				sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				endTime = sdf.format(date);
			} catch (ParseException e) {
				throw new StationException("����ʱ���ʽ����ȷ");
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime",endTime);
		return historyTbPressDao.findAll(map);
	}

	//��station��ѯ��������
	public List<HistoryTbPress> findAllByStation(String station,String startTime,String endTime) throws StationException {
		//������������ĺ�����
		String regex = "[0-9]+";
		if(station==null||station.trim().isEmpty()){
			throw new StationException("վ�Ų���Ϊ��");
		}
		if(!station.matches(regex)){
			throw new StationException("����������");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		if(startTime!=null&&!startTime.trim().isEmpty()){
			try {
				Date date = sdf.parse(startTime);
				sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				startTime = sdf.format(date);
			} catch (ParseException e) {
				throw new StationException("��ʼʱ���ʽ����ȷ");
			}
		}
		sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		if(endTime!=null&&!endTime.trim().isEmpty()){
			try {
				Date date = sdf.parse(endTime);
				sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				endTime = sdf.format(date);
			} catch (ParseException e) {
				throw new StationException("����ʱ���ʽ����ȷ");
			}
		}
		int s = Integer.parseInt(station);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("station", s);
		map.put("startTime", startTime);
		map.put("endTime",endTime);
		List<HistoryTbPress> list = historyTbPressDao.findAllByStation(map);
		return list;
	}

}
