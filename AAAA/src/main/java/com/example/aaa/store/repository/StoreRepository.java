package com.example.aaa.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aaa.product.entity.Product;
import com.example.aaa.store.entity.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {
	
    Boolean existsByStoreName(String storeName);
	
    Product findByStoreName(String storeName);

}
