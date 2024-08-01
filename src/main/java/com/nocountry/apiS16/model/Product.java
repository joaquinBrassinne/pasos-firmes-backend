package com.nocountry.apiS16.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idProduct")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;
    private String name;
    private String description;
    @Column(name = "creation_date")
    private LocalDate creationDate;
    private boolean available;
    private String imageURL;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, targetEntity = Comments.class, orphanRemoval = true)
    private List<Comments> commentsList;

    @Enumerated(EnumType.STRING)
    private State state;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, targetEntity = Request.class, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Request> requestList;

}
