package com.shop.shopping.services.users.impl;

import com.shop.shopping.controllers.users.dtos.UserDto;
import com.shop.shopping.controllers.users.models.User;
import com.shop.shopping.repositories.users.UserRepository;
import com.shop.shopping.services.users.UserService;
import com.shop.shopping.utils.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncodera;
	private final JwtUtil jwtUtil;

	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncodera, JwtUtil jwtUtil) {
		this.userRepository = userRepository;
		this.passwordEncodera = passwordEncodera;
		this.jwtUtil = jwtUtil;
	}

	// Enregistrer un utilisateur
	@Override
	public User createUser(UserDto userDto) {
		String hashedPassword = passwordEncodera.encode(userDto.getPassword());
		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setFirstname(userDto.getFirstname());
		user.setEmail(userDto.getEmail());
		user.setPassword(hashedPassword);
		return userRepository.createUser(user);
	}

	@Override
	public String authenticate(final String email, final String password) throws Exception {
		User user = userRepository.findByEmail(email);  // Rechercher l'utilisateur par email
		if (user == null) {
			throw new Exception("Utilisateur non trouvé");
		}

		// Vérifier si le mot de passe est correct
		if (!passwordEncodera.matches(password, user.getPassword())) {
			throw new Exception("Mot de passe incorrect");
		}

		// Générer un token JWT si les identifiants sont valides
		return jwtUtil.generateToken(user.getEmail());
	}
}
