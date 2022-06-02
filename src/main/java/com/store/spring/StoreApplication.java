package com.store.spring;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.store.spring.models.Address;
import com.store.spring.models.PaymentStatus;
import com.store.spring.models.Product;
import com.store.spring.models.Purchase;
import com.store.spring.models.Usuario;
import com.store.spring.services.AddressService;
import com.store.spring.services.OrderService;
import com.store.spring.services.ProductService;
import com.store.spring.services.UserService;

@SpringBootApplication
public class StoreApplication {
	
	/**
	 * 
	 * @author Malik Korrich
	 * 
	 *
	 */

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner initData(UserService userService , AddressService addressService,ProductService productService, OrderService orderService) {
		return (args) ->{
		
			 
			
			Usuario user = new Usuario("malik korrich","malik.korrich@gmail.com","123456789","https://i.ibb.co/jGS1ccn/user.png");
			Address address = new Address("spain","madrid","calle dr lozano n4 3a",user);
			Address address2 = new Address("spain","madrid","calle guillermo de osma",user);
			userService.add(user);
			addressService.add(address);
			addressService.add(address2);
			
			
			Purchase order = new Purchase(PaymentStatus.PENDING.toString(),user);
			orderService.add(order);
	 
 
			
			List<Product> products = Arrays.asList(
					
					new Product("Iphone 13","new","Phones",1500f,"https://www.powerplanetonline.com/cdnassets/iphone_13_pro_max_verde_alpino_01_l.jpg",14,user,order),
					new Product("Sleeve linen shirt","tight-fitting.....","Clothes",100f,"https://i.postimg.cc/1z9nqqSf/222.jpg",50,user,null),
					new Product("Sleeve dress","unfashionable....","Clothes",50f,"https://i.postimg.cc/05tTRRj3/444.jpg",120,user,null),
					new Product("Stylish dress","formal.....","Clothes",15f,"https://i.postimg.cc/g0P5qGtw/322.jpg",5,user,null),
					new Product("Stylish dress","colourful ....","Clothes",15f,"https://i.postimg.cc/j2mKRGxz/111.jpg",5,user,null)
					
					
					);
			
			 products.forEach(productService::add);
			
		};
 	}

}
