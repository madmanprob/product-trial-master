package com.shop.shopping.services.products;

import java.util.List;

import com.shop.shopping.controllers.products.dtos.ProductDto;
import com.shop.shopping.exceptions.ProductNotFoundException;

public interface ProductService {
	ProductDto createProduct(ProductDto product) throws ProductNotFoundException;

	List<ProductDto> getAllProducts();

	ProductDto getProductById(int id) throws ProductNotFoundException;

	ProductDto updateProduct(int id, final ProductDto product) throws ProductNotFoundException;

	void deleteProduct(int id) throws ProductNotFoundException;

}
