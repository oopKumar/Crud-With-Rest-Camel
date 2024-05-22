package com.oop.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oop.entity.Product;

public interface ProductRepo extends JpaRepository<Product,Integer> {

}
