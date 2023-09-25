package buy.desafio.api.dto;

import buy.desafio.api.domain.product.Product;
import buy.desafio.api.domain.user.User;

import java.util.Set;

public record UserListDataDTO(
        Long id,
        String name,
        String cpf,
        String email,
        Double balance,
        Set<Product> owned) {

    public UserListDataDTO(User user){
        this(user.getId(), user.getName(), user.getCpf(), user.getEmail(), user.getBalance(), user.getProducts());
    }

}
