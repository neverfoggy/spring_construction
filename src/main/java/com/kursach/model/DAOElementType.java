package com.kursach.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "elemtype")
public class DAOElementType {

    public DAOElementType() {    }

    public DAOElementType(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "et_id")
    private Integer id;

    @Column(length = 30)
    private String name;

    @OneToMany(mappedBy = "daoElementType")
    private Set<DAOElement> daoElement;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<DAOElement> getDaoElement() {
        return daoElement;
    }

    public void setDaoElement(Set<DAOElement> daoElement) {
        this.daoElement = daoElement;
    }
}
