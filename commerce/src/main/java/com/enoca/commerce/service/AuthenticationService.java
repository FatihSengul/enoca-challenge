package com.enoca.commerce.service;


import com.enoca.commerce.model.entity.Customer;
import com.enoca.commerce.security.UserPrincipal;
import com.enoca.commerce.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService{
    @Autowired
    private AuthenticationManager authenticationManager;

    JwtTokenProvider jwtProvider = new JwtTokenProvider();

    public Customer signInAndReturnJWT(Customer signInRequest)
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
        );

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String jwt = jwtProvider.generateToken(userPrincipal);

        Customer signInUser = userPrincipal.getCustomer();
        signInUser.setToken(jwt);

        return signInUser;
    }
}
