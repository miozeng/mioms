package com.mioms.core.service;

import java.util.List;

import com.mioms.core.dto.RoleDto;
import com.mioms.core.exception.ServiceGenericException;


public interface RoleService {
	
	public List<RoleDto> findAllRoleMsg();

	
	public List<RoleDto> findAllRoleMsg2();
	
	public List<RoleDto> findAllRoleMsg3();
	
	public void init();
	
	public void exceptionMethod() throws ServiceGenericException;
}
