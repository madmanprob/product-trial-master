package com.shop.shopping.transformators;

import java.util.List;

import com.shop.shopping.controllers.products.dtos.ProductDto;
import com.shop.shopping.controllers.products.models.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class ProductTransformator {
	private static ModelMapper modelMapper;

	private static ModelMapper modelMapper() {
		if (null == modelMapper) {
			modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			modelMapper.getConfiguration().setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
		}
		return modelMapper;
	}

	public Product productDtoToProduct(ProductDto product) {
		return modelMapper().map(product, Product.class);
	}

	public ProductDto productToProductDto(Product product) {
		return modelMapper().map(product, ProductDto.class);
	}

	public List<ProductDto> productList(List<Product> products) {
		return products.stream().map(product -> modelMapper().map(product, ProductDto.class)).toList();
	}
}
