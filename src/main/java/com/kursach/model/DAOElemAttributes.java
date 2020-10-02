package com.kursach.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "element_attribute")
public class DAOElemAttributes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ea_id")
    private Integer id;

    @Column(name = "name", length = 30)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "elem_id", insertable = false, updatable = false)
    private DAOElement daoElement;

    @Column
    private Integer elem_id;

    public Integer getElem_id() {
        return elem_id;
    }

    public void setElem_id(Integer elem_id) {
        this.elem_id = elem_id;
    }

    @OneToMany(mappedBy = "daoElemAttributes")
    private Set<DAOAttributes> daoAttributes;

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

    public DAOElement getDaoElement() {
        return daoElement;
    }

    public long getDaoElementId() {
        return daoElement.getId();
    }

    public void setDaoElement(DAOElement daoElement) {
        this.daoElement = daoElement;
    }
}
