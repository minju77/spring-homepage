package com.example.demo.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public void signup(User user) {
		userRepository.save(user);
	}
	
	public User login(String username, String password) {
		User user = userRepository.findByUsername(username);
		
		if(user != null && user.getPassword().equals(password)) {
			return user;
		}
		
		return null;
	}
}
