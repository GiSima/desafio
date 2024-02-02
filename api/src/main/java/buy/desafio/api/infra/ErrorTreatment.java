package buy.desafio.api.infra;

import buy.desafio.api.dto.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ErrorTreatment {

    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<ExceptionDTO> treatErrorValidation(ValidationException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "422");
        return ResponseEntity.status(422).body(exceptionDTO);
    }

    @ExceptionHandler(value = {PurchaseNotAllowedException.class})
    public ResponseEntity<ExceptionDTO> treatPurchaseNotAllowed(PurchaseNotAllowedException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "403");
        return ResponseEntity.status(403).body(exceptionDTO);
    }

    @ExceptionHandler(value = {PurchaseException.class})
    public ResponseEntity<ExceptionDTO> treatPurchaseError(PurchaseException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "400");
        return ResponseEntity.status(400).body(exceptionDTO);
    }

    @ExceptionHandler(value = {PreconditionFailedException.class})
    public ResponseEntity<ExceptionDTO> treatPreconditionFailedError(PreconditionFailedException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "412");
        return ResponseEntity.status(412).body(exceptionDTO);
    }

    @ExceptionHandler(value = {NoSuchElementException.class, EntityNotFoundException.class})
    public ResponseEntity<ExceptionDTO> treatError404() {
        return ResponseEntity.notFound().build();
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
