package buy.desafio.api.domain.user;

import buy.desafio.api.dto.UserRegisterDTO;
import buy.desafio.api.dto.UserUpdateBalanceDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public void purchase(double amount) {
        if(this.checkBalance(amount)) {
            this.balance -= amount;
        } else throw new RuntimeException("Not enough money deposited.");
    }
}
