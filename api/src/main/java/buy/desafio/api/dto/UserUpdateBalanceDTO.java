package buy.desafio.api.dto;

import jakarta.validation.constraints.NotNull;

public record UserUpdateBalanceDTO(
        @NotNull
        Long id,
        double deposit){


}
