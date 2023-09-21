package buy.desafio.api.domain;

import buy.desafio.api.dto.ProductRegisterDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "products")
@Entity(name = "Product")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;

    public Product(ProductRegisterDTO data) {
        this.name = data.name();
        this.description = data.description();
        if (data.price() > 0)
            this.price = data.price();
        else throw new RuntimeException("Product price can't be negative.");
    }
}


