package com.immutable.alias.service;

import javax.annotation.Resource;
import javax.sound.midi.VoiceStatus;

import org.springframework.stereotype.Service;

import com.immutable.alias.dao.UserDao;
import com.immutable.alias.dao.UserMapper;
import com.immutable.alias.pojos.User;

@Service("testService")
public class TestService {
	
	@Resource(name="userDaoImpl")
	private UserDao userDaoImpl;
	
	@Resource(name="userMapper")
	private UserMapper userMapper;
	
	public void testService() {
		userDaoImpl.selectOnePerson("2");
		System.out.println("hello,this is service!!");
	}
	
	
	public void testInterface() {
		User user = new User();
		user.setPassword("admin");
		user.setUsername("admin");
		User user2 = null;
		user2 = userMapper.selectUser(user);
	}
}
