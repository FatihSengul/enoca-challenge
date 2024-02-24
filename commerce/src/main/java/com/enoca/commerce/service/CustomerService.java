package com.enoca.commerce.service;

import com.enoca.commerce.model.entity.Customer;
import com.enoca.commerce.model.enums.Role;
import com.enoca.commerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;


@Service
public class CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Customer saveUser(Customer customer)
    {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setRole(Role.CUSTOMER);

        return customerRepository.save(customer);
    }

    public Optional<Customer> findByUsername(String username)
    {
        return customerRepository.findByUsername(username);
    }

    @Transactional //TransactionalRequired when executing an update/delete query.
    public void makeAdmin(String username)
    {
        customerRepository.updateUserRole(username, Role.ADMIN);
    }
}
