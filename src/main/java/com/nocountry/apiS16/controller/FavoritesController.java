package com.nocountry.apiS16.controller;

import com.nocountry.apiS16.dto.FavoritesDTO;
import com.nocountry.apiS16.model.Favorites;
import com.nocountry.apiS16.service.interfaces.IFavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/favorites")
public class FavoritesController {

    private final IFavoriteService favoriteService;

    @PostMapping("/add")
    public ResponseEntity<Favorites> addFavorites(@RequestBody FavoritesDTO favoritesDTO){
        return new ResponseEntity<>(this.favoriteService.saveFavorites(favoritesDTO.getId_user(),
                favoritesDTO.getIdProduct()), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id_user}")
    public ResponseEntity<List<Favorites>> getFavorites(@PathVariable Long id_user){
        List<Favorites> favoritesList = this.favoriteService.getFavorities(id_user);

        if (!favoritesList.isEmpty()){
            return new ResponseEntity<>(favoritesList, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id_favorite}")
    public ResponseEntity<String> deleteFavorite(@PathVariable Long id_favorite){
        Boolean deleteFavorite = this.favoriteService.deleteFavorities(id_favorite);

        if (deleteFavorite){
            return new ResponseEntity<>("Delete succesfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Favorite doesnt found",HttpStatus.NOT_FOUND);
        }
    }

}
