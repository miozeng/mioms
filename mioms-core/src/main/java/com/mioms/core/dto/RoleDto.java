package com.mioms.core.dto;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.mioms.core.entity.OperationLog;
import com.mioms.core.entity.Role;
import com.mioms.core.entity.User;


public class RoleDto {
	private String id;

	private String roleName;
	
	private String userStr;
	
	private Map<String, String> userLog = new HashMap<String, String>();
	
	
	public static RoleDto entityToDto(Role role){
		RoleDto dto = new RoleDto();
		dto.setRoleName(role.getRoleName());
		Set<User> users = role.getUsers();
		for (User user :users) {
			Set<OperationLog> logs = user.getLogs();
			StringBuffer sb = new StringBuffer("user【"+user.getName()+"】's Operation：");
			for (OperationLog log : logs) {
				sb.append(log.getMsg()+";" );
			}
			dto.addUserLog(user.getId().toString(), sb.toString());
		}
		return dto;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	
	public String getUserStr() {
		return userStr;
	}


	public void setUserStr(String userStr) {
		this.userStr = userStr;
	}


	public Map<String, String> getUserLog() {
		return userLog;
	}


	public void setUserLog(Map<String, String> userLog) {
		this.userLog = userLog;
	}
	
	
	public void addUserLog(final String key, final String value){
		this.userLog.put(key, value);
	}
	
	
}
