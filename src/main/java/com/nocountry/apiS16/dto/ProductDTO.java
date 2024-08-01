package com.nocountry.apiS16.dto;

import com.nocountry.apiS16.model.Request;
import com.nocountry.apiS16.model.State;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO implements Serializable{
    private Long idProduct;
    private Long idUser;
    private String name;
    private String description;
    private LocalDate creationDate;
    private boolean available;
    private String imageURL;
    private Long categoryId;
    private State state;
    private String userName;
    private String userLastName;
    private String userEmail;
    private String userProvince;
    private List<RequestDTO> requestList;


    // Getters and setters
}

