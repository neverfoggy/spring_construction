package com.kursach.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

// Our own implementation of the Spring Security User.

public class MyUser extends User {

    // Here we add the extra fields of our users.
    private String name;
    private static final long serialVersionUID = 1L;

    public MyUser(String username,
                  String password,
                  Collection<? extends GrantedAuthority> authorities,
                  String name) {
        super(username, password, authorities);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}