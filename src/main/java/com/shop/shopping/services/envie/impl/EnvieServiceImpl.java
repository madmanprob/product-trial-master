package com.shop.shopping.services.envie.impl;

import com.shop.shopping.controllers.envie.models.Envie;
import com.shop.shopping.controllers.products.models.Product;
import com.shop.shopping.controllers.users.models.User;
import com.shop.shopping.repositories.envie.EnvieRepository;
import com.shop.shopping.repositories.products.ProductRepository;
import com.shop.shopping.repositories.users.UserRepository;
import com.shop.shopping.services.envie.EnvieService;
import org.springframework.stereotype.Service;

@Service
public class EnvieServiceImpl implements EnvieService {
	private final EnvieRepository envieRepository;
	private final ProductRepository productRepository;
	private final UserRepository userRepository;

	public EnvieServiceImpl(EnvieRepository envieRepository, ProductRepository productRepository, UserRepository userRepository) {
		this.envieRepository = envieRepository;
		this.productRepository = productRepository;
		this.userRepository = userRepository;
	}

	public void addToWishlist(String userEmail, int productId) {
		User user = userRepository.findByEmail(userEmail);
		Product product = productRepository.getProductById(productId).orElseThrow(() -> new RuntimeException("Produit introuvable"));

		Envie envie = envieRepository.findByUser(user);
		if (envie == null) {
			envie = new Envie();
			envie.setUser(user);
		}
		envie.getProducts().add(product);
		envieRepository.save(envie);
	}

	public void removeFromWishlist(String userEmail, int productId) {
		User user = userRepository.findByEmail(userEmail);
		Product product = productRepository.getProductById(productId).orElseThrow(() -> new RuntimeException("Produit introuvable"));

		Envie wishlist = envieRepository.findByUser(user);
		if (wishlist != null) {
			wishlist.getProducts().remove(product);
			envieRepository.save(wishlist);
		}
	}

	// get la liste d'envies d'un utilisateur
	public Envie getWishlist(String userEmail) {
		User user = userRepository.findByEmail(userEmail);
		return envieRepository.findByUser(user);
	}
}
