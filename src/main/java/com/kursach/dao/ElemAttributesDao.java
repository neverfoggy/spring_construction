package com.kursach.dao;

import com.kursach.model.DAOElemAttributes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElemAttributesDao extends JpaRepository<DAOElemAttributes, Integer> {
}
