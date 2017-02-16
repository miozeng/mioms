package com.mios.ws.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mioms.core.dao.UserDao;
import com.mioms.core.entity.User;
import com.mioms.core.util.Page;



//
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/application-config-hibernate.xml")
public class UserDaoTest {

	@Resource
	private UserDao userDao;
	
	@Before
	public void ints(){
		
		
		// find methods using
		List<User> auser = new ArrayList<User>();
		for (int i =0;i<10; i++) {
			User user = new User();
			user.setName("finduser");
			user.setAge("20");
			auser.add(user);
		}
		userDao.saveList(auser);
	}
	
	@After
	public void destory(){
		//delete history data
		List<User> users = userDao.findByProperty("name", "finduser");
		if(users != null && users.size()>0){
			for(User user : users){
				userDao.delete(user);
			}
			
		}
		
		//delete history data
				List<User> users1 = userDao.findByProperty("name", "testuser");
				if(users1 != null && users1.size()>0){
					for(User user : users1){
						userDao.delete(user);
					}
					
				}
				List<User> users2 = userDao.findByProperty("name", "updatename");
				if(users2 != null && users2.size()>0){
					for(User user : users2){
						userDao.delete(user);
					}
					
				}
	}
	
	@Test
	@Transactional
	public void testSave(){
		User user = new User();
		user.setName("testuser");
		user.setAge("20");
		userDao.save(user);
		List<User> users2= userDao.findByProperty("name", "testuser");
		Assert.assertTrue(users2.size()>0);
		
	}
	
//	@Test
//	@Transactional
//	public void testProcedure (){
//		List<User> users2= userDao.findUsersByAge("20");
//		Assert.assertTrue(users2.size()==10);
//		
//	}
	
	@Test
	@Transactional
	public void testSaveList(){
		List<User> auser = new ArrayList<User>();
		for (int i =0;i<2; i++) {
			User user = new User();
			user.setName("testuser");
			user.setAge("20");
			auser.add(user);
		}
		userDao.saveList(auser);
		
		List<User> users2= userDao.findByProperty("name", "testuser");
		Assert.assertTrue(users2.size()==2);
	}
	
	@Test
	@Transactional
	public void testSaveOrUpdate(){
		User user = new User();
		user.setName("testuser");
		user.setAge("20");
		userDao.saveOrUpdate(user);
		List<User> users2= userDao.findByProperty("name", "testuser");
		Assert.assertTrue(users2.size()==1);
		User tuser = users2.get(0);
		tuser.setName("updatename");
		userDao.saveOrUpdate(user);
		List<User> users3= userDao.findByProperty("name", "updatename");
		Assert.assertTrue(users3.size()>0);
	}

	@Test
	@Transactional
	public void testDeleteById(){
		User user = new User();
		user.setName("testuser");
		user.setAge("20");
		userDao.saveOrUpdate(user);
		List<User> users2= userDao.findByProperty("name", "testuser");
		userDao.deleteById(users2.get(0).getId());
		List<User> users3= userDao.findByProperty("name", "testuser");
		Assert.assertTrue(users3.size()==0);
	}
	
	@Test
	@Transactional
	public void testUpdate(){
		User user = new User();
		user.setName("testuser");
		user.setAge("20");
		userDao.save(user);
		List<User> users2= userDao.findByProperty("name", "testuser");
		User tuser = users2.get(0);
		tuser.setName("updatename");
		userDao.update(user);
		List<User> users3= userDao.findByProperty("name", "updatename");
		Assert.assertTrue(users3.size()>0);
	}
	
	@Test
	@Transactional
	public void testUpdate2(){
		User user = new User();
		user.setName("testuser");
		user.setAge("20");
		userDao.save(user);
		userDao.update(" update User set name ='updatename' where name=? ", "testuser");
		
		List<User> users= userDao.findByProperty("name", "updatename");
		Assert.assertTrue(users.size()>0);
		
	}
	
	@Test
	@Transactional
	public void testFindById(){
		List<User> users= userDao.findByProperty("name", "finduser");
		User user = userDao.findById(users.get(0).getId());
		Assert.assertNotNull(user);
	}
	
	@Test
	@Transactional
	public void testFindAll(){
		List<User> users = userDao.findAll();
		for(User user : users){
			System.out.println(user.getName());
		}
		Assert.assertTrue(users.size()>0);
	}
	
	@Test
	@Transactional
	public void testFindByProperty(){
		List<User> users= userDao.findByProperty("name", "finduser");
		Assert.assertNotNull(users);
	}
	
	@Test
	@Transactional
	public void testFindUniqueByProperty(){
		User user = new User();
		user.setName("mioonly");
		user.setAge("20");
		userDao.save(user);
		User user2= userDao.findUniqueByProperty("name", "mioonly");
		Assert.assertNotNull(user2);
		userDao.delete(user2);
	}
	
	
	@Test
	@Transactional
	public void testFindUniqueByHql(){
		User user = new User();
		user.setName("mioonly");
		user.setAge("20");
		userDao.save(user);
		User user2= userDao.findUniqueByHql(" from User where name= ? and age =?", new Object[]{"mioonly","20"});
		Assert.assertNotNull(user2);
		userDao.delete(user2);
	}
	
	@Test
	@Transactional
	public void testFindByHql() {
		User user = new User();
		user.setName("mioonly");
		user.setAge("20");
		userDao.save(user);
		List<User> user2 = userDao.findByHql(" from User where name= ? ", "mioonly");
		Assert.assertTrue(user2.size()>0);
		for (User usear : user2) {
			userDao.delete(usear);
			
		}
		
	}
	
	@Test
	@Transactional
	public void testFindForPageByHql(){
		
		Page<User> userPage = userDao.findForPageByHql(0, 3, " from User where name= ? ", "finduser");
		Assert.assertTrue(userPage.getObjectList().size()==3);
	}
	
	@Test
	@Transactional
	public void testFindForListByHql(){
		
		List<User> userList = userDao.findForListByHql(0, 3, " from User where name= ? ", "finduser");
		Assert.assertTrue(userList.size()==3);
	}
}
