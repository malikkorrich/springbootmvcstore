package com.store.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.store.spring.models.Usuario;
import com.store.spring.repositories.UserRepository;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Service("UserDetailsService")
public class UserDetailsServiceImp implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 Usuario user = userRepository.findFirstByEmail(username);
		 
		 UserBuilder builder = null;
		 
		 if( user != null ) {
			 
			 builder = User.withUsername(username);
			 builder.disabled(false);
			 builder.password(user.getPassword());
			 builder.authorities(new SimpleGrantedAuthority("USER_ROLE"));
			 
		 }else {
			 throw new UsernameNotFoundException("Username not found ");
		 }
		
		return builder.build();
	}

}
