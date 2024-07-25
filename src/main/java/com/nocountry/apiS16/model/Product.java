package com.nocountry.apiS16.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;
    private String name;
    private String description;
    @Column(name = "creation_date")
    private Date creationDate;
    private boolean available;
    private String imageURL;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(targetEntity = Users.class)
    @JsonBackReference
    private Users users;

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST, targetEntity = Comments.class)
    @JsonManagedReference
    private List<Comments> commentsList;

    @Enumerated(EnumType.STRING)
    private State state;

}
