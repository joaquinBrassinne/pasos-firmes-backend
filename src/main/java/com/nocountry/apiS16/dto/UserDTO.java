package com.nocountry.apiS16.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import java.io.Serializable;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class UserDTO implements Serializable {


    @Size(min = 3)
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Last Name is required")
    private String lastName;

    @Size(min = 8) //44 123 321
    @NotBlank(message =  "Dni is required")
    private String dni;

    @Email
    @NotNull(message = "Email valid is required")
    private String email;

    @Size(min = 8)
    private String password;

    @Size(min = 8)
    private String repeatedPassword;

    @Past
    @NotNull(message = "Birth day is required")
    private LocalDate birthday;

    @NotBlank(message = "Increase a phone number please!")
    @Size(min = 10, max = 11) //3571416413
    private String phoneNumber;

    @NotBlank(message = "Increase a Province please!")
    private String province;

    @NotBlank
    private String photoUser;

}
