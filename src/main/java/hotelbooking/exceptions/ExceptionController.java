package hotelbooking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestController
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> accessDeniedException(AccessDeniedException exception, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(),
                exception.getMessage(),
                request.getDescription(false),
                HttpStatus.UNAUTHORIZED);
        return new ResponseEntity(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<?> invalidInputException(InvalidInputException exception, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(),
                exception.getMessage(),
                request.getDescription(false),
                HttpStatus.BAD_REQUEST);
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
