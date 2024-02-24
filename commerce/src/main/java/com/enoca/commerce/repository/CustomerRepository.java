package com.enoca.commerce.repository;

import com.enoca.commerce.model.entity.Customer;
import com.enoca.commerce.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByUsername(String username);

    @Modifying
    @Query("update Customer set role = :role where username = :username")
    void updateUserRole(@Param("username") String username, @Param("role") Role role);
}

