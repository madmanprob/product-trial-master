package com.shop.shopping.controllers.panier;

import com.shop.shopping.controllers.panier.models.Panier;
import com.shop.shopping.services.paniers.PanierService;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class PanierController {
	private final PanierService panierService;

	public PanierController(PanierService panierService) {this.panierService = panierService;}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Panier getCart() throws Exception {
		final String userEmail = getAuthenticatedUserEmail();
		return panierService.getCart(userEmail);
	}

	@PostMapping("/{id}")
	public void addToCart(@PathVariable int id) throws Exception {
		final String userEmail = getAuthenticatedUserEmail();
		panierService.addToCart(userEmail, id);
	}

	@DeleteMapping("/{id}")
	public void removeFromCart(@PathVariable int id) throws Exception {
		final String userEmail = getAuthenticatedUserEmail();
		panierService.deleteFromCart(userEmail, id);
	}

	private String getAuthenticatedUserEmail() throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			return (authentication.getPrincipal().toString());
		}
		throw new Exception("Utilisateur non authentifié");
	}

}
