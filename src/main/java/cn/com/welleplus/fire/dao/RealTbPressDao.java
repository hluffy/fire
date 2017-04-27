package cn.com.welleplus.fire.dao;

import java.util.List;
import java.util.Map;

import cn.com.welleplus.fire.entity.RealTbPress;

public interface RealTbPressDao {

	List<RealTbPress> find(Map<String, Object> map);
	int findCount();
	int findStationCount();
	List<RealTbPress> findByStation(Map<String, Object> map);
	int findCountByStation(int station);
	List<RealTbPress> findAll(Map<String, Object> map);
	List<RealTbPress> findAllByStation(Map<String, Object> map);
}
