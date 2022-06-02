package com.store.spring.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.store.spring.models.Address;
import com.store.spring.models.Usuario;
import com.store.spring.services.AddressService;
import com.store.spring.services.UserService;
import com.store.spring.upload.StorageService;

@Controller
public class UserController {

	/**
	 * @author Malik Korrich
	 */
	
	@Autowired
	UserService userService;
	
	@Autowired
	StorageService storageService;
	
	@Autowired
	AddressService addressService;
	
	private Usuario user;
	
	@ModelAttribute("user")
	public Usuario getUserSession() {
		 String email = SecurityContextHolder.getContext().getAuthentication().getName();
		 return user = userService.getByEmail(email);
	}
	
	
	@GetMapping("/auth/login")
	public String login() {
		return "login";
		}
	
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new Usuario());
		return "signup";
	}
	
	@PostMapping("/register/add")
	public String register(@Valid @ModelAttribute Usuario user , BindingResult result,  @RequestParam("file") MultipartFile file) {
		if (!result.hasErrors()) {
			
			if (!file.isEmpty()) {
				String imagen = storageService.store(file);
				user.setAvatar(MvcUriComponentsBuilder
						.fromMethodName(FileController.class, "serveFile", imagen).build().toUriString());
				
			}
			userService.add(user);
			addressService.add(new Address("region","city","address ...",user));
		}else {
			return "redirect:/register";
		}
		
		return "redirect:/auth/login";
	}
	
	
	@GetMapping("/profile")
	public String profile(Model model) {
	 model.addAttribute("user", getUserSession());
		return "profile";
	}
	
	
	@GetMapping("/orders")
	public String orders() {
		return "orders";
	}
	
	/*Address*/
	@GetMapping("/address")
	public String address(Model model) {
		List<Address> addresses = addressService.getAddressByUser(getUserSession());
		model.addAttribute("addresses", addresses);
		return "address";
	}
	
	
	@GetMapping("/address/edit")
	public String addressEdit() {
		return "address-edit";
	}
	
	@GetMapping("/address/edit/{id}")
	public String editAddress(@PathVariable long id , Model model) {
		Address address = addressService.getById(id);
		model.addAttribute("direccion", address);
		return "address-edit";
	}
	
	@PostMapping("/address/edit")
	public String editAddress(@Valid @ModelAttribute("direccion") Address address, BindingResult result) {
		if (!result.hasErrors()) {
			 
		 
			addressService.delete(address.getId());
			addressService.add(address,getUserSession());
		 
			 
			
		}else {
			return "redirect:/address/edit";
		}
		return "redirect:/address";
	}
	
	
	
	
	
	/*Settings*/
	
	
	@GetMapping("/settings")
	public String account(Model model) {
		model.addAttribute("user", getUserSession());
		return "settings";
	}
	
	@PostMapping("/settings/edit")
	public String accountEdit(@ModelAttribute("user") Usuario user,  @RequestParam("file") MultipartFile file , BindingResult result) {
		if (!result.hasErrors()) {
			
			if (!file.isEmpty()) {
				String imagen = storageService.store(file);
				user.setAvatar(MvcUriComponentsBuilder
						.fromMethodName(FileController.class, "serveFile", imagen).build().toUriString());
				
			}
			user.setPassword(userService.getpasswordEncoder().encode(user.getPassword()));
			int res = userService.Update(user.getName(), user.getEmail(), user.getPassword(), user.getAvatar(), getUserSession().getId());
			if (res != 1)
				 return "settings";
		}else {
			return "settings";
		}
		return "redirect:/profile";
	}
 
	
	
	
}
