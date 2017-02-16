package com.mioms.core.dao;

import java.util.List;

import com.mioms.core.entity.Menu;


public interface MenuDao extends BaseDao<Menu, Long> {
	
	public List<Menu> findByType(String type);

}
