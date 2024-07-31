package com.nocountry.apiS16.controller;

import com.nocountry.apiS16.dto.ProductDTO;
import com.nocountry.apiS16.dto.ProductGetDTO;
import com.nocountry.apiS16.dto.ProductRequestDTO;
import com.nocountry.apiS16.exceptions.ResourceNotFoundException;
import com.nocountry.apiS16.model.Product;
import com.nocountry.apiS16.service.implementations.ProductService;

import jakarta.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public Product createProduct(@RequestBody ProductDTO productDTO) throws ResourceNotFoundException {
        return this.productService.createProduct(productDTO);
    }

    @GetMapping("/get")
    public List<Product> getAllProducts() {
        List<Product> products = productService.getProducts();
        if (products.isEmpty()) {
            return new ArrayList<>();
        }
        return products;

    }

    @GetMapping("/get/name/{name}")
    public Product getProductByName(@PathVariable String name) throws ResourceNotFoundException {
        return productService.getProductByName(name);
    }

    @GetMapping("/user/{id_user}")
    public List<Product> getProductsByUserId(@PathVariable Long id_user) {
        List<Product> products = this.getProductsByUserId(id_user);
        return products;
    }
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) throws ResourceNotFoundException{
        Product updatedProduct = productService.updateProduct(id, productDTO);
        return updatedProduct;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) throws ResourceNotFoundException {
        return productService.getProductById(id); // Retorna directamente el ProductDTO
    }
//    public ProductDTO getProductById(@PathVariable Long id) throws ResourceNotFoundException {
//        ProductDTO productDTO = productService.getProductById(id);
//        if (productDTO == null) {
//            throw new ResourceNotFoundException("Product not found with id: " + id);
//        }
//        return productDTO;
//    }


    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) throws ResourceNotFoundException {
        productService.deleteProductById(id);
    }

    @PostMapping("/request")
    public ResponseEntity<String> requestProduct(@RequestBody ProductRequestDTO productRequest)  throws ResourceNotFoundException {
        productService.requestProduct(productRequest.getProductId(), productRequest.getRequesterId());
        return ResponseEntity.ok("Producto solicitado con exito");
    }   

//    @PutMapping("/disable/{idProduct}")
//    public ResponseEntity<String> disabledProduct(@PathVariable Long idProduct) throws ResourceNotFoundException {
//        ProductDTO newProduct = new ProductDTO();
//        Product product = productService.getProductById(idProduct);
//        newProduct.setName(product.getName());
//        newProduct.setIdUser(product.getIdUser());
//        newProduct.setDescription(product.getDescription());
//        newProduct.setCreationDate(LocalDate.parse(product.getCreationDate()));
//        newProduct.setAvailable(false);
//        newProduct.setImageURL(product.getImageURL());
//        newProduct.setCategoryId(product.getCategoryId());
//        newProduct.setState(product.getState());
//        newProduct.setUserName(product.getUserName());
//        newProduct.setUserLastName(product.getUserLastName());
//        newProduct.setUserEmail(product.getUserEmail());
//        newProduct.setUserProvince(product.getUserProvince());
//        productService.updateProduct(idProduct, newProduct);
//
//        return ResponseEntity.ok("Producto desabilitado con exito");
//    }
}
