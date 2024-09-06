package com.compasso.ecommerce_app.core.interfaces.repository;

import com.compasso.ecommerce_app.core.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer> {

}