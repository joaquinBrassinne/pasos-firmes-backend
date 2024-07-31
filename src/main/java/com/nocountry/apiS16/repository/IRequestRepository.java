package com.nocountry.apiS16.repository;

import com.nocountry.apiS16.model.Product;
import com.nocountry.apiS16.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRequestRepository extends JpaRepository<Request, Long> {
    @Query("SELECT r FROM Request r WHERE r.users.id_user = :id_user")
    List<Request> findByUserId(@Param("id_user") Long id_user);
}
