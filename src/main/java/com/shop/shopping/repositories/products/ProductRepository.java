package com.shop.shopping.repositories.products;

import com.shop.shopping.controllers.products.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String>, ProductCustomRepository {}
