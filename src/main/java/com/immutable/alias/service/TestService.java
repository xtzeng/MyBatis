package com.immutable.alias.service;

import javax.annotation.Resource;
import javax.sound.midi.VoiceStatus;

import org.springframework.stereotype.Service;

import com.immutable.alias.dao.UserDao;

@Service("testService")
public class TestService {
	
	@Resource(name="userDaoImpl")
	private UserDao userDaoImpl;
	
	public void testService() {
		userDaoImpl.selectOnePerson("2");
		System.out.println("hello,this is service!!");
	}
}
