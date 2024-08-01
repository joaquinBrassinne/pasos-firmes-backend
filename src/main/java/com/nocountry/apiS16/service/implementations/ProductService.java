package com.nocountry.apiS16.service.implementations;
import com.nocountry.apiS16.dto.NotificationDTO;
import com.nocountry.apiS16.dto.ProductDTO;
import com.nocountry.apiS16.dto.ProductGetDTO;
import com.nocountry.apiS16.dto.RequestDTO;
import com.nocountry.apiS16.exceptions.ResourceNotFoundException;
import com.nocountry.apiS16.model.Category;
import com.nocountry.apiS16.model.Product;
import com.nocountry.apiS16.model.Users;
import com.nocountry.apiS16.repository.ICategoryRepository;
import com.nocountry.apiS16.repository.IProductRepository;
import com.nocountry.apiS16.repository.IUserRepository;


import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


        if (users.isPresent() && category.isPresent()) {

            Product product = Product.builder()
                    .name(productDTO.getName())
                    .description(productDTO.getDescription())
                    .creationDate(productDTO.getCreationDate())
                    .available(productDTO.isAvailable())
                    .imageURL(productDTO.getImageURL())
                    .category(category.get())
                    .users(users.get())
                    .state(productDTO.getState())
                    .build();
            return this.iProductRepository.save(product);
        } else {
            throw new ResourceNotFoundException("User or category no present with that id");
        }

    }

    public List<ProductDTO> getAllProductDTOs() {
        List<Product> products = iProductRepository.findAll();
        return products.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ProductGetDTO getProductById(Long id) throws ResourceNotFoundException {
        Product product = iProductRepository.findById(id).orElse(null);
        if (product == null) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        return convertToProductGetDTO(product);
    }

    public ProductGetDTO getProductByName(String name) throws ResourceNotFoundException {
        Optional<Product> optionalProduct = iProductRepository.findByName(name);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            return convertToProductGetDTO(product);
        } else {
            throw new ResourceNotFoundException("Product not found with id: " + name);

        }

    }

    public Product updateProduct(Long idProduct, ProductDTO productDTO) throws ResourceNotFoundException {
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

//    public ProductDTO convertToProductDTO(Product product) {
//
//        ProductDTO productDTO = new ProductDTO();
//        productDTO.setIdProduct(product.getIdProduct());
//        productDTO.setName(product.getName());
//        productDTO.setDescription(product.getDescription());
//        productDTO.setCreationDate(product.getCreationDate());
//        productDTO.setAvailable(product.isAvailable());
//        productDTO.setImageURL(product.getImageURL());
//        productDTO.setCategoryId(product.getCategory().getIdCategory());
//        productDTO.setState(product.getState());
//
//
//        Users user = product.getUsers();
//        if (user != null) {
//            productDTO.setIdUser(user.getId_user());
//            productDTO.setUserName(user.getName());
//            productDTO.setUserLastName(user.getLastName());
//            productDTO.setUserEmail(user.getEmail());
//            productDTO.setUserProvince(user.getProvince());
//        }
//
//
//        return productDTO;
//    }

    public ProductGetDTO convertToProductGetDTO(Product product) {

        ProductGetDTO productDTO = new ProductGetDTO();

        productDTO.setId(product.getIdProduct());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setCreationDate(product.getCreationDate().toString());
        productDTO.setAvailable(product.isAvailable());
        productDTO.setImageURL(product.getImageURL());
        productDTO.setCategoryId(product.getCategory().getIdCategory());
        productDTO.setState(product.getState());

        Users user = product.getUsers();
        if (user != null) {
            productDTO.setIdUser(user.getId_user());
            productDTO.setUserName(user.getName());
            productDTO.setUserLastName(user.getLastName());
            productDTO.setUserEmail(user.getEmail());
            productDTO.setUserProvince(user.getProvince());
        }


        return productDTO;
    }


    public void requestProduct(Long productId, Long requesterId) throws ResourceNotFoundException {
        Product product = iProductRepository.findById(productId).orElse(null);
        if (product == null) {
            throw new ResourceNotFoundException("Product not found with id: " + productId);
        }
        Long sellerId = product.getUsers().getId_user();

        NotificationDTO notificationDTO = new NotificationDTO();

        notificationDTO.setId_requester(requesterId);
        notificationDTO.setId_seller(sellerId);
        notificationDTO.setMessage("Tu producto ha sido solicitado por otro usuario.");


        notificationService.createNotification(notificationDTO);
    }

    public List<ProductDTO> getProductsByUserId(Long idUser) {
        List<Product> products = iProductRepository.findProductsByUserId(idUser);
        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ProductDTO convertToDTO(Product product) {
        List<RequestDTO> requestDTOs = new ArrayList<>();
        if (product.getRequestList() != null) {
            requestDTOs = product.getRequestList().stream()
                    .map(request -> new RequestDTO(
                            request.getIdRequest(),
                            request.getRequestDay(),
                            request.isRequestCompleted(),
                            request.getName()
                    ))
                    .collect(Collectors.toList());
        }

        Long categoryId = product.getCategory() != null ? product.getCategory().getIdCategory() : null;
        Long userId = product.getUsers() != null ? product.getUsers().getId_user() : null;
        String userName = product.getUsers() != null ? product.getUsers().getName() : null;
        String userLastName = product.getUsers() != null ? product.getUsers().getLastName() : null;
        String userEmail = product.getUsers() != null ? product.getUsers().getEmail() : null;
        String userProvince = product.getUsers() != null ? product.getUsers().getProvince() : null;

        return new ProductDTO(
                product.getIdProduct(),
                userId,
                product.getName(),
                product.getDescription(),
                product.getCreationDate(),
                product.isAvailable(),
                product.getImageURL(),
                categoryId,
                product.getState(),
                userName,
                userLastName,
                userEmail,
                userProvince,
                requestDTOs
        );
    }
}
//    List<Product> findAvaibleProduct(){
//        return this.iProductRepository.findAvaibleProduct();
//    }


