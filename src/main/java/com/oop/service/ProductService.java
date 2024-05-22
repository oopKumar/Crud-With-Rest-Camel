package com.oop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oop.entity.Product;
import com.oop.repo.ProductRepo;

@Service
public class ProductService {
	@Autowired
	ProductRepo repo;
	
	public Product saveProduct(Product product) {
		Product save = repo.save(product);
		return save;
	}
	
	public List<Product> getAllProduct() {
		List<Product> product = repo.findAll();
		return product;
	}
	
	public String getById(Integer id) {
		Product product = repo.findById(id).get();
		return " "+product;
	}
	
	public String delete(Integer id) {
		repo.deleteById(id);
		return "Delete Product "+id;
	}


}
