package com.example.aaa.product.controller;




import java.util.ArrayList;
import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import com.example.aaa.product.entity.Product;
import com.example.aaa.product.repository.CategoryRepository;

import com.example.aaa.product.repository.ProductRepository;
import com.example.aaa.product.service.ProductServiceImpl;



@Controller
public class ProductController {
	
	
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductServiceImpl productServiceImpl;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	
	@GetMapping("/product")
	public String viewProductPage(Model model) {
		
		 findPages(1,"productName", "asc", model);
		 
		 return "all_products";
	}
	
	@GetMapping("pages/{pageNumbers}")
	public String findPages(@PathVariable("pageNumbers") Integer pageNumbers,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		
		Integer pageSize = 2;
		
		Page<Product>pages = productServiceImpl.findPaginated(pageNumbers, pageSize, sortField, sortDir);
		
		List<Product> listProducts = pages.getContent();
		model.addAttribute("currentPage",pageNumbers);
		model.addAttribute("totalPages", pages.getTotalPages());
		model.addAttribute("totalItems", pages.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("listProducts", listProducts);
		return "all_products";
		
	}

	
	@PostMapping("/saveProduct")
	public  String createProduct(Product product, MultipartFile file){
		
			
		productServiceImpl.save(product, file);
			
		return "redirect:/product";
		}
	
	
		
		@GetMapping("/showNewProductForm")
		public String showNewProductForm (Model model) {
			
			Product product = new Product();
			
			model.addAttribute("product", product);
			
			return "add_product";
		
	}
		
		@GetMapping("/showNewProductForm/{id}")
		public String showNewProductForm (Model model,@PathVariable	("id") Integer id) {
			
			Product product = productRepository.findById(id).get();
			model.addAttribute("product", product);
			
			return "updateProduct";
		
	}
		
		
		
		@GetMapping("/products/productDetails/{id}")
		public String profileDetails(@PathVariable("id")Integer id, Model model) {
			Product product = productRepository.findById(id).get();
			   
			model.addAttribute("product", product);
			
			return "productDetails";
			
		}
		
		@GetMapping("/deleteProduct/{id}")
		public String deleteProduct(@PathVariable("id")Integer id) {
			
			Product product = productRepository.findById(id).get();
			product.setCategory(null);
			product.setManufacturer(null);
			productRepository.save(product);
			
			productRepository.deleteById(id);
			 
			return "redirect:/product";
		
		}
		
		@GetMapping("/products")
		public String gridDetails( Model model) {
			List<Product> products = productRepository.findAll();
			   
			model.addAttribute("products", products);
			
			return "productGrid";
			
		}
		
		@GetMapping("/products/keyword/{keyword}")
		public String gridDetails( Model model, @PathVariable("keyword") String keyword ) {
			
			List<Product> products = new ArrayList<>();
			List<Product> product = productRepository.findByKeyword(keyword);
			for (Product product2 : product) {
				
				if(product2.getKeyword().equals(keyword)) {
					products.add(product2);
				}
			}
			   
			model.addAttribute("products", products);
			
			return "redirect:/products";
		
		}
		
		
		
		
		
		
		
		
		
		
		
}
