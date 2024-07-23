package com.nocountry.apiS16.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Users { //UserDetails representa al Usuario logeado en Spring Security

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


    @OneToMany(mappedBy = "users",cascade = CascadeType.PERSIST, targetEntity = Product.class)
    @JsonManagedReference
    private List<Product> productList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, targetEntity = Comments.class)
    @JsonManagedReference
    private List<Comments> commentsList;

    @OneToMany(mappedBy = "users",cascade = CascadeType.PERSIST,targetEntity = Favorites.class)
    @JsonManagedReference
    private List<Favorites> favoritesList;

//    //Implementaciones de los metodos UserDetails
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//
//    @Override
//    public String getUsername() {
//        return email;
//    }
//
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }

}
