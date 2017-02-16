package com.mioms.core.service;

import java.util.List;

import com.mioms.core.dto.UserDto;
import com.mioms.core.util.Page;


public interface UserService {
	
	public  void saveUser(UserDto dto);
	
	public void deleteUser(final Long id);
	
	public UserDto findById(final Long id);
	
	public UserDto login(String name, String paswd);

	public List<UserDto> findAll();
	
	public Page<UserDto> findByPage(int firstResult,int maxResults);
}
