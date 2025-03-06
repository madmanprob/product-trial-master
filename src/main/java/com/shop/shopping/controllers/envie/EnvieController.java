package com.shop.shopping.controllers.envie;

import com.shop.shopping.controllers.envie.models.Envie;
import com.shop.shopping.services.envie.EnvieService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/envie")
public class EnvieController {
	private final EnvieService envieService;

	public EnvieController(EnvieService envieService) {this.envieService = envieService;}

	@GetMapping()
	public Envie getWishlist(@RequestParam String userEmail) {
		return envieService.getWishlist(userEmail);
	}

	@PostMapping("/{id}")
	public void addToWishlist(@PathVariable int id) throws Exception {
		final String userEmail = getAuthenticatedUserEmail();
		envieService.addToWishlist(userEmail, id);
	}

	@DeleteMapping("/{id}")
	public void removeFromWishlist(@PathVariable int id) throws Exception {
		final String userEmail = getAuthenticatedUserEmail();
		envieService.removeFromWishlist(userEmail, id);
	}

	private String getAuthenticatedUserEmail() throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			return (authentication.getPrincipal().toString());
		}
		throw new Exception("Utilisateur non authentifié");
	}
}
