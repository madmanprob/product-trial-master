package com.shop.shopping.services.paniers;

import com.shop.shopping.controllers.panier.models.Panier;

public interface PanierService {
	Panier getCart(String userEmail);

	void addToCart(String userEmail, int productId);

	void deleteFromCart(String userEmail, int productId);
}
