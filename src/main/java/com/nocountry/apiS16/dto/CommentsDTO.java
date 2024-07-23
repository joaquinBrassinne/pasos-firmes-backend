package com.nocountry.apiS16.dto;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class CommentsDTO implements Serializable {

    private Long id_user;
    private Long id_product;

    @NotBlank(message = "Increase a description")
    private String description;

    @PastOrPresent
    @NotNull(message = "Increase a valid creation date!")
    private LocalDate creationDate;
}
