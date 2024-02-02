package com.domain.product;

import com.domain.user.User;
import com.dto.ProductRegisterDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Table(name = "products")
@Entity(name = "Product")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Name is mandatory")
    @Size(max=100, message = "Size limit of 100 characters")
    private String name;
    @Size(max=400, message = "Size limit of 400 characters")
    private String description;
    @NotNull(message = "Price is mandatory")
    @Positive(message = "Price must not be negative")
    private double price;

    @JsonIgnore
    @ManyToMany(mappedBy = "products")
    Set<User> users;

    public Product(ProductRegisterDTO data) {
        this.name = data.name();
        this.description = data.description();
        if (data.price() > 0)
            this.price = data.price();
        else throw new RuntimeException("Product price can't be negative.");
    }

    public void addUser(User user){
        users.add(user);
    }
}


