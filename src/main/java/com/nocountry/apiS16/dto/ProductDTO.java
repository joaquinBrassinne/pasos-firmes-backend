package com.nocountry.apiS16.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
@Getter
@Setter
@Data
@NoArgsConstructor
public class ProductDTO {

    private Long idProduct;
    private String name;
    private Long idUser;
    private String description;
    private Date creationDate;
    private boolean available;
    private boolean state;
    private String imageURL;
    private Long categoryId;
    private CategoryDTO category;
}
