package com.mioms.core.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mioms.core.dto.RoleDto;
import com.mioms.core.dto.UserDto;
import com.mioms.core.service.RoleService;
import com.mioms.core.service.UserService;
import com.mioms.core.util.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@Controller
@Api(value = "/user", description = "user API")
public class UserController {
	
	private static final Logger logger = LogManager.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;


	@ApiOperation(notes = "This function save user  ", httpMethod = "POST", value = "save user  ")
	@RequestMapping(value = "user/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(final UserDto dto) {
		Map<String, Object> rep = new HashMap<String, Object>();
		userService.saveUser(dto);
		rep.put("ret", true);
		rep.put("msg", "save succeed");
		return rep;
	}

	@ApiOperation(notes = "This function login the system ", httpMethod = "POST", value = "user login ")
	@RequestMapping(value = "user/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(@RequestBody UserDto dto,HttpSession httpSession){
		Map<String, Object> rep = new HashMap<String, Object>();
		logger.info("user name :"+dto.getName());
		UserDto user = userService.login(dto.getName(), dto.getPassword());
		if(user == null ){
			rep.put("status", "N");
			rep.put("msg", "save failure");
		}else{
			httpSession.setAttribute("uid", user.getId());
			rep.put("status", "Y");
			rep.put("uid", "382");
		}
	
		return rep;
	}
	
	@ApiOperation(notes = "This function find user by id ", httpMethod = "POST", value = "find by id ")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "user id ", required = true, dataType = "long", paramType = "query") })
	@RequestMapping(value = "user/findById", method = RequestMethod.GET)
	@ResponseBody
	public UserDto findById(final Long id) {
		System.out.println(id);
		UserDto user = userService.findById(id);
		return user;
	}

	@RequestMapping(value = "user/findAll", method = RequestMethod.GET)
	@ResponseBody
	public List<UserDto> findAll() {
		List<UserDto> dtos = userService.findAll();
		logger.error("seach done!");
		logger.debug("seach done!");
//		UserDetail user = SpringSecuritySeesionUtil.findCurrentUser();
		return dtos;
	}

	@RequestMapping(value = "user/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> delete(final Long id) {
		Map<String, Object> rep = new HashMap<String, Object>();
		userService.deleteUser(id);
		rep.put("ret", true);
		rep.put("msg", "delete succeed");
		return rep;
	}

	@RequestMapping(value = "user/findPage", method = RequestMethod.GET)
	@ResponseBody
	public List<UserDto> findPage(int pageNum) {
		int maxResults = 3;
		int firstResult = (pageNum - 1) * maxResults;
		Page<UserDto> pages = userService.findByPage(firstResult, maxResults);
		return pages.getObjectList();
	}

	@RequestMapping(value = "user/findRoleMsg1", method = RequestMethod.GET)
	@ResponseBody
	private List<RoleDto> findAllRoleMsg() {
		List<RoleDto> dtos = roleService.findAllRoleMsg();
		return dtos;
	}
	
	@RequestMapping(value = "user/findRoleMsg2", method = RequestMethod.GET)
	@ResponseBody
	private List<RoleDto> findAllRoleMsg2() {
		List<RoleDto> dtos = roleService.findAllRoleMsg2();
		return dtos;
	}
	
	@RequestMapping(value = "user/findRoleMsg3", method = RequestMethod.GET)
	@ResponseBody
	private List<RoleDto> findAllRoleMsg3() {
		List<RoleDto> dtos = roleService.findAllRoleMsg3();
		return dtos;
	}
	
	@RequestMapping(value = "user/init", method = RequestMethod.GET)
	private void init() {
		 roleService.init();
	}

	
	@RequestMapping(value = "role/err", method = RequestMethod.GET)
	private void err() {
		roleService.exceptionMethod();
	}


}
