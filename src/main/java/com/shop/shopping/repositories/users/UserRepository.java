package com.shop.shopping.repositories.users;

import com.shop.shopping.controllers.users.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String>, UserCustomRepository {
	User findByEmail(String email);
}