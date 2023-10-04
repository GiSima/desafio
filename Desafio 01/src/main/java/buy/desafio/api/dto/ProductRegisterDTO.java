package buy.desafio.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductRegisterDTO(String name, String description, Double price) {
}
