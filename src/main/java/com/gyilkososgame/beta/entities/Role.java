package com.gyilkososgame.beta.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "ROLES")
@NamedQuery(name = "Role.findAll", query = "Select R from Role R")
public class Role {

	@Id
	@Column(name = "Name")
	private String name;
	@Column(name = "Role")
	private String role;


	@Override
	public String toString() {
		return "Users [name=" + name + ", role=" + role + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Role() {
			 
		 }
	public Role(Users user) {
		 
	 }

	public Role(String name, String role) {
		// TODO Auto-generated cnstructor stub
	}

}
