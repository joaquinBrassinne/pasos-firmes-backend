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
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_comments;

    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private Users user;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;

    private String description;

    @Column(name = "creation_date")
    private LocalDate creationDate;

}
