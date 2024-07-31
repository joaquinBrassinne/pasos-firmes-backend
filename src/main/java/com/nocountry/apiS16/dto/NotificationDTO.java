package com.nocountry.apiS16.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO implements Serializable {
    private Long id_requester;
    private Long id_seller;
    private String message;
}
