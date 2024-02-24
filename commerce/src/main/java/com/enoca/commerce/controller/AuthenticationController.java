package com.enoca.commerce.controller;

import com.enoca.commerce.model.entity.Customer;
import com.enoca.commerce.service.AuthenticationService;
import com.enoca.commerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/authentication")//pre-path
public class AuthenticationController
{
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private CustomerService customerService;

    @PostMapping("/sign-up") //api/authentication/sign-up
    public ResponseEntity<?> signUp(@RequestBody Customer customer)
    {
        if (customerService.findByUsername(customer.getUsername()).isPresent())
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(customerService.saveUser(customer), HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")//api/authentication/sign-in
    public ResponseEntity<?> signIn(@RequestBody Customer customer)
    {
        return new ResponseEntity<>(authenticationService.signInAndReturnJWT(customer), HttpStatus.OK);
    }
}
