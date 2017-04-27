package cn.com.welleplus.fire.service;

import java.util.List;

import cn.com.welleplus.fire.entity.Admin;
import cn.com.welleplus.fire.exception.AdminNameException;
import cn.com.welleplus.fire.exception.PasswordException;

public interface AdminService {

	Admin login(String username, String password) throws AdminNameException,PasswordException;
	boolean checkToken(String username, String token);
	List<Admin> findAll();
	boolean SaveAdmin(String username,String password) throws AdminNameException,PasswordException;
}
