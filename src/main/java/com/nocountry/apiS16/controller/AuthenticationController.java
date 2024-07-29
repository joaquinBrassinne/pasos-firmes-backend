package com.nocountry.apiS16.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nocountry.apiS16.dto.auth.AuthenticationRequest;
import com.nocountry.apiS16.dto.auth.AuthenticationResponse;
import com.nocountry.apiS16.model.Users;
import com.nocountry.apiS16.service.auth.AuthenticationService;
import com.nocountry.apiS16.service.implementations.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @GetMapping("/validate-token")
    public ResponseEntity<Boolean> validate(@RequestParam String jwt){
        boolean isTokenValid = authenticationService.validateToken(jwt);
        return ResponseEntity.ok(isTokenValid);
    }


    @GetMapping("/getUser")
    public ResponseEntity<Users> getUserByEmail(@RequestParam String jwt){
        if(!authenticationService.validateToken(jwt)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        
        Users usersFinded = this.userService.findUserByEmail(authenticationService.extractEmail(jwt)).get();
        if (usersFinded != null){
            return new ResponseEntity<>(usersFinded, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
        @RequestBody @Valid AuthenticationRequest authenticationRequest){

            AuthenticationResponse rsp = authenticationService.login(authenticationRequest);
            return ResponseEntity.ok(rsp);

    }
}
