package com.kursach.dao;

import com.kursach.model.DAOMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;

public interface MasterEntityDao extends JpaRepository<DAOMaster, Integer> {

    String myquery = "SELECT del.name, da.value, dm.amount FROM DAOMaster dm JOIN dm.daoAttributes da JOIN da.daoElemAttributes de JOIN de.daoElement del WHERE dm.reqId = :reqId";

    @Query(value = myquery)
    Page<DAOMaster> findAllByreqId(@Param(value = "reqId") Integer reqId, Pageable pageable);

    String myquery1 = "SELECT dr.id, dr.build, dm.amount, da.value, de.name FROM DAORequest dr JOIN dr.daoMasters dm JOIN dm.daoAttributes da JOIN da.daoElemAttributes dea JOIN dea.daoElement de WHERE dr.ready = false GROUP BY dm.id, dr.id ";

    @Query(value = myquery1)
    Page<DAOMaster> findGroupByAttr(Pageable pageable);






}
