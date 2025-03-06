package com.shop.shopping.controllers.products.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shop.shopping.enums.InventoryStatus;
import lombok.Data;

@Data
public class ProductDto {
	@JsonProperty("id")
	private int id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("code")
	private String code;

	@JsonProperty("description")
	private String description;

	@JsonProperty("image")
	private String image;

	@JsonProperty("category")
	private String category;

	@JsonProperty("price")
	private double price;

	@JsonProperty("quantity")
	private int quantity;

	@JsonProperty("internalReference")
	private String internalReference;

	@JsonProperty("shellId")
	private int shellId;

	@JsonProperty("inventoryStatus")
	private InventoryStatus inventoryStatus;

	@JsonProperty("rating")
	private double rating;

	@JsonProperty("updatedAt")
	private long updatedAt;

	@JsonProperty("createdAt")
	private long createdAt;
}
