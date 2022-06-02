package com.store.spring.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class Product {
	/**
	 * @author Malik Korrich
	 */
	
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotEmpty
	private String name;
	private String description;
	private String category;
	
	private float price;
	@NotEmpty
	private String image;
	
	private int quantity;
	
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Purchase order;

	public Product() {}

	public Product(@NotEmpty String name, String description, String category, float price, @NotEmpty String image,
			int quantity, Usuario usuario, Purchase order) {
		super();
		this.name = name;
		this.description = description;
		this.category = category;
		this.price = price;
		this.image = image;
		this.quantity = quantity;
		this.usuario = usuario;
		this.order = order;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Purchase getOrder() {
		return order;
	}

	public void setOrder(Purchase order) {
		this.order = order;
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, description, id, image, name, order, price, quantity, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(category, other.category) && Objects.equals(description, other.description)
				&& id == other.id && Objects.equals(image, other.image) && Objects.equals(name, other.name)
				&& Objects.equals(order, other.order)
				&& Float.floatToIntBits(price) == Float.floatToIntBits(other.price) && quantity == other.quantity
				&& Objects.equals(usuario, other.usuario);
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", category=" + category
				+ ", price=" + price + ", image=" + image + ", quantity=" + quantity + ", usuario=" + usuario
				+ ", order=" + order + "]";
	}

	
	
	
	
	

}
