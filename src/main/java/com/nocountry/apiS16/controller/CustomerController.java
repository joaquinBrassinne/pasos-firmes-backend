package com.nocountry.apiS16.controller;

import com.nocountry.apiS16.service.auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nocountry.apiS16.dto.RegisteredUserDTO;
import com.nocountry.apiS16.dto.UserDTO;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/register")
public class CustomerController {

    @Autowired
    private AuthenticationService authenticateService;

    @PostMapping
    public ResponseEntity<RegisteredUserDTO> registerOne( @RequestBody @Valid UserDTO newUser){

        RegisteredUserDTO registeredUser = authenticateService.registerOneCustomer(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);

    }
}
