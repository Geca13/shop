package com.example.aaa.shoppingCart.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.aaa.product.entity.Product;
import com.example.aaa.product.repository.ProductRepository;
import com.example.aaa.shoppingCart.entity.ShoppingCart;
import com.example.aaa.shoppingCart.repository.ShoppingCartRepository;
import com.example.aaa.shoppingCart.service.ShoppingCartService;
import com.example.aaa.users.entity.Address;
import com.example.aaa.users.entity.Users;
import com.example.aaa.users.repository.AddressRepository;
import com.example.aaa.users.repository.UsersRepository;
import com.example.aaa.users.service.UsersDetails;

@Controller
public class ShoppingCartController {
	
	@Autowired
	UsersRepository userRepository;
	
	@Autowired
	ShoppingCartRepository cartRepository;
	
	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	private static Double total;
	
	@GetMapping("/shoppingCart")
	public String shoppingCart(@AuthenticationPrincipal UsersDetails userD, @ModelAttribute("cart") ShoppingCart cart, Model model) {
		List<Product> products = new ArrayList<>();
		
	    String userEmail = userD.getUsername();
        Users user = userRepository.findByEmail(userEmail);
		
        LocalDate date = LocalDate.now();
        List<Address> address = new ArrayList<>();
        List<Address> adr = addressRepository.findAll();
		
		for (Address address2 : adr) {
		
			if(address2.getUser().getId() == user.getId()) {
				address.add(address2);
			}
		}
		model.addAttribute("address", address);
        model.addAttribute("products", products);
        model.addAttribute("user", user);
        model.addAttribute("date", date);
		
		return "confirmShoppingCart";
		
	}
	
	@PostMapping("/shoppingCart")
	public String proccesShoppingCart(@AuthenticationPrincipal UsersDetails userD ,@ModelAttribute("cart") ShoppingCart cart, Model model) {
		
		
		
		
	    String userEmail = userD.getUsername();
        Users user = userRepository.findByEmail(userEmail);
		LocalDate date = LocalDate.now();
        ShoppingCart sc = new ShoppingCart();
        sc.setUser(user);
        sc.setDate(date);
        sc.setProducts(products);
        sc.setAddress(cart.getAddress());
   /*     for (Product product : products) {
			
        	total += product.getProductPrice();
        	
        	//github pass Golm@n123 --- User Geca123
		}
        
        sc.setTotal(total);
        */
        cartRepository.save(sc);
		
		return "redirect:/profile";
	
	
	}

	@PostMapping("/add")
	private List<Product> addProducts(List<Product> products) {
		
		List<Product> productss = new ArrayList<>();
		
		List<Product> product = productRepository.findAll();
		for (Product product2 : product) {
			
			productss.add(product2);
			
		}
		
		
		return productss;
	}

}
