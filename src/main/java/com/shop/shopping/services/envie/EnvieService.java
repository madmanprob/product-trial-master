package com.shop.shopping.services.envie;

import com.shop.shopping.controllers.envie.models.Envie;

public interface EnvieService {
	public Envie getWishlist(String userEmail);

	public void addToWishlist(String userEmail, int productId);

	public void removeFromWishlist(String userEmail, int productId);

}
