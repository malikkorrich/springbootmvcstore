package com.store.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.spring.models.Purchase;
import com.store.spring.models.Usuario;

@Repository
public interface OrderRepository extends JpaRepository<Purchase, Long> {

	List<Purchase> findByUsuario(Usuario user);
}
