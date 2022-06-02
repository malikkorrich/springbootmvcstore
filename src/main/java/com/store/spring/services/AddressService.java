package com.store.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.spring.models.Address;
import com.store.spring.models.Usuario;
import com.store.spring.repositories.AddressRepository;

@Service
public class AddressService {

	@Autowired
	AddressRepository addressRepository;
	
	
	public Address add(Address address) {
		return addressRepository.save(address);
	}
	
	public Address add(Address address, Usuario user) {
		address.setUsuario(user);
		return addressRepository.save(address);
	}
	
	public List<Address> getAddressByUser(Usuario usuario){
		return addressRepository.findByUsuario(usuario);
	}
	
	public Address getById(long id) {
		return addressRepository.getById(id);
	}
	
	public void delete(long id) {
		 addressRepository.deleteById(id);
	}
 
	public void delete(Address address) {
		 addressRepository.delete(address);
		 
	}
}
