package domain.user;

import buy.desafio.api.domain.product.Product;
import buy.desafio.api.dto.UserRegisterDTO;
import buy.desafio.api.dto.UserUpdateBalanceDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Set;

@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"cpf", "email"}))
@Entity(name = "User")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is mandatory")
    @Size(max=100, message = "Size limit of 100 characters")
    private String name;
    @NotBlank(message = "CPF is mandatory")
    @CPF(message = "CPF must be a valid one")
//    @Column(unique = true)
    private String cpf;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email must follow pattern: name@domain.com")
    @Size(max=100, message = "Size limit of 100 characters")
//    @Column(unique = true)
    private String email;
    @Min(value = 0, message = "Value can't be lower than 0")
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
