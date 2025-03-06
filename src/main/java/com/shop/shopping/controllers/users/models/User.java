package com.shop.shopping.controllers.users.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users_metier")
@Data
public class User {
    @Id
    private String id;
    private String username;
    private String firstname;
    private String email;
    private String password;  // Stocker le mot de passe haché
}
