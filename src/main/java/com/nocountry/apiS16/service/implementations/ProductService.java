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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class ProductService {

    private final IProductRepository iProductRepository;

    private final ICategoryRepository iCategoryRepository;

    private final IUserRepository userRepository;

    public Product createProduct(ProductDTO productDTO) throws ResourceNotFoundException {

        Optional<Users> users = this.userRepository.findById(productDTO.getIdUser());

        Category category = iCategoryRepository.findById(productDTO.getIdProduct())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + productDTO.getIdProduct()));

        if (users.isEmpty()){
            throw new ResourceNotFoundException("User dont found");
        }

        if (productDTO == null) {
            throw new ResourceNotFoundException("ProductDTO object cannot be null");
        }
        if (productDTO.getName() == null || productDTO.getName().isEmpty()) {
            throw new ResourceNotFoundException("Product name required");
        }
        if (productDTO.getCategory() == null) {
            throw new ResourceNotFoundException("Category cannot be null");
        }
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setCreationDate(productDTO.getCreationDate());
        product.setAvailable(productDTO.isAvailable());
        product.setState(productDTO.isState());
        product.setImageURL(productDTO.getImageURL());
        product.setCategory(category);
        product.setUsers(users.get());

        return iProductRepository.save(product);

    }

    public List<Product> getAllProduct() {
        return iProductRepository.findAll();
    }

    public Product getProductById(Long id) throws ResourceNotFoundException {
        Product product = iProductRepository.findById(id).orElse(null);
        if (product == null) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        return product;
    }

    public Product getProductByName(String name) throws ResourceNotFoundException {
        Product product = iProductRepository.findByName(name).orElse(null);
        if (product == null) {
            throw new ResourceNotFoundException("Product not found with id: " + name);
        }
        return product;
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
        existingProduct.setState(productDTO.isState());
        existingProduct.setCategory(category);

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

}

