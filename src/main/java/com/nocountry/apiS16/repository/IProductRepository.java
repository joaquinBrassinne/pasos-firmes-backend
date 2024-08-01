package com.nocountry.apiS16.repository;
import com.nocountry.apiS16.dto.ProductDTO;
import com.nocountry.apiS16.model.Product;
import com.nocountry.apiS16.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
    Optional<Product> findById(Long id);

    @Query("SELECT p FROM Product p WHERE p.users.id_user = :id_user")
    List<Product> findProductsByUserId(@Param("id_user") Long id_user);

    //List<Product> findAvaibleProduct();



}
