package com.shop.shopping;

import com.shop.shopping.controllers.products.dtos.ProductDto;
import com.shop.shopping.controllers.products.models.Product;
import com.shop.shopping.enums.InventoryStatus;
import com.shop.shopping.exceptions.ProductNotFoundException;
import com.shop.shopping.repositories.products.ProductRepository;
import com.shop.shopping.services.products.ProductService;
import com.shop.shopping.transformators.ProductTransformator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductTransformator productTransformator;

	@Test
	void testAddProduct() throws ProductNotFoundException {
		Product product = new Product();
		product.setId(99);
		product.setName("pr 1");
		product.setCode("c321");
		product.setInventoryStatus(InventoryStatus.INSTOCK);

		when(productRepository.createProduct(any(Product.class))).thenReturn(product);

		ProductDto createdProduct = productService.createProduct(productTransformator.productToProductDto(product));

		assertNotNull(createdProduct);
		assertEquals("pr 1", createdProduct.getName());
		assertEquals("c321", createdProduct.getCode());
	}
}
