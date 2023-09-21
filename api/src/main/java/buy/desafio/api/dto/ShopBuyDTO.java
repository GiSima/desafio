package buy.desafio.api.dto;

import jakarta.validation.constraints.NotNull;

public record ShopBuyDTO(
        @NotNull
        Long idUser,
        @NotNull
        Long idProduct,
        double amount) {
}
