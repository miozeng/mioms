package com.mioms.core.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mioms.core.entity.User;


@Repository
public class UserDaoImpl extends BaseDaoImpl<User, Long> implements UserDao {
	private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);
	@Override
	public List<User> findAllUsers() {
		logger.info("dao info findALl");
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createQuery("from User");
		return query.list();
	}

	@Override
	public List<User> findUsersByAge(String age) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery("{CALL proc_find_user(?)}").addEntity(User.class);
		query.setString(0, age);
		List<User> list = query.list();
		return list;
	}


}
