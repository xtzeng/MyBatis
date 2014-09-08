package com.immutable.alias.spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.immutable.alias.dao.UserMapper;
import com.immutable.alias.pojos.User;

public class MyBatisTest {

	@Test
	public void testSelect() {
			ApplicationContext ctx=null;  
	        ctx=new ClassPathXmlApplicationContext("applicationContext.xml");  
	        UserMapper userMapper=(UserMapper)ctx.getBean("userMapper");  
	        User u=new User();  
	        u.setUsername("admin");  
	        u.setPassword("admin");  
	        System.out.println(userMapper.selectUser(u));  
	        
	        User u2 =(User) userMapper.selectUser(u);
	        System.out.println(u2);
	        //插入（去掉下面的注释进行调试）  
	        /* 
	        User insertUser=new User(); 
	        insertUser.setUsername("testUsername"); 
	        insertUser.setPassword("testPassword"); 
	        userMapper.insertUser(insertUser); 
	        */  
	        //更新（去掉下面的注释进行调试）  
	        /* 
	        u.setId(1); 
	        u.setPassword("updatePassword"); 
	        userMapper.updateUser(u); 
	        */  
	        //删除（去掉下面的注释进行调试）  
	        /* 
	        userMapper.deleteUser(9); 
	        */  
	}
	
	@Test
	public void testExtends() {
		ApplicationContext ctx=null;  
        ctx=new ClassPathXmlApplicationContext("applicationContext.xml");  
        UserMapper userMapper=(UserMapper)ctx.getBean("userMapper"); 
        User user = userMapper.selectOneUser("2");
        System.out.println(user);
	}
}
