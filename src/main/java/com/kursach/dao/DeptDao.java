package com.kursach.dao;

import com.kursach.model.DAODept;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptDao extends JpaRepository<DAODept, Integer> {

}
