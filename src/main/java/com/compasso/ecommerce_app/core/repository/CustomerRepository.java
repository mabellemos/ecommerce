package com.compasso.ecommerce_app.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compasso.ecommerce_app.core.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}