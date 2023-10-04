package buy.desafio.api.dto;

import org.springframework.validation.FieldError;

public record ExceptionDTO(String message, String status) {

}
