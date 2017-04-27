package cn.com.welleplus.fire.service;

import java.util.List;
import java.util.Map;

import cn.com.welleplus.fire.entity.RealTbPress;
import cn.com.welleplus.fire.exception.PageException;
import cn.com.welleplus.fire.exception.StationException;

public interface RealTbPressService {

	List<RealTbPress> findTbPressByPage(String pageIndex,String pageSize) throws PageException;
	Map<String, Integer> findCount();
	List<RealTbPress> findTbPressBystation(String pageIndex,String pageSize,String station) throws PageException,StationException;
	Map<String, Integer> findCountByStation(String station) throws StationException;
	List<RealTbPress> findAll(String startTime,String endTime) throws StationException;
	List<RealTbPress> findAllByStation(String station,String startTime,String endTime) throws StationException;
}
