package com.compasso.ecommerce_app.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compasso.ecommerce_app.core.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}