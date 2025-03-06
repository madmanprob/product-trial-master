package com.shop.shopping.repositories.users.impl;

import com.shop.shopping.controllers.users.models.User;
import com.shop.shopping.repositories.users.UserCustomRepository;
import org.springframework.data.mongodb.core.MongoTemplate;

public class UserCustomRepositoryImpl implements UserCustomRepository {

	private final MongoTemplate mongoTemplate;

	public UserCustomRepositoryImpl(MongoTemplate mongoTemplate) {this.mongoTemplate = mongoTemplate;}

	@Override
	public User createUser(final User user) {
		return mongoTemplate.save(user);
	}
}
