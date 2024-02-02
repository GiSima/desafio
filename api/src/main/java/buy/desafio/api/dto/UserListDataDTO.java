package buy.desafio.api.dto;

import com.domain.product.Product;
import com.domain.user.User;

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
