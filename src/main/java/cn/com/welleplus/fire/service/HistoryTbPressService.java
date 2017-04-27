package cn.com.welleplus.fire.service;

import java.util.List;
import java.util.Map;

import cn.com.welleplus.fire.entity.HistoryTbPress;
import cn.com.welleplus.fire.exception.PageException;
import cn.com.welleplus.fire.exception.StationException;

public interface HistoryTbPressService {

	List<HistoryTbPress> findTbPressByPage(String pageIndex,String pageSize) throws PageException;
	Map<String, Integer> findCount();
	List<HistoryTbPress> findTbPressBystation(String pageIndex,String pageSize,String station) throws PageException,StationException;
	Map<String, Integer> findCountByStation(String station) throws StationException;
	List<HistoryTbPress> findAll(String startTime,String endTime) throws StationException;
	List<HistoryTbPress> findAllByStation(String station,String startTime,String endTime) throws StationException;
}
