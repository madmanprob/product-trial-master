package com.shop.shopping.controllers.products.models;

import com.shop.shopping.enums.InventoryStatus;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products_metier")
@ToString
@Data
public class Product {
	@Id
	private int id;
	private String code;
	private String name;
	private String description;
	private String image;
	private String category;
	private double price;
	private int quantity;
	private String internalReference;
	private int shellId;
	private InventoryStatus inventoryStatus;
	private double rating;
	private long createdAt;
	private long updatedAt;

}
