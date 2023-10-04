package buy.desafio.api.infra;

import java.util.*;
import buy.desafio.api.dto.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.UnexpectedTypeException;
import jakarta.validation.ValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class ErrorTreatment {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionDTO> treatError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionDTO> treatErrorValidation(ValidationException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "422");
        return ResponseEntity.status(422).body(exceptionDTO);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDTO> treatError400(MethodArgumentNotValidException e){
        ExceptionDTO exceptionDTO = new ExceptionDTO(e.getMessage(), "400");
        return ResponseEntity.badRequest().body(exceptionDTO);
    }
//
//    @ExceptionHandler(HttpClientErrorException.UnprocessableEntity.class)
//    public ResponseEntity<ExceptionDTO> treatError422(){
//        ExceptionDTO exceptionDTO = new ExceptionDTO("Unprocessable Entity", "422");
//        return ResponseEntity.unprocessableEntity().body(exceptionDTO);
//    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionDTO> treatError409(DataIntegrityViolationException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "409");
        return ResponseEntity.status(409).body(exceptionDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> treatGeneralException(Exception exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "500");
        return ResponseEntity.internalServerError().body(exceptionDTO);
    }

}
