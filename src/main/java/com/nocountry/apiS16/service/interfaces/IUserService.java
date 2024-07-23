package com.nocountry.apiS16.service.interfaces;

import com.nocountry.apiS16.dto.UserDTO;
import com.nocountry.apiS16.model.Users;

import java.util.List;

public interface IUserService {

    public Users saveUser(UserDTO userDTO);
    public List<Users> getUsers();
    public Users findUserByName(String name);
    public Users findUserByDni(String dni);
    public Users findUserById(Long id_user);
    public Users editUser(Long id_user, UserDTO userDTO);
    public Boolean deleteUser(Long id_user);

}
