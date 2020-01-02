package com.gyilkososgame.beta.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "APPUSER")
@NamedQuery(name = "Users.findAll", query = "Select C from Users C")
public class Users {

	@Id
	@Column(name = "Name")
	private String name;
	@Column(name = "Password")
	private String pw;


	@Override
	public String toString() {
		return "Users [name=" + name + ", pw=" + pw + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public Users() {
			 
		 }
	public Users(Users user) {
		 
	 }

	public Users(String name, String pw) {
		// TODO Auto-generated cnstructor stub
	}

}
