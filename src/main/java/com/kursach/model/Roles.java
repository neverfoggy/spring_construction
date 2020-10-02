package com.kursach.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Integer id;

    @Column(length = 30)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<DAOUser> users;

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

    public Set<DAOUser> getUsers() {
        return users;
    }

    public void setUsers(Set<DAOUser> users) {
        this.users = users;
    }
}
