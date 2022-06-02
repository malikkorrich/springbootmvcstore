package com.store.spring.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class Address {
	
	/**
	 * @author Malik Korrich
	 */
	

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotEmpty(message = "User's region cannot be empty.")
	private String region;
	@NotEmpty(message = "User's city cannot be empty.")
	private String city;
	@NotEmpty(message = "User's address cannot be empty.")
	private String address;
	
	@ManyToOne
	private Usuario usuario;
	
	public Address() {}

	public Address(@NotEmpty String region, @NotEmpty String city, @NotEmpty String address, Usuario usuario) {
		super();
		this.region = region;
		this.city = city;
		this.address = address;
		this.usuario = usuario;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, city, id, region, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(address, other.address) && Objects.equals(city, other.city) && id == other.id
				&& Objects.equals(region, other.region) && Objects.equals(usuario, other.usuario);
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", region=" + region + ", city=" + city + ", address=" + address + ", usuario="
				+ usuario + "]";
	}

	
	
	
	
	
}