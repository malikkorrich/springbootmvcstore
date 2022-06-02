package com.store.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.store.spring.models.Usuario;
import com.store.spring.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	public Usuario add(Usuario user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
		
	}

	public Usuario getByEmail(String email) {
		
		return userRepository.findFirstByEmail(email);
	}
	
	
	public void delete(long id) {
		
		  userRepository.deleteById(id);
	}
	
	public void delete(Usuario user) {
		
		  userRepository.delete(user);
		  
	}

	public Usuario getById(long id) {
		return userRepository.getById(id);
	}
	
	public int Update(String name,String email, String password, String avatar ,long id) {
		return userRepository.update(name,email,password,avatar,id);
	}

	public BCryptPasswordEncoder getpasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
