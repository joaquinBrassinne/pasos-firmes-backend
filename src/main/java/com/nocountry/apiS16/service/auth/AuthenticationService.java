
package com.nocountry.apiS16.service.auth;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.nocountry.apiS16.dto.RegisteredUserDTO;
import com.nocountry.apiS16.dto.UserDTO;
import com.nocountry.apiS16.dto.auth.AuthenticationRequest;
import com.nocountry.apiS16.dto.auth.AuthenticationResponse;
import com.nocountry.apiS16.model.Users;
import com.nocountry.apiS16.service.implementations.UserService;

@Service
public class AuthenticationService {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;


    public RegisteredUserDTO registerOneCustomer(UserDTO SaveUserDTO){
        Users user = userService.saveUser(SaveUserDTO);

        RegisteredUserDTO userDTO = new RegisteredUserDTO();
        userDTO.setId(user.getId_user());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());

        String jwt = jwtService.generateToken((UserDetails) user, generateExtraClaims(user));
        userDTO.setJwt(jwt);


        return userDTO;
    }

    private Map<String, Object> generateExtraClaims(Users user) {
        Map<String, Object> extraClaims = new HashMap<>();

        extraClaims.put("name", user.getName());


        return extraClaims;
    }

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        
        Authentication authentication = new UsernamePasswordAuthenticationToken(
            authenticationRequest.getEmail(),
            authenticationRequest.getPassword()
        );
        authenticationManager.authenticate(authentication);

        UserDetails user = (UserDetails) userService.findUserByEmail(authenticationRequest.getEmail()).get();

        String jwt = jwtService.generateToken(user, generateExtraClaims((Users)user));

        AuthenticationResponse authRsp = new AuthenticationResponse();
        authRsp.setJwt(jwt);

        return authRsp;

    }

    public boolean validateToken(String jwt) {

        try{
            jwtService.extractEmail(jwt);
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }

}
