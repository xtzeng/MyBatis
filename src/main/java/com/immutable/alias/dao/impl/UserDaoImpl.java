package com.immutable.alias.dao.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.immutable.alias.dao.UserDao;
import com.immutable.alias.pojos.User;

@Repository("userDaoImpl")
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao{

	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	
	@Override
	public User selectOnePerson(String userId) {
		
		java.util.Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		User u = null;
		u = sqlSession.selectOne("com.immutable.alias.dao.UserDao.selectOnePerson", param);
 		return u;
	}

}
