package com.example.aaa.shoppingCart.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.aaa.product.entity.Product;
import com.example.aaa.users.entity.Address;
import com.example.aaa.users.entity.Users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private LocalDate date;
	
	@ManyToMany
	private List<Product> products;
	
	private Double total;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Users user;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
    private Address address;

}
