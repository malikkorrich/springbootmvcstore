package com.store.spring.models;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Usuario {
	
	/**
	 * @author Malik Korrich
	 */
	

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotEmpty(message = "User's name cannot be empty.")
	private String name;
	@NotEmpty(message = "User's email cannot be empty.")
	@Email(message = "User's email not valid.")
	private String email;
	@NotEmpty(message = "User's password cannot be empty.")
	@Size(min = 6)
	private String password;
	private String avatar;
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	
	public Usuario() {}
	
	
	public Usuario(@NotEmpty String name, @NotEmpty String email, @NotEmpty String password, String avatar) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.avatar = avatar;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getAvatar() {
		return avatar;
	}


	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}


	public Date getDate() {
		return createdOn;
	}


	public void setDate(Date date) {
		this.createdOn = date;
	}


	@Override
	public int hashCode() {
		return Objects.hash(avatar, createdOn, email, id, name, password);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(avatar, other.avatar) && Objects.equals(createdOn, other.createdOn)
				&& Objects.equals(email, other.email) && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password);
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", avatar="
				+ avatar + ", date=" + createdOn + "]";
	}





	
	
	
	
	
}
