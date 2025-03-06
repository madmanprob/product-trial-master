package com.shop.shopping.repositories.products.impl;

import java.util.List;
import java.util.Optional;

import com.shop.shopping.controllers.products.dtos.ProductDto;
import com.shop.shopping.controllers.products.models.Product;
import com.shop.shopping.repositories.products.ProductCustomRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class ProductCustomRepositoryImpl implements ProductCustomRepository {
	private final MongoTemplate mongoTemplate;

	public ProductCustomRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public Product createProduct(final Product product) {
		// Ajout de timestamps pour la céation et la mise à jour
		product.setCreatedAt(System.currentTimeMillis());
		product.setUpdatedAt(System.currentTimeMillis());

		return mongoTemplate.save(product);
	}

	@Override
	public List<Product> getAllProducts() {
		return mongoTemplate.findAll(Product.class);
	}

	@Override
	public Optional<Product> getProductById(final int id) {
		Query query = new Query(Criteria.where("id").is(id));
		Product product = mongoTemplate.findOne(query, Product.class);
		return Optional.ofNullable(product);
	}

	@Override
	public Product updateProduct(final int id, final ProductDto productDto) {
		Optional<Product> product = getProductById(id);
		if (product.isPresent()) {
			product.get().setName(productDto.getName());
			product.get().setCode(productDto.getCode());
			product.get().setDescription(productDto.getDescription());
			product.get().setImage(productDto.getImage());
			product.get().setCategory(productDto.getCategory());
			product.get().setPrice(productDto.getPrice());
			product.get().setQuantity(productDto.getQuantity());
			product.get().setInternalReference(productDto.getInternalReference());
			product.get().setShellId(productDto.getShellId());
			product.get().setInventoryStatus(productDto.getInventoryStatus());
			product.get().setRating(productDto.getRating());
			product.get().setUpdatedAt(System.currentTimeMillis());
		}
		return mongoTemplate.save(product.get());
	}

	@Override
	public void deleteProduct(final int id) {
		Query query = new Query(Criteria.where("id").is(id));
		mongoTemplate.remove(query, Product.class);
	}
}
