package cn.com.welleplus.fire.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.welleplus.fire.dao.AdminDao;
import cn.com.welleplus.fire.entity.Admin;
import cn.com.welleplus.fire.exception.AdminNameException;
import cn.com.welleplus.fire.exception.PasswordException;
import cn.com.welleplus.fire.service.AdminService;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Resource
	private AdminDao adminDao;

	public Admin login(String username, String password)
			throws AdminNameException, PasswordException {
		//������������ĺ�����
		if(username==null||username.trim().isEmpty()){
			throw new AdminNameException("�����ܿ�");
		}
		String reg = "^\\w{3,10}$";
		if(!username.matches(reg)){
			throw new AdminNameException("�û��������Ϲ���");
		}
		if(password==null||password.trim().isEmpty()){
			throw new PasswordException("���벻�ܿ�");
		}
		if(!password.matches(reg)){
			throw new PasswordException("���벻���Ϲ���");
		}
		//��ѯ�û�����
		Admin user = adminDao.findAdminByUsername(username);

		if(user==null){
			throw new AdminNameException("�û�������");
		}
		if(user.getPassword().equals(password)){
			//ҵ����
			//��¼�ɹ��������û���Ϣ
			String token = UUID.randomUUID().toString();
			user.setToken(token);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("username", user.getUsername());
			data.put("token", token);
			int n = adminDao.updateUser(data);
			return user;
		}
		throw new PasswordException("�������");
	}

	public boolean checkToken(String username, String token) {
		//������������ĺ�����
		if(username==null||username.trim().isEmpty()){
			return false;
		}
		if(token==null||token.trim().isEmpty()){
			return false;
		}
		//��ѯ�û�����
		Admin user = adminDao.findAdminByUsername(username);
		if(user==null){
			return false;
		}
		return token.equals(user.getToken());
	}

	public List<Admin> findAll() {
		return adminDao.findAll();
	}

	public boolean SaveAdmin(String username, String password) throws AdminNameException,PasswordException {
		//������������ĺ�����
		if(username==null||username.trim().isEmpty()){
			throw new AdminNameException("�����ܿ�");
		}
		String reg = "^\\w{3,10}$";
		if(!username.matches(reg)){
			throw new AdminNameException("�û��������Ϲ���");
		}
		if(password==null||password.trim().isEmpty()){
			throw new PasswordException("���벻�ܿ�");
		}
		if(!password.matches(reg)){
			throw new PasswordException("���벻���Ϲ���");
		}
		//��ѯ�û�����
		Admin user = adminDao.findAdminByUsername(username);

		if(user!=null){
			throw new AdminNameException("�û����Ѵ���");
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", username);
		map.put("password", password);
		int n = adminDao.savaAdmin(map);
		if(n!=1){
			return false; //���治�ɹ�
		}
		return true; //����ɹ�
	}

}
