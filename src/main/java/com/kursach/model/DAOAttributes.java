package com.kursach.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "warehouse")
public class DAOAttributes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wh_id")
    private Integer id;

    @Column(length = 30)
    private String value;

    public Short getAmount() {
        return amount;
    }

    public void setAmount(Short amount) {
        this.amount = amount;
    }

    @Column(columnDefinition = "SMALLINT")
    @Type(type = "org.hibernate.type.ShortType")
    private Short amount = 0;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ea_id", insertable = false, updatable = false)
    private DAOElemAttributes daoElemAttributes;

    @Column(name = "ea_id")
    private Integer attr_id;

    public Integer getAttr_id() {
        return attr_id;
    }

    public void setAttr_id(Integer attr_id) {
        this.attr_id = attr_id;
    }

    @OneToMany(mappedBy = "daoAttributes")
    private Set<DAOMaster>  daoMaster;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public DAOElemAttributes getDaoElemAttributes()
    {
        return daoElemAttributes;
    }

    public long getDaoElemAttributesId()
    {
        return daoElemAttributes.getId();
    }

    public void setDaoElemAttributes(DAOElemAttributes daoElemAttributes) {
        this.daoElemAttributes = daoElemAttributes;
    }
}
