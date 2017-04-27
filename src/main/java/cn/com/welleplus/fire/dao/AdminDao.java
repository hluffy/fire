package cn.com.welleplus.fire.dao;

import java.util.List;
import java.util.Map;

import cn.com.welleplus.fire.entity.Admin;

public interface AdminDao {

	Admin findAdminByUsername(String username);
	int findAdminCount(String username);
	int updateUser(Map<String, Object> user);
	List<Admin> findAll();
	int savaAdmin(Map<String, String> map);
}
