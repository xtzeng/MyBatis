package com.immutable.alias.dao;

import org.springframework.stereotype.Repository;

import com.immutable.alias.pojos.User;


@Repository("userMapper")
public interface UserMapper {
	public User selectUser(User user);
	public void insertUser(User user);
	public void updateUser(User user);
	public void deleteUser(int userId);
	public User selectOneUser(String userId);
}
