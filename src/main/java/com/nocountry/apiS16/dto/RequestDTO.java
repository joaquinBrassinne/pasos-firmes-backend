package com.nocountry.apiS16.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestDTO implements Serializable {

    private Long idRequest;
    private LocalDate requestDay;
    private boolean requestCompleted;
    private String name;
    private String lastName;
    private String email;
    private String province;
    private String phoneNumber;
    private Long socialWorkNumber;
    private Long disabilityCertificateNumber;
}
