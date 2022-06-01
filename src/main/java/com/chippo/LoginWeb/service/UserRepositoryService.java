package com.chippo.LoginWeb.service;

import java.util.List;

import com.chippo.LoginWeb.model.UserDTO;

public interface UserRepositoryService {
	
	void add(UserDTO userDTO);

	void delete(long id);

	void update(UserDTO userDTO);

	UserDTO getUserById(long id);
	
	UserDTO getUserByPhone(long phone);
	
	UserDTO getUserByUserName(String username);

	List<UserDTO> getAll();
}
