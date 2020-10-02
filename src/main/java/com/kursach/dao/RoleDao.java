package com.kursach.dao;

import com.kursach.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Roles, Integer> {
    Roles findByName(String name);
}
