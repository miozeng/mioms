package com.mioms.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.mioms.core.dao.MenuDao;
import com.mioms.core.dao.OperationLogDao;
import com.mioms.core.dao.RoleDao;
import com.mioms.core.dao.UserDao;
import com.mioms.core.dto.RoleDto;
import com.mioms.core.entity.Menu;
import com.mioms.core.entity.Role;
import com.mioms.core.entity.User;
import com.mioms.core.exception.ServiceGenericException;


@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private OperationLogDao operationLogDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private MenuDao menuDao;
	@Override
	public List<RoleDto> findAllRoleMsg() {
		List<Role> roles = roleDao.findAll();
		List<RoleDto> dtos = new ArrayList<RoleDto>();
		for (Role role : roles) {
			RoleDto dto = RoleDto.entityToDto(role);
			dtos.add(dto);
		}
		return dtos;
	}
	@Override
	public List<RoleDto> findAllRoleMsg2() {
		List<Role> roles = roleDao.findAll();
		List<RoleDto> dtos = new ArrayList<RoleDto>();
		for (Role role : roles) {
			RoleDto dto = new RoleDto();
			dto.setRoleName(role.getRoleName());
			Set<User> users = role.getUsers();
			String userStr ="";
			for (User user :users) {
				userStr+=user.getName()+";";
			}
			dto.setUserStr(userStr);
			dtos.add(dto);
		}
		return dtos;
	}
	
	@Override
	public List<RoleDto> findAllRoleMsg3() {
		List<Role> roles = roleDao.findAll();
		List<RoleDto> dtos = new ArrayList<RoleDto>();
		for (Role role : roles) {
			RoleDto dto = new RoleDto();
			dto.setRoleName(role.getRoleName());
			dtos.add(dto);
		}
		return dtos;
	}
	
	@Override
	public void init() {
	
		
		List<Menu> menus = menuDao.findByType("type1");
		for (int i = 0; i < menus.size(); i++) {
			System.out.println(menus.get(i).getMenuName());
		}
		
	}
	@Override
	public void exceptionMethod() throws ServiceGenericException{
		int a = 1;
		try {
			Assert.isTrue(a ==2);
		} catch (Exception e) {
			throw new ServiceGenericException("002", "you have erorr on assert");
		}
	
		
	}


}
