package com.immutable.alias.dao;

import com.immutable.alias.pojos.User;



public interface UserMapper {
	public User selectUser(User user);
	public void insertUser(User user);
	public void updateUser(User user);
	public void deleteUser(int userId);
	public User selectOneUser(String userId);
}
