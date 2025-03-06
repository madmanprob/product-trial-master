package com.shop.shopping;

import com.shop.shopping.controllers.products.ProductController;
import com.shop.shopping.controllers.products.dtos.ProductDto;
import com.shop.shopping.controllers.users.models.User;
import com.shop.shopping.enums.InventoryStatus;
import com.shop.shopping.repositories.products.ProductRepository;
import com.shop.shopping.services.products.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class ProductControllerTest {

	@Mock
	private ProductService productService;

	@InjectMocks
	private ProductController productController;

	@Autowired
	private MockMvc mockMvc;
	@Mock
	private ProductRepository productRepository;

	@BeforeEach
	public void setup() {
		User userAdmin = new User();
		userAdmin.setEmail("admin@admin.com");
		Authentication authentication = new UsernamePasswordAuthenticationToken(userAdmin.getEmail(), null);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Configurer MockMvc
		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}

	@Test
	void testCreateProduct() throws Exception {
		ProductDto product = new ProductDto();
		product.setId(100);
		product.setName("pr 1");
		product.setCode("c321");
		product.setInventoryStatus(InventoryStatus.INSTOCK);
		when(productService.createProduct(any(ProductDto.class))).thenReturn(product);

		mockMvc.perform(post("/products").contentType(MediaType.APPLICATION_JSON).content("{\"id\": \"100\",\"name\": \"pr 1\", \"code\": \"c321\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("pr 1"))
				.andExpect(jsonPath("$.code").value("c321"));
	}
}
