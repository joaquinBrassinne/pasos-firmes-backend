package com.nocountry.apiS16.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestDTO {

    private Long idRequest;
    private LocalDate requestDay;
    private boolean requestCompleted;
    private String name;
}
