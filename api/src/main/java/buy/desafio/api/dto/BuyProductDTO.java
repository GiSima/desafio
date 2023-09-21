package buy.desafio.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record BuyProductDTO(
        @NotNull(message = "User Id is mandatory")
        Long userId,
        @NotNull(message = "Product Id is mandatory")
        Long productId,
        @Positive(message = "Amount must not be negative")
        double amount) {
}
