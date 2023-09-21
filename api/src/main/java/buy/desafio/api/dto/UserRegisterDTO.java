package buy.desafio.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserRegisterDTO(
        @NotBlank(message = "Name is mandatory")
        String name,
        @NotBlank(message = "CPF is mandatory")
        @Pattern(regexp = "\\d{11}", message = "CPF must be eleven digits long")
        String cpf,
        @NotBlank(message = "Email is mandatory")
        @Email(message = "Email must follow pattern: name@domain.com")
        String email) {
}
