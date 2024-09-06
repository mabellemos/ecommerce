package com.compasso.ecommerce_app.core.interfaces.repository;

import com.compasso.ecommerce_app.core.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}