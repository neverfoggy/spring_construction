package com.kursach.dao;

import com.kursach.model.DAOElementType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElemTypeDao extends JpaRepository<DAOElementType, Integer> {
}
