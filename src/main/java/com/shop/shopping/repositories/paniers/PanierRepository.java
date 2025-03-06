package com.shop.shopping.repositories.paniers;

import com.shop.shopping.controllers.panier.models.Panier;
import com.shop.shopping.controllers.users.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PanierRepository extends MongoRepository<Panier, String> {
    Panier findByUser(User user);
}
