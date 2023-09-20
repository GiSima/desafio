package buy.desafio.api.dto;

import jakarta.validation.constraints.NotNull;

public record UserUpdateBalance(
        @NotNull
        Long id,
        double deposit){


}
