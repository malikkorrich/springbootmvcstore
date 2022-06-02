package com.store.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsServiceImp userDetailsServiceImp;
	
 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.userDetailsService(userDetailsServiceImp).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http
		 .authorizeRequests()
		 .antMatchers("/", "/webjars/**", "/css/**", "/images/**","/js/**","/fonts**",  "/h2-console/**","/product/**","/register/**","/about/**","/contact/**","/auth/**", "/files/**").permitAll()
		 .anyRequest().authenticated().and()
		 .formLogin()
			.loginPage("/auth/login")  
			.defaultSuccessUrl("/", true)  
			.loginProcessingUrl("/auth/login")  
			.permitAll()
			.and()
		.logout()
			.logoutUrl("/auth/logout")   
			.logoutSuccessUrl("/");  

	http.csrf().disable();
	http.headers().frameOptions().disable();
	}

	
	
	
}
