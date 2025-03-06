package com.shop.shopping.controllers.users;

import com.shop.shopping.controllers.users.dtos.LoginUser;
import com.shop.shopping.controllers.users.dtos.UserDto;
import com.shop.shopping.controllers.users.models.User;
import com.shop.shopping.repositories.users.UserRepository;
import com.shop.shopping.services.users.UserService;
import com.shop.shopping.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {
	private final UserService userService;
	private final JwtUtil jwtUtil;
	private final UserRepository userRepository;

	public AuthenticationController(UserService userService, JwtUtil jwtUtil, UserRepository userRepository) {
		this.userService = userService;
		this.jwtUtil = jwtUtil;
		this.userRepository = userRepository;
	}

	// Création d'un compte
	@PostMapping("/account")
	public ResponseEntity<String> createAccount(@RequestBody UserDto userDto) {
		User user = userService.createUser(userDto);
		return ResponseEntity.ok("Utilisateur créé avec succès");
	}

	// Route pour obtenir le token JWT
//	@PostMapping("/token")
//	public ResponseEntity<String> createToken(@RequestBody LoginRequest loginRequest) {
//		User user = userRepository.findByEmail(loginRequest.getEmail());
//		if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
//			String token = jwtUtil.generateToken(user.getEmail());
//			return ResponseEntity.ok(token);
//		} else {
//			return ResponseEntity.status(401).body("Email ou mot de passe incorrect");
//		}
//	}
	// Générer le token lors de l'authentification
	@PostMapping("/token")
	public ResponseEntity<String> authenticate(@RequestBody LoginUser loginUser) {
		try {
			// Retourner token JWT
			String token = userService.authenticate(loginUser.getEmail(), loginUser.getPassword());
			return ResponseEntity.ok(token);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
	}


}
