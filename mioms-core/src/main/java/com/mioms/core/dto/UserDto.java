package com.mioms.core.dto;

import com.mioms.core.entity.User;

public class UserDto {
	
	private String id;
	private String name;

	private String age;

	
	private String password;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static UserDto EntityToDto(User user){
		UserDto dto = new UserDto();
		try {
			dto.setId(user.getId().toString());
			dto.setAge(user.getAge());
			dto.setName(user.getName());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return dto;
	}
	
	
	public static User DtoToEntity(UserDto dto){
		User user = new User();
		if(dto.getId() != null ){
			user.setId(Long.parseLong(dto.getId()));
		}
	
		user.setAge(dto.getAge());
		user.setName(dto.getName());
		return user;
	}
	
}
