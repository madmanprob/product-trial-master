package com.shop.shopping.services.users;

import com.shop.shopping.controllers.users.dtos.UserDto;
import com.shop.shopping.controllers.users.models.User;

public interface UserService {
	User createUser(final UserDto userDto);
	String authenticate(final String email, final String password) throws Exception;
}
