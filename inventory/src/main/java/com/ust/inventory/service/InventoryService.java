package com.ust.inventory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.inventory.exception.ProductNotFoundException;
import com.ust.inventory.model.Product;
import com.ust.inventory.repository.ProductRepository;

@Service
public class InventoryService {
    @Autowired
    private ProductRepository repository;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product addProduct(Product product) {
        return repository.save(product);
    }

    public Product getProductById(Long id){
        Optional<Product> optional = repository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        else{
            throw new ProductNotFoundException();
        }
    }

    public Product updateProduct(Long id, Product updatedProduct){
        Optional<Product> optional = repository.findById(id);
        Product product = new Product();
        if(optional.isPresent()){
            product = optional.get();
        }
        else{
            throw new ProductNotFoundException();
        }
        product.setName(updatedProduct.getName());
        product.setStock(updatedProduct.getStock());
        product.setPrice(updatedProduct.getPrice());
        return repository.save(product);
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }
}
