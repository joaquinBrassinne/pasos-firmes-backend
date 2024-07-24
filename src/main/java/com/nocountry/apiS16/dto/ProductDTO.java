package com.nocountry.apiS16.dto;

import com.nocountry.apiS16.model.State;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@Data
@NoArgsConstructor
public class ProductDTO implements Serializable {


    @NotBlank(message = "Cant be blank")
    private String name;
    private Long idUser;
    private String description;
    private Date creationDate;
    private boolean available;
    private String imageURL;
    private Long categoryId;
    private State state;


}
