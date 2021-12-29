package com.usa.habilitacion.repository.crudRepository;

import com.usa.habilitacion.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface productCrudRepository extends MongoRepository<Product, String > {
    public List<Product> findByDescriptionContainingIgnoreCase(String description);
    public List<Product> findByPriceLessThanEqual(Double price);
}
