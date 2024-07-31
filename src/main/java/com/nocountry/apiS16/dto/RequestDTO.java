package com.nocountry.apiS16.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO implements Serializable {

    private Long idRequest;
    private LocalDate requestDay;
    private boolean requestCompleted;
    private Long idUser;
    private Long idProduct;
    private String completeNameOfUser;
}
