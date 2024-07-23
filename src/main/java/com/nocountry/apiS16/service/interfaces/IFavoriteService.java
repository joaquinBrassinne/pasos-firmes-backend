package com.nocountry.apiS16.service.interfaces;

import com.nocountry.apiS16.model.Favorites;

import java.util.List;


public interface IFavoriteService {

    Favorites saveFavorites(Long id_user, Long id_product);
    List<Favorites> getFavorities(Long id_user);
    boolean deleteFavorities(Long id);
}
