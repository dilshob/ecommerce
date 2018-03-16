package com.dil.ecommerce.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
	DataSource dataSource;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
    	return NoOpPasswordEncoder.getInstance();
    }
	
	 @Autowired
	 public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
	   auth.jdbcAuthentication().dataSource(dataSource)
	  .usersByUsernameQuery(
	   "select username,password, enabled from users where username=?")
	  .authoritiesByUsernameQuery(
	   "select username, role from user_roles where username=?");
	 } 
	 
	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
	   http.authorizeRequests()
		  .antMatchers("/static/css/**").permitAll()
		  .antMatchers("/ecommerce/h2/**").permitAll()
		  .antMatchers("/adminHome").access("hasRole('ROLE_ADMIN')")   
		  .antMatchers("/userHome").access("hasRole('ROLE_CUSTOMER')")  
		  .antMatchers("/userHome").access("hasRole('ROLE_VISITOR')")  
		  .anyRequest().permitAll()
		  .and()
		    .formLogin().loginPage("/login")
		    .usernameParameter("username").passwordParameter("password")
		    
		  .and()
			 .exceptionHandling().accessDeniedPage("/403")
		  .and()
		    .csrf();
	 }
}