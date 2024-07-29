package com.nocountry.apiS16.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnswersDTO {
    private String completeName;
    private String content;
    private LocalDate creationDate;
    private Long id_user;
    private Long id_comments;
}
