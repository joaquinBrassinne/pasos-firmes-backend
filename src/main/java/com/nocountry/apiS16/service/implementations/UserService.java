package com.nocountry.apiS16.service.implementations;

import com.nocountry.apiS16.dto.RegisteredUserDTO;
import com.nocountry.apiS16.dto.UserDTO;
import com.nocountry.apiS16.exceptions.InvalidPasswordException;
import com.nocountry.apiS16.exceptions.ObjectNotFoundException;
import com.nocountry.apiS16.model.Users;
import com.nocountry.apiS16.repository.IProductRepository;
import com.nocountry.apiS16.repository.IUserRepository;
import com.nocountry.apiS16.service.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {


    private final IUserRepository userRepository;



    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Users saveUser(UserDTO userDTO) {


        validatePassword(userDTO);
        Users userCreated = Users.builder()
                .name(userDTO.getName())
                .lastName(userDTO.getLastName())
                .dni(userDTO.getDni())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .birthday(userDTO.getBirthday())
                .phoneNumber(userDTO.getPhoneNumber())
                .province(userDTO.getProvince())
                .userPhoto(userDTO.getPhotoUser())
                .socialWorkNumber(userDTO.getSocialWorkNumber())
                .disabilityCertificateNumber(userDTO.getDisabilityCertificateNumber())
                .build();
        userCreated.setPassword(passwordEncoder.encode(userDTO.getPassword()));

         return userRepository.save(userCreated);
    }

    private void validatePassword(UserDTO dto) {

        if(!StringUtils.hasText(dto.getPassword()) || !StringUtils.hasText(dto.getRepeatedPassword())){
            throw new InvalidPasswordException("Passwords don't match");
        }

        if(!dto.getPassword().equals(dto.getRepeatedPassword())){
            throw new InvalidPasswordException("Passwords don't match");
        }
    }

    @Override
    public List<Users> getUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public Users findUserByName(String name) {
        return this.userRepository.getUserByName(name)
                .orElseThrow(()-> new RuntimeException("User with that name doesnt exist"));
    }


    @Override
    public Optional<Users> findUserByEmail(String email) throws ObjectNotFoundException {

         Optional<Users> users = userRepository.getUserByEmail(email);

        if(users.isPresent()){
            return users;
        }else{
            throw new ObjectNotFoundException("User with that email doesnt found");
        }

    }

    @Override
    public Users findUserByDni(String dni) {
        return this.userRepository.getUserByDni(dni).orElseThrow(()-> new RuntimeException("User with that dni: +"
                + dni + " doesnt exist"));
    }

    @Override
    public Users findUserById(Long idUser) {
        return this.userRepository.findById(idUser).orElseThrow(()-> new RuntimeException("The user with that id: "
                + idUser + " doesnt exist"));
    }

    @Override
    public Users editUser(Long idUser, UserDTO userDTO) {
        Users usersEdited = this.findUserById(idUser);

        usersEdited.setName(userDTO.getName());
        usersEdited.setLastName(userDTO.getLastName());
        usersEdited.setDni(userDTO.getDni());
        usersEdited.setEmail(userDTO.getEmail());
        usersEdited.setBirthday(userDTO.getBirthday());
        usersEdited.setPhoneNumber(userDTO.getPhoneNumber());


        return this.userRepository.save(usersEdited);
    }

    @Override
    public Boolean deleteUser(Long idUser) {
        Users usersDeleted = this.findUserById(idUser);
        if (usersDeleted != null){
            this.userRepository.delete(usersDeleted);
            return true;
        } else {
            return false;
        }
    }




}
