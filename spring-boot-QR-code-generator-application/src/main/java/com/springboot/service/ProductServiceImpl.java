package com.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.entity.Product;
import com.springboot.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product saveProducts(Product product) {
		return productRepository.save(product);
	}
	
	@Override
	public Iterable<Product> findAll() {
		return productRepository.findAll();
    }
}