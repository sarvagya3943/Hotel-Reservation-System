package com.springmvc.service;

import java.util.ArrayList;

import com.springmvc.model.User;

public interface UserService {
	
	public boolean doesUserExist(User user) ;
	int saveUser(User user) ;
	public User findByEmail(String email);
	public User findByID(int user_id) ;
	public ArrayList<User> findAllUsers() ;
	
}
