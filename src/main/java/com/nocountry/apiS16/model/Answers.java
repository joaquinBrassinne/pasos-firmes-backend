package com.nocountry.apiS16.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Answers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_asnswers;
    private String completeName;
    private String content;
    private LocalDate creationDate;

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = false)
    @JsonBackReference
    private Comments comments;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private Users users;
}
