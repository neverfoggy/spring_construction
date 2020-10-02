package com.kursach.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
public class DAOUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer id;
	@Column
	private String username;
	@Column
	@JsonIgnore
	private String password;

	@Column(length = 30)
	private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dept_id", referencedColumnName = "dept_id")
    private DAODept daoDept;

	@JsonIgnore
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
			name = "user_role",
			joinColumns = @JoinColumn(
			name = "user_id", referencedColumnName = "user_id"),
			inverseJoinColumns = @JoinColumn(
					name = "role_id", referencedColumnName = "role_id"))
	private Set<Roles> roles;

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}