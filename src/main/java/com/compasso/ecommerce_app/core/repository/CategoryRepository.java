package com.compasso.ecommerce_app.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compasso.ecommerce_app.core.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}