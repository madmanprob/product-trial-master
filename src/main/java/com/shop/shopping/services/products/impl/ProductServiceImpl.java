package com.shop.shopping.services.products.impl;

import java.util.List;
import java.util.Optional;

import com.shop.shopping.controllers.products.dtos.ProductDto;
import com.shop.shopping.controllers.products.models.Product;
import com.shop.shopping.exceptions.ProductNotFoundException;
import com.shop.shopping.repositories.products.ProductRepository;
import com.shop.shopping.services.products.ProductService;
import com.shop.shopping.transformators.ProductTransformator;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;
	private final ProductTransformator productTransformator;


	public ProductServiceImpl(ProductRepository productRepository, ProductTransformator productTransformator) {
		this.productRepository = productRepository;
		this.productTransformator = productTransformator;
	}

	@Override
	public ProductDto createProduct(final ProductDto product) throws ProductNotFoundException {
		if (null == product) {
			throw new ProductNotFoundException("Il faut fournir un produit");
		}
		// Ajouter un nouveau produit
		Product createdProduct = productRepository.createProduct(productTransformator.productDtoToProduct(product));
		return productTransformator.productToProductDto(createdProduct);
	}

	@Override
	public List<ProductDto> getAllProducts() {
		List<Product> products = productRepository.getAllProducts();
		return productTransformator.productList(products);
	}

	@Override
	public ProductDto getProductById(final int id) throws ProductNotFoundException {
		// Recherche produit par ID
		Product product = productRepository.getProductById(id).orElseThrow(() -> new ProductNotFoundException("Product introuvable"));
		return productTransformator.productToProductDto(product);
	}

	@Override
	public ProductDto updateProduct(final int id, final ProductDto productDto) throws ProductNotFoundException {
		Optional<Product> product = productRepository.getProductById(id);
		if (product.isEmpty()) {
			throw new ProductNotFoundException("Product introuvable");
		}
		// Mise à jour des détails du produit
		Product updProduct = productRepository.updateProduct(id, productDto);
		return productTransformator.productToProductDto(updProduct);
	}

	@Override
	public void deleteProduct(final int id) throws ProductNotFoundException {
		Optional<Product> product = productRepository.getProductById(id);
		if (product.isEmpty()) {
			throw new ProductNotFoundException("Product introuvable");
		}
		// Suppression du produit
		productRepository.deleteProduct(id);
	}
}
