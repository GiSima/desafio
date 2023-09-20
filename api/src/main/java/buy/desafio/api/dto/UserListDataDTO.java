package buy.desafio.api.dto;

import buy.desafio.api.domain.User;

public record UserListDataDTO(Long id, String name, String cpf, String email, Double balance) {

    public UserListDataDTO(User user){
        this(user.getId(), user.getName(), user.getCpf(), user.getEmail(), user.getBalance());
    }

}
