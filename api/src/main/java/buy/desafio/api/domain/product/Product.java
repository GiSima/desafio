package buy.desafio.api.domain.product;

import buy.desafio.api.domain.user.User;
import buy.desafio.api.dto.ProductRegisterDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    private String name;
    private String description;
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


