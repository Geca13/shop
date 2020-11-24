package com.example.aaa.product.service;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.aaa.product.entity.Category;
import com.example.aaa.product.entity.Manufacturer;
import com.example.aaa.product.entity.Product;
import com.example.aaa.product.repository.CategoryRepository;
import com.example.aaa.product.repository.ManufacturerRepository;
import com.example.aaa.product.repository.ProductRepository;




@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ManufacturerRepository manufacturerRepository;
	
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public Page<Product> findPaginated(Integer pageNumber, Integer pageSize, String sortField, String sortDirection) {
		
		
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
		
		return productRepository.findAll(pageable);
		
	}
	@Override
	
	     public void save(Product product, MultipartFile file) {
	    	 
	    
	    	 
		        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		        if(fileName.contains("..")) {
		        	System.out.println("not a valid file");
		        }
		        try {
					product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		        //product.setImage(fileName);
		
		        if(categoryRepository.existsByProductCategory(product.getCategory().getProductCategory())) {
    			
    			Category cat = categoryRepository.findByProductCategory(product.getCategory().getProductCategory());
    			
    			product.setCategory(cat);
    		}
		
		if(manufacturerRepository.existsByManufacturerName(product.getManufacturer().getManufacturerName())) {
			
    		Manufacturer man = manufacturerRepository.findByManufacturerName(product.getManufacturer().getManufacturerName());
    			
    			product.setManufacturer(man);
    		}
    		
    		
    		
    		 productRepository.save(product);
		
	}

}
