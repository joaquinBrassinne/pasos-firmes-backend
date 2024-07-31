package com.nocountry.apiS16.dto;

import com.nocountry.apiS16.model.Request;
import com.nocountry.apiS16.model.State;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@Data
@NoArgsConstructor
public class ProductDTO implements Serializable {

    private Long id;

    @NotBlank(message = "Cant be blank")
    private String name;
    private Long idUser;
    private String description;
    private LocalDate creationDate;
    private boolean available;
    private String imageURL;
    private Long categoryId;
    private State state;
    private String completeName;
    private List<RequestDTO> requestList;

}
