package com.immutable.alias.spring;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.immutable.alias.dao.UserDao;
import com.immutable.alias.dao.impl.UserDaoImpl;
import com.immutable.alias.pojos.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml") 
public class UserDaoImplTest extends  AbstractJUnit4SpringContextTests  {

	@Resource(name="userDaoImpl")
	private UserDao userDaoImpl;
	
	@Test
	public void testImpl() {
		//UserDaoImpl userDaoImpl = new UserDaoImpl();
		User user = null;
		user = userDaoImpl.selectOnePerson("2");
	}
}
