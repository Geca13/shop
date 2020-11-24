package com.example.aaa.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aaa.product.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	Category findByProductCategory(String productCategory);
	
	Boolean existsByProductCategory(String productCategory);
	
	

}
