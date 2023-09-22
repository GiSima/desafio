package buy.desafio.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class ErrorTreatment {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity treatError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity treatError400(MethodArgumentNotValidException e){
        var errors = e.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(ValidationErrorDataDTO::new).toList());
    }

    @ExceptionHandler(HttpClientErrorException.UnprocessableEntity.class)
    public ResponseEntity treatError422(){
        return ResponseEntity.unprocessableEntity().build();
    }

    private record ValidationErrorDataDTO(String body, String message){
        public ValidationErrorDataDTO(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }

    }

}
