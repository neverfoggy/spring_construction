package com.kursach.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "request")
public class DAORequest {

    public DAORequest(){}

    public DAORequest(String build, Integer dept_id) {
        this.build = build;
        this.dept_id = dept_id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "req_id")
    private Integer id;

    @Column(name = "building", length = 100)
    private String build;


    @Column(nullable = false)
    private Boolean ready = false;

    public Boolean getReady() {
        return ready;
    }

    public void setReady(Boolean ready) {
        this.ready = ready;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dept_id",insertable = false, updatable = false)
    private DAODept daoDept;

    @OneToMany(mappedBy = "daoRequest")
    private Set<DAOMaster> daoMasters;

    @Column
    private Integer dept_id;

    @Temporal(TemporalType.TIMESTAMP)
   private Date date = new Date();

    public java.util.Date getCreationDateTime() {
        return date;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public DAODept getDaoDept() {
        return daoDept;
    }

    public void setDaoDept(DAODept daoDept) {
        this.daoDept = daoDept;
    }

    public long getDept_id() {
        return dept_id;
    }

    public void setDept_id(Integer dept_id) {
        this.dept_id = dept_id;
    }

//    public void setId(long id) {
//        this.id = id;
//    }

    public Set<DAOMaster> getDaoMasters() {
        return daoMasters;
    }

    public void setDaoMasters(Set<DAOMaster> daoMasters) {
        this.daoMasters = daoMasters;
    }


}
