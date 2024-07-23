package com.nocountry.apiS16.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FavoritesDTO implements Serializable {
    private Long id_user;
    private Long idProduct;
}
