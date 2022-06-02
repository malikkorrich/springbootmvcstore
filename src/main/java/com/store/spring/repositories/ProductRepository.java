package com.store.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.spring.models.Purchase;
import com.store.spring.models.Product;
import com.store.spring.models.Usuario;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findByUsuario(Usuario user);
	
	List<Product> findByOrder(Purchase order);
	
	List<Product> findByOrderIsNull();
	
	List<Product> findByNameContainsIgnoreCaseAndOrderIsNull(String name);
	
	List<Product> findByNameContainsIgnoreCaseAndUsuario(String name,Usuario user);

}
