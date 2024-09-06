package com.compasso.ecommerce_app.core.interfaces.repository;

import com.compasso.ecommerce_app.core.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}