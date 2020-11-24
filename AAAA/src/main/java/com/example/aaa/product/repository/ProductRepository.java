package com.example.aaa.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.aaa.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	// sto pravime ako imaat isto iminja???
	
	Boolean existsByProductName(String productName);
	
    Product findByProductName(String productName);
	
    
    Boolean existsByKeyword(String keyword);
	
 //   Product findByKeyword(String keyword);
    
    
    Boolean existsByProductOrigin(String productOrigin);
	
    Product findByProductOrigin(String productOrigin);
    
    List<Product>findByKeyword(String keyword);
    
    
	
	

}
