package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {
	User saveUser(User user);
	User getUserById(Long id);
	User getUserByEmail(String email);
}
