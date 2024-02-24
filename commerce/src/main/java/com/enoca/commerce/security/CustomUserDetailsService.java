package com.enoca.commerce.security;

import com.enoca.commerce.model.entity.Customer;
import com.enoca.commerce.repository.CustomerRepository;
import com.enoca.commerce.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService
{
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Customer customer = customerRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        Set<GrantedAuthority> authorities = Set.of(SecurityUtils.convertToAuthority(customer.getRole().name()));

        return UserPrincipal.builder()
                .customer(customer).id(customer.getId())
                .username(username)
                .password(customer.getPassword())
                .authorities(authorities)
                .build();
    }
}
