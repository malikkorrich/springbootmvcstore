package com.store.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.spring.models.Purchase;
import com.store.spring.models.Product;
import com.store.spring.models.Usuario;
import com.store.spring.repositories.OrderRepository;
 

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ProductService productService;
	
	public Purchase add(Purchase order ,Usuario user) {
		order.setUsuario(user);
		return orderRepository.save(order);
	}
	
	public Purchase add(Purchase order ) {
		return orderRepository.save(order);
	}
	
	public Product addProductoToOrder(Product product, Purchase order) {
		product.setOrder(order);
		return  productService.edit(product);
		}
	
	public Purchase findById(long id) {
		return orderRepository.findById(id).orElse(null);
	}
	
	public List<Purchase> findAll(){
		return orderRepository.findAll();
	}
	
	public List<Purchase> findByUser(Usuario user){
		return orderRepository.findByUsuario(user);
	}
	
}
