package com.kursach.dao;

import com.kursach.model.DAOElement;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ElemDao extends JpaRepository<DAOElement, Integer> {



}
