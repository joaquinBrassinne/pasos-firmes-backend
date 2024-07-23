package com.nocountry.apiS16.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisteredUserDTO implements Serializable{

    private Long id;

    private String email;

    private String name;

    private String jwt;

}
