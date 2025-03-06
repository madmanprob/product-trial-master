package com.shop.shopping.repositories.envie;

import com.shop.shopping.controllers.envie.models.Envie;
import com.shop.shopping.controllers.panier.models.Panier;
import com.shop.shopping.controllers.users.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EnvieRepository extends MongoRepository<Envie, String> {
    Envie findByUser(User user);
}
