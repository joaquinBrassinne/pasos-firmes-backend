package com.nocountry.apiS16.service.implementations;

import com.nocountry.apiS16.exceptions.ObjectNotFoundException;
import com.nocountry.apiS16.model.Favorites;
import com.nocountry.apiS16.model.Product;
import com.nocountry.apiS16.model.Users;
import com.nocountry.apiS16.repository.IFavoritesRepository;
import com.nocountry.apiS16.repository.IProductRepository;
import com.nocountry.apiS16.repository.IUserRepository;
import com.nocountry.apiS16.service.interfaces.IFavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteService implements IFavoriteService {

    private final IFavoritesRepository favoritesRepository;

    private final IUserRepository userRepository;

    private final IProductRepository productRepository;

    @Override
    public Favorites saveFavorites(Long id_user, Long id_product) throws ObjectNotFoundException {

        //Buscamos el user por el id
        Optional<Users> users = this.userRepository.findById(id_user);
        //Buscamos el producto por el id
        Optional<Product> product = this.productRepository.findById(id_product);

        if (users.isPresent() && product.isPresent()){
            Favorites favorites = Favorites.builder()
                    .users(users.get())
                    .product(product.get())
                    .build();

            return this.favoritesRepository.save(favorites);

        }else {
            throw new ObjectNotFoundException("Product or user doesnt found");
        }

    }

    @Override
    public List<Favorites> getFavorities(Long id_user) {
        return this.favoritesRepository.findAll();
    }


    @Override
    public boolean deleteFavorities(Long id) throws ObjectNotFoundException{
        Favorites favoritesDeleted = this.favoritesRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException("Favorities with that id doesnt exist"));

        if (favoritesDeleted != null){
            this.favoritesRepository.delete(favoritesDeleted);
            return true;
        }else {
             throw new ObjectNotFoundException("Favorities with that id doesnt exist");
        }
    }
}
