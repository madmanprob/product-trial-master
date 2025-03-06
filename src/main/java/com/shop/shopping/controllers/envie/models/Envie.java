package com.shop.shopping.controllers.envie.models;

import java.util.ArrayList;
import java.util.List;

import com.shop.shopping.controllers.products.models.Product;
import com.shop.shopping.controllers.users.models.User;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "envie")
@Data
public class Envie {
    @Id
    private String id;

    @DBRef
    private User user;

    @DBRef
    private List<Product> products = new ArrayList<>();
}
