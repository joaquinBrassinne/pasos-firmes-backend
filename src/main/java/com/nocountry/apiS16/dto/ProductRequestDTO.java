package com.nocountry.apiS16.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductRequestDTO implements Serializable {
    private Long productId;
    private Long requesterId;
}
