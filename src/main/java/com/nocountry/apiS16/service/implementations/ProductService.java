package com.nocountry.apiS16.service.implementations;
import com.nocountry.apiS16.dto.ProductDTO;
import com.nocountry.apiS16.exceptions.ResourceNotFoundException;
import com.nocountry.apiS16.model.Category;
import com.nocountry.apiS16.model.Product;
import com.nocountry.apiS16.model.Users;
import com.nocountry.apiS16.repository.ICategoryRepository;
import com.nocountry.apiS16.repository.IProductRepository;
import com.nocountry.apiS16.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final IProductRepository iProductRepository;

    private final ICategoryRepository iCategoryRepository;

    private final IUserRepository userRepository;


    public Product createProduct(ProductDTO productDTO) throws ResourceNotFoundException {

        Optional<Users> users = this.userRepository.findById(productDTO.getIdUser());

        Optional<Category> category = iCategoryRepository.findById(productDTO.getCategoryId());


        if(users.isPresent() && category.isPresent()) {

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
        }else {
            throw new ResourceNotFoundException("User or category no present with that id");
        }

    }

    public List<ProductDTO> getAllProductDTOs() {
        List<Product> products = iProductRepository.findAll();
        return products.stream().map(this::convertToProductDTO).collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) throws ResourceNotFoundException {
        Product product = iProductRepository.findById(id).orElse(null);
        if (product == null) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        return convertToProductDTO(product);
    }

    public ProductDTO getProductByName(String name) throws ResourceNotFoundException {
     Optional<Product> optionalProduct= iProductRepository.findByName(name);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            return convertToProductDTO(product);
        } else { throw new ResourceNotFoundException("Product not found with id: " + name);

        }

    }

    public Product updateProduct(Long idProduct, ProductDTO productDTO) throws  ResourceNotFoundException{
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
    public ProductDTO convertToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setCreationDate(product.getCreationDate());
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

    public List<ProductDTO> getProductsByUserId(Long id_user) {
        List<Product> products = iProductRepository.findProductsByUserId(id_user);
        return products.stream().map(this::convertToProductDTO).collect(Collectors.toList());
    }
}

