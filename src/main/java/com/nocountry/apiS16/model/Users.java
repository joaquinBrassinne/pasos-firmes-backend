package com.nocountry.apiS16.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.time.LocalDate;
import java.util.List;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id_user")
public class Users implements UserDetails { //UserDetails representa al Usuario logeado en Spring Security

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;
    private String name;
    @Column(name = "last_name")
    private String lastName;
    private String dni;
    @Column(unique = true)
    private String email;
    private String password;
    private LocalDate birthday;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String province;
    private String userPhoto;
    private Long socialWorkNumber;
    private Long disabilityCertificateNumber;


    @OneToMany(mappedBy = "users",cascade = CascadeType.REMOVE, targetEntity = Product.class, orphanRemoval = true)
    private List<Product> productList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, targetEntity = Comments.class , orphanRemoval = true)
    private List<Comments> commentsList;

    @OneToMany(mappedBy = "users",cascade = CascadeType.REMOVE,targetEntity = Favorites.class , orphanRemoval = true)
    private List<Favorites> favoritesList;

    @OneToMany(mappedBy = "users", cascade = CascadeType.REMOVE, targetEntity = Request.class, orphanRemoval = true)
    private List<Request> requestList;


    @OneToMany(mappedBy = "users", cascade = CascadeType.REMOVE, targetEntity = Answers.class, orphanRemoval = true)
    private List<Answers> answersList;



    //Implementaciones de los metodos UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


    @Override
    public String getUsername() {
        return email;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return true;
    }

}
