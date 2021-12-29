package com.usa.habilitacion.service;

import com.usa.habilitacion.model.Product;
import com.usa.habilitacion.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class productService {
    @Autowired
    private productRepository productRepository;

    public List<Product> getAll(){
        return productRepository.getAll();
    }
    public Optional<Product> getProduct(String Reference){
        return productRepository.getProduct(Reference);
    }
    public Product save(Product product){
        if(product.getReference() == "null"){
            return product;
        }
        else{
            return productRepository.create(product);
        }
    }
    public Product update(Product product) {

        if (product.getReference() != null) {
            Optional<Product> dbProduct = productRepository.getProduct(product.getReference());
            if (! dbProduct.isEmpty()) {
                if (product.getBrand()!= null) {
                    dbProduct.get().setBrand(product.getBrand());
                }
                if (product.getCategory() != null) {
                    dbProduct.get().setCategory(product.getCategory());
                }
                if (product.getNombre() != null) {
                    dbProduct.get().setNombre(product.getNombre());
                }
                if (product.getDescription() != null) {
                    dbProduct.get().setDescription(product.getDescription());
                }
                if (product.getPrice() != 0.0) {
                    dbProduct.get().setPrice(product.getPrice());
                }
                if (product.getQuantity() != 0) {
                    dbProduct.get().setQuantity(product.getQuantity());
                }
                if (product.getPhotography()!= null) {
                    dbProduct.get().setPhotography(product.getPhotography());
                }
                productRepository.update(dbProduct.get());
                return dbProduct.get();
            } else {
                return product;
            }
        } else {
            return product;
        }
    }
    public boolean delete(String reference) {
        return  getProduct(reference).map(product -> {
            productRepository.delete(product);
            return true;
        }).orElse(false);
    }
    public List<Product> findByDescriptionContainingIgnoreCase(String description){
        return productRepository.findByDescriptionContainingIgnoreCase(description);
    }
    public List<Product> findByPriceLessThanEqual(Double price){
        return productRepository.findByPriceLessThanEqual(price);
    }
}
