package com.mioms.core.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mioms.core.entity.Menu;


@Repository
public class MenuDaoImpl extends BaseDaoImpl<Menu, Long> implements MenuDao {

	
	public List<Menu> findByType(String type) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.enableFilter("menuTypeFilter").setParameter("menuType", type);
		Query query = session.createQuery("from Menu where type = :menuType");
		query.setParameter("menuType", type);
		return query.list();
	}

	
}
