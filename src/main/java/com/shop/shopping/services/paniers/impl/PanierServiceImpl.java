package com.shop.shopping.services.paniers.impl;

import com.shop.shopping.controllers.panier.models.Panier;
import com.shop.shopping.controllers.products.models.Product;
import com.shop.shopping.controllers.users.models.User;
import com.shop.shopping.repositories.paniers.PanierRepository;
import com.shop.shopping.repositories.products.ProductRepository;
import com.shop.shopping.repositories.users.UserRepository;
import com.shop.shopping.services.paniers.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

@Service
public class PanierServiceImpl implements PanierService {
	private final PanierRepository panierRepository;
	private final ProductRepository productRepository;
	private final UserRepository userRepository;

	public PanierServiceImpl(PanierRepository panierRepository, ProductRepository productRepository, UserRepository userRepository) {
		this.panierRepository = panierRepository;
		this.productRepository = productRepository;
		this.userRepository = userRepository;
	}

	// Récupérer le panier d'un utilisateur
	public Panier getCart(String userEmail) {
		User user = userRepository.findByEmail(userEmail);
		return panierRepository.findByUser(user);
	}

	// Ajouter un produit au panier
	@Override
	public void addToCart(String userEmail, int productId) {
		User user = userRepository.findByEmail(userEmail);
		Product product = productRepository.getProductById(productId).orElseThrow(() -> new RuntimeException("Produit introuvable"));

		Panier panier = panierRepository.findByUser(user);
		if (panier == null) {
			panier = new Panier();
			panier.setUser(user);
		}
		panier.getProducts().add(product);
		panierRepository.save(panier);
	}

	// Enlever un produit du panier
	@Override
	public void deleteFromCart(String userEmail, int productId) {
		User user = userRepository.findByEmail(userEmail);
		Product product = productRepository.getProductById(productId).orElseThrow(() -> new RuntimeException("Produit introuvable"));
		Panier panier = panierRepository.findByUser(user);
		if (panier != null) {
			panier.getProducts().remove(product);
			panierRepository.save(panier);
		}
	}
}
