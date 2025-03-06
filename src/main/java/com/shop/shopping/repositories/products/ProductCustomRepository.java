package com.shop.shopping.repositories.products;

import java.util.List;
import java.util.Optional;

import com.shop.shopping.controllers.products.dtos.ProductDto;
import com.shop.shopping.controllers.products.models.Product;

public interface ProductCustomRepository {
	Product createProduct(final Product product);

	List<Product> getAllProducts();

	Optional<Product> getProductById(final int id);

	Product updateProduct(final int id, final ProductDto product);

	void deleteProduct(final int id);
}
