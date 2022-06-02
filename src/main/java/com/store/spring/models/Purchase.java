package com.store.spring.models;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Purchase {
	
	/**
	 * @author Malik Korrich
	 */
	

	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@CreatedDate
	@Temporal(TemporalType.TIME)
	private Date orderOn;
	private String status;
	
	
	@ManyToOne
	private Usuario usuario;
	
	
	public Purchase() {}


	public Purchase(String status, Usuario usuario) {
		super();
		this.status = status;
		this.usuario = usuario;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Date getOrderOn() {
		return orderOn;
	}


	public void setOrderOn(Date orderOn) {
		this.orderOn = orderOn;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, orderOn, status, usuario);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Purchase other = (Purchase) obj;
		return id == other.id && Objects.equals(orderOn, other.orderOn) && Objects.equals(status, other.status)
				&& Objects.equals(usuario, other.usuario);
	}


	@Override
	public String toString() {
		return "Order [id=" + id + ", orderOn=" + orderOn + ", status=" + status + ", user=" + usuario + "]";
	}
	
	
	
	
	
	
}
