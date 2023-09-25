package buy.desafio.api.domain.user;

import buy.desafio.api.domain.product.Product;
import buy.desafio.api.dto.UserRegisterDTO;
import buy.desafio.api.dto.UserUpdateBalanceDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.Pair;

import java.util.Set;

@Table(name = "users")
@Entity(name = "User")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cpf;
    private String email;
    private double balance;

    @ManyToMany
    @JoinTable(name = "shop",
            joinColumns = @JoinColumn(name = "user_id_fk"),
            inverseJoinColumns = @JoinColumn(name = "product_id_fk"))
    Set<Product> products;

    public User(UserRegisterDTO data) {
        this.name = data.name();
        this.email = data.email();
        this.cpf = data.cpf();
        this.balance = 0;
    }

    public boolean checkBalance(double price){
        return this.getBalance() >= price;
    }

    public void deposit(UserUpdateBalanceDTO data) {
        if(data.deposit() >= 0) {
            this.balance += data.deposit();
        } else throw new RuntimeException("Deposit must not be negative.");

    }

    public void purchase(double amount, Product product) {
        if(this.checkBalance(amount)) {
            this.balance -= amount;
            addProduct(product);
        } else throw new RuntimeException("Not enough money deposited.");
    }

    private void addProduct(Product product){
        products.add(product);
    }

}
