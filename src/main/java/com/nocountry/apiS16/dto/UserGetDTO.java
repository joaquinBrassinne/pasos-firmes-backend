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
public class UserGetDTO implements Serializable {


    @Size(min = 3)
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Last Name is required")
    private String lastName;

    @Email
    @NotNull(message = "Email valid is required")
    private String email;

    @Past
    private LocalDate birthday;


    @Size(min = 10, max = 11) //3571416413
    private String phoneNumber;

    @NotBlank(message = "Increase a Province please!")
    private String province;

    private String photoUser;

    private Long socialWorkNumber;

    private Long disabilityCertificateNumber;
}

