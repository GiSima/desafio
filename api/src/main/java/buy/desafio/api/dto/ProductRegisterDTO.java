package buy.desafio.api.dto;

import jakarta.validation.constraints.NotNull;

public record ProductRegisterDTO(
        @NotNull
        String name,
        String description,
        @NotNull
        Double price) {
}
