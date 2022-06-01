package com.chippo.LoginWeb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chippo.LoginWeb.entity.User;
import com.chippo.LoginWeb.model.UserDTO;
import com.chippo.LoginWeb.repository.UserRepository;
import com.chippo.LoginWeb.service.UserRepositoryService;

@Service
public class UserRepositoryServiceImpl implements UserRepositoryService{

	@Autowired
	UserRepository UserRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	@Override
	public void add(UserDTO userDTO) {
		// TODO Auto-generated method stub
		User user = new User();

		user.setEmail(userDTO.getEmail());
		user.setUsername(userDTO.getUsername());
		
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		user.setPhone(userDTO.getPhone());
		
		UserRepository.save(user);
		userDTO.setId(user.getId());
	}

	@Override
	public void delete(long id) {
		User user = UserRepository.findById(id).orElse(null);
		if (user != null) {
			UserRepository.delete(user);
		}
	}

	@Override
	public void update(UserDTO userDTO) {
		User user = UserRepository.findById(userDTO.getId()).orElse(null);
		if (user != null) {
			user.setEmail(userDTO.getEmail());
			user.setUsername(userDTO.getUsername());
			user.setPassword(userDTO.getPassword());
			user.setPhone(userDTO.getPhone());

			UserRepository.save(user);
		}
	}

	@Override
	public UserDTO getUserById(long id) {
		User user = UserRepository.findById(id).orElse(null);
		if (user != null) {
			UserDTO userDTO = new UserDTO();
			userDTO.setId(user.getId());
			userDTO.setEmail(user.getEmail());
			userDTO.setUsername(user.getUsername());
			userDTO.setPassword(user.getPassword());
			userDTO.setPhone(user.getPhone());

			return userDTO;
		}
		return null;
	}

	@Override
	public UserDTO getUserByPhone(long phone) {
		User user = UserRepository.findById(phone).orElse(null);
		if (user != null) {
			UserDTO userDTO = new UserDTO();
			userDTO.setId(user.getId());
			userDTO.setEmail(user.getEmail());
			userDTO.setUsername(user.getUsername());
			userDTO.setPassword(user.getPassword());
			userDTO.setPhone(user.getPhone());

			return userDTO;
		}
		return null;
	}

	@Override
	public UserDTO getUserByUserName(String username) {
		User user = UserRepository.findByUsername(username);
		if (user != null) {
			UserDTO userDTO = new UserDTO();
			userDTO.setId(user.getId());
			userDTO.setEmail(user.getEmail());
			userDTO.setUsername(user.getUsername());
			userDTO.setPassword(user.getPassword());
			userDTO.setPhone(user.getPhone());

			return userDTO;
		}
		return null;
	}

	@Override
	public List<UserDTO> getAll() {
		
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		List<User> users = UserRepository.findAll();
		
		for (User user : users) {
			UserDTO userDTO = new UserDTO();
			userDTO.setId(user.getId());
			userDTO.setEmail(user.getEmail());
			userDTO.setUsername(user.getUsername());
			userDTO.setPassword(user.getPassword());
			userDTO.setPhone(user.getPhone());

			userDTOs.add(userDTO);
		}
		return userDTOs;
	}

	
}
