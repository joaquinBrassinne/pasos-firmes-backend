package com.nocountry.apiS16.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class CategoryDTO implements Serializable {
    private Long idCategory;
    @NotBlank
    private String name;
}
