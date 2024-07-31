package com.nocountry.apiS16.service.implementations;
import com.nocountry.apiS16.dto.NotificationDTO;
import com.nocountry.apiS16.dto.ProductDTO;
import com.nocountry.apiS16.dto.ProductGetDTO;
import com.nocountry.apiS16.dto.RequestDTO;
import com.nocountry.apiS16.exceptions.ResourceNotFoundException;
import com.nocountry.apiS16.model.Category;
import com.nocountry.apiS16.model.Product;
import com.nocountry.apiS16.model.Request;
import com.nocountry.apiS16.model.Users;
import com.nocountry.apiS16.repository.ICategoryRepository;
import com.nocountry.apiS16.repository.IProductRepository;
import com.nocountry.apiS16.repository.IUserRepository;


import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final IProductRepository iProductRepository;

    private final ICategoryRepository iCategoryRepository;

    private final IUserRepository userRepository;

    @Autowired
    private NotificationService notificationService;

    public Product createProduct(ProductDTO productDTO) throws ResourceNotFoundException {

        Optional<Users> users = this.userRepository.findById(productDTO.getIdUser());


        Optional<Category> category = iCategoryRepository.findById(productDTO.getCategoryId());


        if(users.isPresent() && category.isPresent()) {

            Users user = users.get();

            String completeName = user.getName() + " " + user.getLastName();

            Product product = Product.builder()
                    .name(productDTO.getName())
                    .description(productDTO.getDescription())
                    .creationDate(productDTO.getCreationDate())
                    .available(productDTO.isAvailable())
                    .imageURL(productDTO.getImageURL())
                    .category(category.get())
                    .users(users.get())
                    .state(productDTO.getState())
                    .completeName(completeName)
                    .build();
            return this.iProductRepository.save(product);
        }else {
            throw new ResourceNotFoundException("User or category no present with that id");
        }

    }

    public List<Product> getProducts(){
        return this.iProductRepository.findAll();
    }

    public Product getProductById(Long id_product){
        return this.iProductRepository.findById(id_product).orElseThrow(()-> new RuntimeException("Product dont found"));
    }



    public Product updateProduct(Long idProduct, ProductDTO productDTO) throws ResourceNotFoundException{
        Product existingProduct = iProductRepository.findById(idProduct)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + idProduct));

        Category category = iCategoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + productDTO.getCategoryId()));
        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setCreationDate(productDTO.getCreationDate());
        existingProduct.setAvailable(productDTO.isAvailable());
        existingProduct.setImageURL(productDTO.getImageURL());
        existingProduct.setState(productDTO.getState());

        return iProductRepository.save(existingProduct);
    }

    public void deleteProductById(Long id) throws ResourceNotFoundException {
        Optional<Product> product = iProductRepository.findById(id);
        if (product.isPresent()) {
            iProductRepository.delete(product.get());
        } else {
            throw new ResourceNotFoundException("There is no product with the id: " + id);
        }

    }



    public void requestProduct(Long productId, Long requesterId) throws ResourceNotFoundException {
        Product product = iProductRepository.findById(productId).orElse(null);
        if(product == null) {
            throw new ResourceNotFoundException("Product not found with id: " + productId);
        }
        Long sellerId = product.getUsers().getId_user();

        NotificationDTO notificationDTO = new NotificationDTO();

        notificationDTO.setId_requester(requesterId);
        notificationDTO.setId_seller(sellerId);
        notificationDTO.setMessage("Tu producto ha sido solicitado por otro usuario.");


        notificationService.createNotification(notificationDTO);
    }

    List<Product> getProductByIdUser(Long id_user){
        return this.iProductRepository.findProductsByUserId(id_user);
    }

    public Product getProductByName(String name){
        return this.iProductRepository.findByName(name).orElseThrow(()-> new RuntimeException("PRODUCT DONT FOUND"));
    }

//    List<Product> findAvaibleProduct(){
//        return this.iProductRepository.findAvaibleProduct();
//    }
}

