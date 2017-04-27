package cn.com.welleplus.fire.dao;

import java.util.List;
import java.util.Map;

import cn.com.welleplus.fire.entity.HistoryTbPress;

public interface HistoryTbPressDao {

	List<HistoryTbPress> find(Map<String, Object> map);
	int findCount();
	int findStationCount();
	List<HistoryTbPress> findByStation(Map<String, Object> map);
	int findCountByStation(int station);
	List<HistoryTbPress> findAll(Map<String, Object> map);
	List<HistoryTbPress> findAllByStation(Map<String, Object> map);
}
