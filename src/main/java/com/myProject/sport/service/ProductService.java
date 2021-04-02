package com.myProject.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.sport.entity.Product;
import com.myProject.sport.repository.ProductRepository;

@Service
public class ProductService extends CrudImpl<Product> {
	public ProductRepository repository;

	@Autowired
	public ProductService(ProductRepository repository) {
		super(repository);
		this.repository = repository;
	}
}
