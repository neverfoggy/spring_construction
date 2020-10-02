package com.kursach.dao;

import com.kursach.model.DAOAttributes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AttributesDao extends JpaRepository<DAOAttributes,Integer> {
    String myquery1 = "SELECT da.id, de.name, da.value, da.amount FROM DAOAttributes da JOIN da.daoElemAttributes dea JOIN dea.daoElement de";
    @Query(value = myquery1)
    Page<DAOAttributes> findAllAttrs(Pageable pageable);
}
