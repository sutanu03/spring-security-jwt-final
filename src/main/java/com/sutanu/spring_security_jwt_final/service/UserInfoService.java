package com.sutanu.spring_security_jwt_final.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sutanu.spring_security_jwt_final.entity.UserInfo;
import com.sutanu.spring_security_jwt_final.repository.UserInfoRepo;

@Service
public class UserInfoService implements UserDetailsService{

	@Autowired
	private UserInfoRepo userInfoRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> userInfo = userInfoRepo.findByName(username);
		return userInfo.map(UserInfoDetails::new)
				.orElseThrow(()-> new UsernameNotFoundException("User not found "+username));
	}
	
	// register user
	public String addUser(UserInfo userInfo) {
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		userInfoRepo.save(userInfo);
		return "User created succesfully";
	}
	
	// get all users
	public List<UserInfo> getAllUsers(){
		return userInfoRepo.findAll();
	}
	
	//get user using id
	public UserInfo getUser(Integer id) {
		return userInfoRepo.findById(id).get();
	}

}
