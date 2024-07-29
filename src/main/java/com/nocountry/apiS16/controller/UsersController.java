package com.nocountry.apiS16.controller;

import com.nocountry.apiS16.dto.RegisteredUserDTO;
import com.nocountry.apiS16.dto.UserDTO;
import com.nocountry.apiS16.exceptions.ObjectNotFoundException;
import com.nocountry.apiS16.model.Users;
import com.nocountry.apiS16.service.interfaces.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UsersController {

    private final IUserService userService;

    @PostMapping("/add")
    public ResponseEntity<Users> saveUsers(@Valid @RequestBody UserDTO userDTO){
        return new ResponseEntity<>(this.userService.saveUser(userDTO),HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Users>> getUsers(){
        List<Users> usersList = this.userService.getUsers();

        if (!usersList.isEmpty()){
            return new ResponseEntity<>(usersList, HttpStatus.OK);
        }else {
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/get/{id_user}")
    public ResponseEntity<Users> findUser(@PathVariable Long id_user){
        Users usersFinded = this.userService.findUserById(id_user);

        if (usersFinded != null){
            return new ResponseEntity<>(usersFinded, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }


    @PutMapping("/update/{id_user}")
    public ResponseEntity<Users> userEdit(@PathVariable Long id_user, @Valid @RequestBody UserDTO userDTO){
        Users userUpdated = this.userService.editUser(id_user, userDTO);

        if (userUpdated !=null){
            return  new ResponseEntity<>(userUpdated, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id_user}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id_user){
        Boolean userDeleted = this.userService.deleteUser(id_user);

        if (userDeleted){
            return new ResponseEntity<>("User deleted", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }


}
