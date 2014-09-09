package com.immutable.alias.dao;

import com.immutable.alias.pojos.User;

public interface UserDao {
	public User selectOnePerson(String userId);
}
