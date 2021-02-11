package com.spring.service;

import com.spring.model.Product;

public interface ProductService {

	public Product saveProduct(Product product);
	
	public Iterable<Product> findAll();

}
