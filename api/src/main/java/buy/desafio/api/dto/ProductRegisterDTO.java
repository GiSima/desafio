package buy.desafio.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductRegisterDTO(
        @NotNull(message = "Name is mandatory")
        String name,
        String description,
        @NotNull(message = "Price is mandatory")
        @Positive(message = "Price must not be negative")
        Double price) {
}
