package com.nocountry.apiS16.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CategoryDTO {
    private Long idCategory;
    @NotBlank
    private String name;
}
