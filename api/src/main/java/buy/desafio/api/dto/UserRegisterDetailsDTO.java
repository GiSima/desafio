package buy.desafio.api.dto;

import buy.desafio.api.domain.user.User;

public record UserRegisterDetailsDTO(Long id, String name, String cpf, Double balance) {

    public UserRegisterDetailsDTO(User user) {
        this(user.getId(), user.getName(), user.getCpf(), user.getBalance());
    }
}
