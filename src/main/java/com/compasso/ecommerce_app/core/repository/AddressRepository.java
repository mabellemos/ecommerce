package com.compasso.ecommerce_app.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compasso.ecommerce_app.core.model.Address;

public interface AddressRepository extends JpaRepository<Address,Integer> {

}