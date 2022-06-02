package com.store.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.store.spring.models.Product;
import com.store.spring.services.ProductService;

@Controller
public class HomeController {
	
	/**
	 * @author Malik Korrich
	 */
	
	@Autowired
	ProductService productService;
	
	@ModelAttribute("products")
	public List<Product> getProducts(){
		return productService.productUnSold();
	} 
	
 
	
	@GetMapping("/about")
	public String about() {
		return "about";
		}
	
	@GetMapping("/contact")
	public String contact() {
		return "contact";
		}
	
	
	@GetMapping("/product/{id}")
	public String product(Model model,@PathVariable long id ) {
		Product product = productService.findById(id);
		if(product != null) {
			model.addAttribute("product", product);
		}else {
			return "redirect:/";
		}
		
		return "product";
		}
	
	
	@GetMapping("/")
	public String index(Model model, @RequestParam(name="q", required=false) String query) {
		if (query != null)
			model.addAttribute("products", productService.search(query));
		return "index";
	}
	

}
