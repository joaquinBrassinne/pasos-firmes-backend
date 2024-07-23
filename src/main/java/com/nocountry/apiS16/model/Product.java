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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    @Column(unique = true)
    private String name;

    @Column(name = "id_user")
    private Long idUser;

    private String description;

    @Column(name = "creation_date")
    private Date creationDate;

    private boolean available;
    private boolean state;

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

}
