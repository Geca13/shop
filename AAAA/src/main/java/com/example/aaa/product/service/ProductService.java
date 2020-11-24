package com.example.aaa.product.service;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.example.aaa.product.entity.Product;



public interface ProductService {
	
	public Page<Product> findPaginated(Integer pageNumber, Integer pageSize,String sortField, String sortDirection);

	public void save(Product product, MultipartFile file);
}
