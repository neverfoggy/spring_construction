package com.kursach.model;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "reqmaster")
public class DAOMaster {

    public DAOMaster(Short amount) {
        this.amount = amount;
    }

    public  DAOMaster() {}

    public DAOMaster(Integer attr_id, Short amount, Integer reqId) {
        this.amount = amount;
        this.attr_id = attr_id;
        this.reqId = reqId;
    }

    public DAORequest getDaoRequest() {
        return daoRequest;
    }

    public void setDaoRequest(DAORequest daoRequest) {
        this.daoRequest = daoRequest;
    }

    public Integer getReqId() {
        return reqId;
    }

    public void setReqId(Integer reqId) {
        this.reqId = reqId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rm_id")
    private Integer id;

    @Column(columnDefinition = "SMALLINT")
    @Type(type = "org.hibernate.type.ShortType")
    private Short amount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wh_id", insertable = false, updatable = false)
    private DAOAttributes daoAttributes;

    @Column(name = "wh_id")
    private Integer attr_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "req_Id", insertable = false, updatable = false)
    private DAORequest daoRequest;

    @Column(name = "req_Id")
    private Integer reqId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getAmount() {
        return amount;
    }

    public void setAmount(Short amount) {
        this.amount = amount;
    }

    public DAOAttributes getDaoAttributes() {
        return daoAttributes;
    }

    public void setDaoAttributes(DAOAttributes daoAttributes) {
        this.daoAttributes = daoAttributes;
    }

    public Integer getAttr_id() {
        return attr_id;
    }

    public void setAttr_id(Integer attr_id) {
        this.attr_id = attr_id;
    }
}
