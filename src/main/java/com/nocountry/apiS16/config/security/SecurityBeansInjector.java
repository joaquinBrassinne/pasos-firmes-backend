//package com.nocountry.apiS16.config.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.nocountry.apiS16.exceptions.ObjectNotFoundException;
//import com.nocountry.apiS16.repository.IUserRepository;
//
//@Configuration
//public class SecurityBeansInjector {
//
//
//    @Autowired
//    private IUserRepository userRepository;
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
//
//        return authenticationConfiguration.getAuthenticationManager();
//
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//
//        DaoAuthenticationProvider authenticationStrategy = new DaoAuthenticationProvider();
//        authenticationStrategy.setPasswordEncoder(passwordEncoder());
//        authenticationStrategy.setUserDetailsService(userDetailsService());
//
//        return authenticationStrategy;
//
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService(){
//        return (email) -> {
//            return userRepository.getUserByEmail(email)
//                .orElseThrow(() -> new ObjectNotFoundException("User not found with username " + email));
//        };
//    }
//
//}
