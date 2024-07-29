package com.nocountry.apiS16.repository;

import com.nocountry.apiS16.dto.RegisteredUserDTO;
import com.nocountry.apiS16.dto.UserDTO;
import com.nocountry.apiS16.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<Users, Long> {

    public Optional<Users> getUserByName(String name);
    public Optional<Users> getUserByDni(String dni);
    public Optional<Users> getUserByEmail(String email);

}
