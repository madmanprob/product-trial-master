package com.shop.shopping.repositories.users;

import com.shop.shopping.controllers.users.models.User;

public interface UserCustomRepository {
	User createUser(User user);

}
