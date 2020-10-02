package com.kursach.controller;

import java.net.HttpCookie;

import com.kursach.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.kursach.service.JwtUserDetailsService;


import com.kursach.config.JwtTokenUtil;
import com.kursach.model.JwtRequest;
import com.kursach.model.UserDTO;

@Controller
@CrossOrigin
public class JwtAuthenticationController {
	@Autowired
	private RoleDao roleDao;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

       final HttpCookie cookie = new HttpCookie("session", token);

        cookie.setMaxAge(-1); // expires in 7 days
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");


//    System.out.println(cookie.toString());
		return ResponseEntity.status(HttpStatus.FOUND).header("Location", "/success").header(HttpHeaders.SET_COOKIE, cookie.toString()).build();
	}
	
	@RequestMapping( value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(UserDTO user) throws Exception {
		return ResponseEntity.ok(userDetailsService.save(user));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}