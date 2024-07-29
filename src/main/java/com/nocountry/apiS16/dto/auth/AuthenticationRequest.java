package com.nocountry.apiS16.dto.auth;


import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
public class AuthenticationRequest implements Serializable {

    private String email;

    private String password;

}
