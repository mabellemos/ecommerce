package com.compasso.ecommerce_app.core.interfaces.repository;

import com.compasso.ecommerce_app.core.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Category, Integer>{

}