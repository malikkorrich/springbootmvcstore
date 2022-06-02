package com.store.spring.controllers;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.store.spring.models.Address;
import com.store.spring.models.PaymentStatus;
import com.store.spring.models.Product;
import com.store.spring.models.Purchase;
import com.store.spring.models.Usuario;
import com.store.spring.services.AddressService;
import com.store.spring.services.OrderService;
import com.store.spring.services.ProductService;
import com.store.spring.services.UserService;

@Controller
public class CartController {
	
	/**
	 * @author Malik Korrich
	 */
	
	@Autowired
	ProductService productService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	AddressService addressService;
	@Autowired
	HttpSession session;
	
	private Usuario usuario;

	@ModelAttribute("user")
	public Usuario getUserSession() {
		 String email = SecurityContextHolder.getContext().getAuthentication().getName();
		 return usuario = userService.getByEmail(email);
	}
	
	@ModelAttribute("cart")
	public List<Product> productCart() {
		List<Long> idsCart = (List<Long>) session.getAttribute("cart");
		return ( idsCart == null) ? null : productService.findAllByIds(idsCart);
	}
	
	@ModelAttribute("total_cart")
	public Double totalCart() {
		List<Product> productosCarrito = productCart();
		if (productosCarrito != null)
			return productosCarrito.stream()
				.mapToDouble(p -> p.getPrice())
				.sum();
		return 0.0;
	}
	
	
	@ModelAttribute("my_orders")
	public List<Purchase> myOrders() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		usuario = userService.getByEmail(email);
		return orderService.findByUser(usuario);
	}
	
	
	@GetMapping("/cart")
	public String cart(Model model) {
		return "cart";
	}
	
	
	
	
	@GetMapping("/cart/add/{id}")
	public String addCart(Model model, @PathVariable Long id) {
		List<Long> cartIds = (List<Long>) session.getAttribute("cart");
		if (cartIds == null)
			cartIds = new ArrayList<>();
		if (!cartIds.contains(id))
			cartIds.add(id);
		session.setAttribute("cart", cartIds);
		return "redirect:/cart";
	}
	
	
	@GetMapping("/cart/delete/{id}")
	public String deleteProductCart(Model model, @PathVariable Long id) {
		List<Long> cartIds = (List<Long>) session.getAttribute("cart");
		if (cartIds == null)
			return "redirect:/";
		cartIds.remove(id);
		if (cartIds.isEmpty())
			session.removeAttribute("cart");
		else
			session.setAttribute("cart", cartIds);
		return "redirect:/cart";
		
	}
	
	
	@GetMapping("/checkout")
	public String getCheckout(Model model) {
		List<Address> addresses = addressService.getAddressByUser(getUserSession());
		model.addAttribute("direction", addresses.get(0));
	 
		return "checkout";
	}
	
	
	
	@GetMapping("/cart/close")
	public String checkout() {
		List<Long> cartIds = (List<Long>) session.getAttribute("cart");
		if (cartIds == null)
			return "redirect:/";
		
		List<Product> products = productCart();
		
		Purchase purchase = orderService.add(new Purchase(), usuario);
		purchase.setStatus(PaymentStatus.COMPLETED.toString());
		products.forEach(p -> orderService.addProductoToOrder(p, purchase));
		session.removeAttribute("cart");
		
		return "redirect:/invoice/"+purchase.getId();
		
	}
	
	
	
	
	
	@GetMapping("/myorders")
	public String myOrders(Model model) {
		return "myorders";
	}
	
	
	@GetMapping("/invoice/{id}")
	public String invoice(Model model, @PathVariable Long id) {
		Purchase purchase= orderService.findById(id);
		List<Product> products = productService.productOrder(purchase);
		model.addAttribute("products", products);
		model.addAttribute("order", purchase);
		model.addAttribute("total_order", products.stream().mapToDouble(p -> p.getPrice()).sum());
		return "/invoice";
	}
	
}
