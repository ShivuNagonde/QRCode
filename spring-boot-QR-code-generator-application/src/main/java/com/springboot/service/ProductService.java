package com.springboot.service;

import com.springboot.entity.Product;

public interface ProductService {

	public Product saveProducts(Product product);

    public Iterable<Product> findAll();

}
