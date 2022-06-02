package com.store.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.spring.models.Purchase;
import com.store.spring.models.Product;
import com.store.spring.models.Usuario;
import com.store.spring.repositories.ProductRepository;
import com.store.spring.upload.StorageService;

@Service
public class ProductService {
	
	@Autowired
	StorageService storageService;
	
	@Autowired
	ProductRepository productRepository;
	
	public Product add(Product product) {
	 return productRepository.save(product);
	}
	
	public void delete(Product product) {
		
		if (!product.getImage().isEmpty())
			storageService.delete(product.getImage());
		productRepository.delete(product);
	}
	
	public void delete(long id) {
		productRepository.deleteById(id);
	}
	
	public Product edit(Product product) {
		 return productRepository.save(product);
		}
	
	public Product findById(long id) {
		return productRepository.findById(id).orElse(null);
	}
		
	public List<Product> findAll(){
		return productRepository.findAll();
	}
	
	
	public List<Product> productUser(Usuario user){
		return productRepository.findByUsuario(user);
	}
	
	
	public List<Product> productOrder(Purchase order){
		return productRepository.findByOrder(order);
	}
	
	public List<Product> productUnSold(){
		return productRepository.findByOrderIsNull();
	}
	
	public List<Product> search(String query){
		return productRepository.findByNameContainsIgnoreCaseAndOrderIsNull(query);
	}
		
	public List<Product> searchMyProduct(String query,Usuario user){
	return productRepository.findByNameContainsIgnoreCaseAndUsuario(query,user);
	}
	
	
	
	public List<Product> findAllByIds(List<Long> ids) {
		return productRepository.findAllById(ids);
	}
	
	
	
	
	
	
	
	

}
