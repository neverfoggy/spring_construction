package com.kursach.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "department")
public class DAODept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_id")
    private Integer id;

    @Column(length = 30)
    private String name;

    @OneToMany(mappedBy = "daoDept")
    private Set<DAORequest> daoMaster;

    @OneToMany(mappedBy = "daoDept")
    private Set<DAOUser> daoUser;

    public Set<DAORequest> getDaoMaster() {
        return daoMaster;
    }

    public void setDaoMaster(Set<DAORequest> daoMaster) {
        this.daoMaster = daoMaster;
    }

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

    public Set<DAOUser> getDaoUser() {
        return daoUser;
    }

    public void setDaoUser(Set<DAOUser> daoUser) {
        this.daoUser = daoUser;
    }
}
