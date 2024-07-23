//package com.nocountry.apiS16.service.auth;
//
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.nocountry.apiS16.dto.RegisteredUserDTO;
//import com.nocountry.apiS16.dto.UserDTO;
//import com.nocountry.apiS16.model.Users;
//import com.nocountry.apiS16.service.implementations.UserService;
//
//@Service
//public class AuthenticationService {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private JwtService jwtService;
//
//    public RegisteredUserDTO registerOneCustomer(UserDTO newUser){
//        Users user = userService.saveUser(newUser);
//
//        RegisteredUserDTO userDTO = new RegisteredUserDTO();
//        userDTO.setId(user.getId_user());
//        userDTO.setName(user.getName());
//        userDTO.setEmail(user.getEmail());
//
//        String jwt = jwtService.generateToken(user, generateExtraClaims(user));
//        userDTO.setJwt(jwt);
//
//
//        return null;
//    }
//
//    private Map<String, Object> generateExtraClaims(Users user) {
//        Map<String, Object> extraClaims = new HashMap<>();
//
//        extraClaims.put("name", user.getName());
//
//
//        return extraClaims;
//    }
//
//}
