package com.kursach.service;

import java.util.*;

import com.kursach.dao.RoleDao;
import com.kursach.model.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kursach.dao.UserDao;
import com.kursach.model.DAOUser;
import com.kursach.model.UserDTO;

import java.util.stream.*;


@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		DAOUser user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new MyUser(user.getUsername(), user.getPassword(),getAuthorities(user.getRoles()),"Здравствуйте, " + user.getName());
	}
	private Collection<? extends GrantedAuthority> getAuthorities(Set<Roles> roles) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Roles role: roles){
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}

		return authorities;
	}



	
	public DAOUser save(UserDTO user) {
		DAOUser newUser = new DAOUser();

		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setName(user.getName());
		newUser.setRoles(Stream.of(user.getRoles()).map(x -> roleDao.findByName(x)).collect(Collectors.toSet()));
		return userDao.save(newUser);
	}
}