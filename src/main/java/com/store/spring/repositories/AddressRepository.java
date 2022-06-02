package com.store.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.spring.models.Address;
import com.store.spring.models.Usuario;

@Repository
public interface AddressRepository  extends JpaRepository<Address, Long>{

	 public List<Address> findByUsuario(Usuario usuario);
}
