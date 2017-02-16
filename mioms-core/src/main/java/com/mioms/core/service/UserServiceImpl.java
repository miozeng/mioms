package com.mioms.core.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mioms.core.dao.UserDao;
import com.mioms.core.dto.UserDto;
import com.mioms.core.entity.User;
import com.mioms.core.util.LdapHandlerUtil;
import com.mioms.core.util.Page;
import com.mioms.core.util.SpringSecuritySeesionUtil;


@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
	@Autowired
	private UserDao userDao;
	
	@Value("${ldap.url}")
	private String url;

	@Value("${ldap.dn}")
	private String dn;

	@Value("${ldap.password}")
	private String dpassword;
	
	@Value("${ldap.paswdatrr}")
	private String paswdAtr;
	
	@Value("${ldap.factory}")
	private String factory;
	
	@Value("${ldap.search.base}")
	private String searchBase;
	
	@Value("${ldap.search.filter}")
	private String searchFilter;
	public  void saveUser(UserDto dto){
		User user = UserDto.DtoToEntity(dto);
		user.setPassword(SpringSecuritySeesionUtil.getMd5Passwords(user.getPassword(), user.getName()));
		userDao.save(user);
	}
	
	public void deleteUser(final Long id){
		userDao.deleteById(id);
	}
	
	public UserDto findById(final Long id){
		User user = userDao.findById(id);
		UserDto dto = UserDto.EntityToDto(user);
		return dto;
	}

	public List<UserDto> findAll() {
		logger.info("service info findALl");
		List<User> users = userDao.findAllUsers();
		 List<UserDto> dtos = new ArrayList<UserDto>();
		 for (User user : users) {
			 UserDto dto = UserDto.EntityToDto(user);
			 dtos.add(dto);
		}
		return dtos;
	}

	public Page<UserDto> findByPage(int firstResult, int maxResults) {
		Page<User> userPage = userDao.findForPageByHql(firstResult, maxResults, " From User", null);
		 List<UserDto> dtos = new ArrayList<UserDto>();
		 for (User user : userPage.getObjectList()) {
			 UserDto dto = UserDto.EntityToDto(user);
			 dtos.add(dto);
		}
		 Page<UserDto> userDtoPage = new Page<UserDto>(userPage.getResultCount(), maxResults, dtos);
		return userDtoPage;
	}

	@Override
	public UserDto login(String name, String paswd) {
		// check LDAP
//		LdapHandlerUtil ldapUtil = new LdapHandlerUtil(url, dn, dpassword);
		if(LdapHandlerUtil.getUrl() == null || "".equals(LdapHandlerUtil.getUrl())){
			LdapHandlerUtil.innit(url, dn, dpassword,paswdAtr,factory,searchBase,searchFilter);
		}
		boolean ldapret = LdapHandlerUtil.authenticate(name, paswd);
		if (ldapret) {
			// check database
			User user = userDao.findUniqueByProperty("name", name);
			UserDto dto = UserDto.EntityToDto(user);
			return dto;
		}
		return null;
	}

}
