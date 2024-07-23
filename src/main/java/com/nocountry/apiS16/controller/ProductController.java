package com.nocountry.apiS16.controller;

import com.nocountry.apiS16.dto.ProductDTO;
import com.nocountry.apiS16.exceptions.ResourceNotFoundException;
import com.nocountry.apiS16.model.Product;
import com.nocountry.apiS16.service.implementations.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public Product createProduct(@RequestBody ProductDTO productDTO) throws ResourceNotFoundException {
        return productService.createProduct(productDTO);
    }

    @GetMapping("/get")
    public List<Product> getAllProducts() {
        return productService.getAllProduct();
    }

    @GetMapping("/get/name/{name}")
    public Product getProductByName(@PathVariable String name) throws ResourceNotFoundException {
        return productService.getProductByName(name);
    }


    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) throws ResourceNotFoundException{
        return productService.updateProduct(id, productDTO);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) throws ResourceNotFoundException  {
        Product product = productService.getProductById(id);
        if (product == null) {
            throw new ResourceNotFoundException("Category not found with id: " + id);
        }
        return product;
    }


    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) throws ResourceNotFoundException {
        productService.deleteProductById(id);
    }

}
