package com.kursach.dao;

import com.kursach.model.DAOElement;
import com.kursach.model.DAORequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RequestDao extends JpaRepository<DAORequest, Integer>, JpaSpecificationExecutor<DAORequest> {

    DAORequest findFirstByOrderByIdDesc();

    Page<DAORequest> findAll(Pageable pageable);

    Page<DAORequest> findAllByReady(Boolean ready,Pageable pageable);

    Page<DAORequest> findByReady(Boolean bool, Pageable pageable);




}
