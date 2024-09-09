package com.compasso.ecommerce_app.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.compasso.ecommerce_app.core.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    @Query(value="FROM Users u WHERE u.username = ?1")
    Optional<Users> login(String username);

}

