package buy.desafio.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record BuyProductDTO(
        @NotNull(message = "User Id is mandatory")
        Long userId,
        @NotNull(message = "Product Id is mandatory")
        Long productId,
        @NotNull(message = "Amount is mandatory")
        @Positive(message = "Amount must not be negative")
        Integer amount) {
}
