package com.shop.shopping.controllers.products;

import java.util.List;

import com.shop.shopping.controllers.products.dtos.ProductDto;
import com.shop.shopping.exceptions.ProductNotFoundException;
import com.shop.shopping.services.products.ProductService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {
	private final ProductService productService;

	public ProductController(final ProductService productService) {
		this.productService = productService;
	}

	// Créer un produit
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product) throws ProductNotFoundException {
		if (Boolean.FALSE.equals(isAdmin())){
			return ResponseEntity.status(403).build();
		}
		ProductDto createdProduct = productService.createProduct(product);
		return ResponseEntity.ok(createdProduct);
	}

	// Obtenir tous les produits
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDto>> getAllProducts() {
		List<ProductDto> products = productService.getAllProducts();
		return ResponseEntity.ok(products);
	}

	// Obtenir un produit par ID
	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable int id) throws ProductNotFoundException {
		ProductDto productDto = productService.getProductById(id);
		return ResponseEntity.ok(productDto);
	}

	// Mettre à jour un produit
	@PatchMapping("/{id}")
	public ResponseEntity<ProductDto> updateProduct(@PathVariable int id, @RequestBody ProductDto product) throws ProductNotFoundException {
		if (Boolean.FALSE.equals(isAdmin())){
			return ResponseEntity.status(403).build();
		}
		ProductDto updatedProduct = productService.updateProduct(id, product);
		return ResponseEntity.ok(updatedProduct);
	}

	// Supprimer un produit
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable int id) throws ProductNotFoundException {
		if (Boolean.FALSE.equals(isAdmin())){
			return ResponseEntity.status(403).build();
		}
		productService.deleteProduct(id);
		return ResponseEntity.ok().build();
	}

	boolean isAdmin() {
		final String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
		return "admin@admin.com".equals(currentUserEmail);
	}

}