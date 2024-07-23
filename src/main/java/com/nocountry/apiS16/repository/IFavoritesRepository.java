package com.nocountry.apiS16.repository;

import com.nocountry.apiS16.dto.FavoritesDTO;
import com.nocountry.apiS16.model.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IFavoritesRepository extends JpaRepository<Favorites, Long> {

    //List<FavoritesDTO> findByIdUser(Long id_user);


}
