package com.kursach.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "element")
public class DAOElement {

    public DAOElement() {}

    public DAOElement(String name, Integer elem_type_id) {
        this.name = name;
        this.elem_type_id = elem_type_id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "elem_id")
    private Integer id;

    @Column(length = 30)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "et_id", insertable = false, updatable = false)
    private DAOElementType daoElementType;

    @Column(name = "et_id")
    private Integer elem_type_id;

    public Integer getElem_type_id() {
        return elem_type_id;
    }

    public void setElem_type_id(Integer elem_type_id) {
        this.elem_type_id = elem_type_id;
    }

    @OneToMany(mappedBy = "daoElement")
    private Set<DAOElemAttributes> daoElemAttributes;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DAOElementType getDaoElementType() {
        return daoElementType;
    }

    public long getDaoElementTypeId() {
        return daoElementType.getId();
    }

    public void setDaoElementType(DAOElementType daoElementType) {
        this.daoElementType = daoElementType;
    }

    public Set<DAOElemAttributes> getDaoElemAttributes() {
        return daoElemAttributes;
    }

    public void setDaoElemAttributes(Set<DAOElemAttributes> daoElemAttributes) {
        this.daoElemAttributes = daoElemAttributes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
